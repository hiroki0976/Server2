import java.net.Socket;

public class ServerThread2 implements Runnable{
    private Socket socket;

    @Override
    public void run() {
        try {
            SendResponse.SendAuthorResponse(socket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    ServerThread2(Socket socket) {
        this.socket = socket;
    }

}
