import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static void main(String[] args) throws IOException {
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(new Cartao("401231021845", "Fulano", 1000.0));
        cartoes.add(new Cartao("123456789012", "Ciclano", 800.0));
        cartoes.add(new Cartao("425601267894", "Beltrano", 200.0));

        int porta = 2001; // Porta para o servidor socket

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor socket está esperando por conexões...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
                // Inicie uma nova thread para lidar com o cliente
                Thread thread = new Thread(new ServidorSocketThread(clientSocket, cartoes));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
