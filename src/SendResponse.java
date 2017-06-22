
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendResponse{
    static void SendOkResponse(Socket socket, FileInputStream fis, String ext) throws Exception {
        OutputStream output = socket.getOutputStream();

        Util.writeLine(output, "HTTP/1.1 200 OK");
        Util.writeLine(output, "Date: " + Util.getDate());
        Util.writeLine(output, "Server: Server.java");
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "Content-type: " + Util.getContentType(ext));
        Util.writeLine(output, "");

        int ch;
        while ((ch = fis.read()) != -1) {
            output.write(ch);
        }
    }

    static void SendNotFoundResponse(Socket socket, String errorDocumentRoot) throws Exception {
        OutputStream output = socket.getOutputStream();

        Util.writeLine(output, "HTTP/1.1 404 Not Found");
        Util.writeLine(output, "Date: " + Util.getDate());
        Util.writeLine(output, "Server: Server.java");
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "Content-type: text/html");
        Util.writeLine(output, "");

        try (FileInputStream fis = new FileInputStream(errorDocumentRoot + "404.html")) {
            int ch;
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }
        }
    }

    static void SendAuthorResponse(Socket socket) throws Exception {
        OutputStream output = socket.getOutputStream();

        Util.writeLine(output, "HTTP/1.1 401 Authorization Required");
        Util.writeLine(output, "WWW-Authenticate: Basic realm=\"Secret File\"");
        Util.writeLine(output, "Date: " + Util.getDate());
        Util.writeLine(output, "Server: Server.java");
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "Content-type: text/html");
        Util.writeLine(output, "");
    }

}
