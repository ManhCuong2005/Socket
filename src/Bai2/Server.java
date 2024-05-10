import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import javax.swing.SwingUtilities;

public class Server extends Thread {
    static ServerSocket serverSocket;
    DataOutputStream output;
    DataInputStream input;
    static Socket socket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            System.out.println("Connected successful");

            Server t = new Server();
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            while (true) {
                String time = input.readUTF();
                if ("time".equals(time)) {
                    LocalTime currentTime = LocalTime.now();
                    int hour = currentTime.getHour();
                    int minute = currentTime.getMinute();
                    int second = currentTime.getSecond();
                    String messageString = hour + " : " + minute + " : " + second;

                    output.writeUTF(messageString);
                    output.flush();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}