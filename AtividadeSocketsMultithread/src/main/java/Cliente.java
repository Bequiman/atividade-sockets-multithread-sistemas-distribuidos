import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private static DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Cartao cartao = new Cartao();
        TransacaoCliente transacaoCliente = null;
        int op = -1;
        String formaPagamento;
        TransacaoServidor respostaServidor;

        while (op != 3) {
            Socket conexao = new Socket("127.0.0.1", 2001);
            ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());

            imprimeMenu();
            op = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (op) {
                case 1, 2:
                    formaPagamento = Integer.toString(op);

                    if (transacaoCliente == null) {
                        transacaoCliente = transacao(formaPagamento);
                    }

                    cartao.adicionarTransacaoCliente(transacao(formaPagamento));

                    saida.writeObject(transacao(formaPagamento));
                    saida.flush();

                    respostaServidor = (TransacaoServidor) entrada.readObject();

                    if (respostaServidor.getCartao() != null) {
                        transacaoCliente.setCartao(respostaServidor.getCartao());
                        cartao.setNumero(respostaServidor.getCartao().getNumero());
                        cartao.setNomeCliente(respostaServidor.getCartao().getNomeCliente());
                        cartao.setSaldo(respostaServidor.getCartao().getSaldo());
                        cartao.adicionarTransacaoServidor(respostaServidor);

                        System.out.println(respostaServidor.getCodigoResposta());
                        System.out.println(cartao);
                    } else {
                        System.out.println(respostaServidor.getCodigoResposta());
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("FIM DO PROGRAMA!");
                    break;
                default:
                    System.out.println("Entrada inválida!!!");
                    System.out.println();
            }
        }
    }
    public static void imprimeMenu() {
        System.out.println("------------Menu----------------");
        System.out.println("1. Debitar");
        System.out.println("2. Creditar");
        System.out.println("3. Sair");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.print("Digite a operação desejada : ");
    }
    public static TransacaoCliente transacao(String formaPagamento) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String data = dtfDate.format(date);
        String hora = dtfTime.format(time);

        return new TransacaoCliente("200", 200, hora, data, "040104", "401231021845", formaPagamento);
    }
}

