import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cliente3 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        Cartao cartao = new Cartao();

        Socket conexao = new Socket("127.0.0.1", 2001);
        ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String data = dtfDate.format(date);
        String hora = dtfTime.format(time);

        TransacaoCliente tc = new TransacaoCliente("200", 200, hora, data, "040104", "000000000000", "2");

        cartao.adicionarTransacaoCliente(tc);
        saida.writeObject(tc);
        saida.flush();

        TransacaoServidor respostaServidor = (TransacaoServidor) entrada.readObject();


        if (respostaServidor.getCartao() != null) {
            tc.setCartao(respostaServidor.getCartao());
            cartao.setNumero(respostaServidor.getCartao().getNumero());
            cartao.setNomeCliente(respostaServidor.getCartao().getNomeCliente());
            cartao.setSaldo(respostaServidor.getCartao().getSaldo());
            cartao.adicionarTransacaoServidor(respostaServidor);

            System.out.println("Mensagem recebida do servidor: " + respostaServidor.getTipoMensagem());
            System.out.println(cartao);
        } else {
            System.out.println(respostaServidor.getCodigoResposta());
            System.out.println("Cartão não encontrado no servidor.");
        }
    }
}

