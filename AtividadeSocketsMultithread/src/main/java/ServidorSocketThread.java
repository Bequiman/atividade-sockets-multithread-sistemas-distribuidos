import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class ServidorSocketThread implements Runnable {
    private Socket clientSocket;
    private List<Cartao> cartoes;
    private static long nsuCounter = 0;
    private static DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    public ServidorSocketThread(Socket clientSocket, List<Cartao> cartoes) {
        this.clientSocket = clientSocket;
        this.cartoes = cartoes;
    }

    @Override
    public void run() {
        try (ObjectInputStream entrada = new ObjectInputStream(clientSocket.getInputStream())) {
            ObjectOutputStream saida = new ObjectOutputStream(clientSocket.getOutputStream());

            TransacaoCliente transacao = (TransacaoCliente) entrada.readObject();

            System.out.println(transacao);

            TransacaoServidor transacaoServidor = verificarCartao(transacao);

            saida.writeObject(transacaoServidor);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Cartao verificarDados(TransacaoCliente transacao){
        for (Cartao c : cartoes) {
            if (c.getNumero().equals(transacao.getNumeroCartao())) {
                return new Cartao(c.getNumero(),c.getNomeCliente(),c.getSaldo());
            }
        }
        return new Cartao(null,null,0);
    }

    public TransacaoServidor verificarCartao(TransacaoCliente transacao) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String data = dtfDate.format(date);
        String hora = dtfTime.format(time);

        for (Cartao c : cartoes) {
            if (c.getNumero().equals(transacao.getNumeroCartao())) {
                if (transacao.getFormaPagamento().equals("1")) {
                    if (c.debitar(transacao.getValor())) {
                        return new TransacaoServidor("210", c.getSaldo(), hora, data, "040104", "00", getNextNSU(), c);
                    } else {
                        return new TransacaoServidor("210", transacao.getValor(), hora, data, "040104", "51", 0L, c);
                    }
                } else {
                    if (c.creditar(transacao.getValor())) {
                        return new TransacaoServidor("210", c.getSaldo(), hora, data, "040104", "00", getNextNSU(), c);
                    } else {
                        return new TransacaoServidor("210", transacao.getValor(), hora, data, "040104", "05", 0L, c);
                    }
                }
            }
        }
        return new TransacaoServidor("210", transacao.getValor(), hora, data, "040104", "05", 0L, null);
    }

    private synchronized long getNextNSU() {
        nsuCounter++;
        return nsuCounter;
    }
}


/*
 private static AtomicLong nsuCounter = new AtomicLong(0);

    private long getNextNSU() {
        return nsuCounter.incrementAndGet();
    }

 */