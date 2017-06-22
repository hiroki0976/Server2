
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Authorized {
    Socket socket;
    String line;
    String encodeAuthorization;

    public boolean isAuthorized() {
        try {
            InputStream input = socket.getInputStream();
            while ((line = Util.readLine(input)) != null) {
                if (line == "") break;
                if (line.startsWith("Authorization")) {
                    if (line.split(" ")[2].equals(encodeAuthorization)) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.fillInStackTrace();
            }
        }

        return false;
    }


    public Authorized(Socket socket, String encodeAuthorization) {
        this.socket = socket;
        this.encodeAuthorization = encodeAuthorization;
    }

}
