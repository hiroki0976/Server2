
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class Server2 {
    private static String user = "fujioka";
    private static String pass = "hiroki";
    private static final String userPassword = user + ":" + pass;
    private static String encodeAuthorization = Base64.getEncoder().encodeToString(userPassword.getBytes());

    public static void main(String[] args) throws Exception{
            try (ServerSocket server = new ServerSocket(8080)) {
                for(;;) {
                    Socket socket = server.accept();
                    Authorized author = new Authorized(socket, encodeAuthorization);

                    if (author.isAuthorized()) {
                        for (;;) {
                            socket = server.accept();
                            ServerThread serverThread = new ServerThread(socket);
                            Thread thread = new Thread(serverThread);
                            thread.start();
                        }
                    } else {
                        socket = server.accept();
                        ServerThread2 serverThread2 = new ServerThread2(socket);
                        Thread thread2 = new Thread(serverThread2);
                        thread2.start();
                    }
                }




        }
    }

}