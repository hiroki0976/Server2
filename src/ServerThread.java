
import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    private static final String DOCUMENT_ROOT = "/Users/fujioka/HTML/";
    private Socket socket;

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            String path = null;
            String ext = null;
            String line;

            while ((line = Util.readLine(input)) != null) {
                if (line == "") break;
                if (line.startsWith("GET")) {
                    path = line.split(" ")[1];
                    String[] tmp = path.split("\\.");
                    ext = tmp[tmp.length - 1];
                }
            }

            try(FileInputStream fis = new FileInputStream(DOCUMENT_ROOT + path)) {
                SendResponse.SendOkResponse(socket, fis, ext);
            } catch (FileNotFoundException ex) {
                SendResponse.SendNotFoundResponse(socket, DOCUMENT_ROOT);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }



    ServerThread(Socket socket) {
        this.socket = socket;
    }
}
