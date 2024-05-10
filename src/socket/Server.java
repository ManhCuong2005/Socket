/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Server extends Thread{
    static ServerSocket serverSocket;
    DataInputStream input;
    static DataOutputStream output;
    static Socket socket;

    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            while (true) {
                String message = input.readUTF();
                System.out.println("Client: " + message);
            }
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            System.out.println("Connected successful");
            Server server = new Server();
            server.start();
            
            while (true) {
                output = new DataOutputStream(socket.getOutputStream());
                String messageString = sc.nextLine();
                output.writeUTF(messageString);
                output.flush();
            }
        } catch (Exception e) {
        }
    }
}
