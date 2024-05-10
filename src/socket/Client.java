/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Client extends Thread{
    static Socket socket;
    static DataOutputStream output;
    static DataInputStream input;

    public Client() {
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            while (true) {
                String message = input.readUTF();
                System.out.println("Server: " + message);
            }
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 8080);
            Client t = new Client();
            t.start();
            output = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String messString = sc.nextLine();
                output.writeUTF(messString);
                output.flush();
            }
        } catch (Exception e) {
        }
    }
}
