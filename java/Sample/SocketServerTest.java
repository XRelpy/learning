/*************************************************************************
	> File Name: SocketServerTest.java
	> Author: 
	> Mail: 
	> Created Time: 2017年07月28日 星期五 16时33分35秒
 ************************************************************************/

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class SocketServerTest {
    public static void main(String[] args) {
        ServerSocket ser;
        try {
            ser = new ServerSocket(8189);
            while(true) {
                Socket client = ser.accept();
                ServerThread t = new ServerThread(client);
                t.start();
                System.out.println("Start Client:" + t.getId());
            }
        } catch(Exception ex) {

        }   
    }
}

class ServerThread extends Thread {
    private Socket client;
    public ServerThread (Socket client) {
        super();
        this.client = client;
    }
    
    @Override 
    public void run() {
        try {
            InputStream inStream = client.getInputStream();
            OutputStream outStream = client.getOutputStream();
            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true);
            out.println("Thread Running:" + this.getId());
            while(true) {
                String value = in.nextLine();
                out.println("Server:" + value);
                //out.println("Thread Running:" + this.getId());
                //Thread.sleep(5000);
                if (value.equals("exit")) {
                    out.println("Bye !!!");
                    break;
                }
            }
            client.close();
        } catch(Exception ex) {
            
        }   
    }
}
