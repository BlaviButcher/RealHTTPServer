import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer
{
    private static final ExecutorService exec =  Executors.newFixedThreadPool(10);
    public static void main(String args[])
    {
        try{
            // Create serverSocket
            ServerSocket server = new ServerSocket(8080);
            while(true){
                // Wait for something to connect
                Socket client = server.accept();
                // Create new ServerSession of connected client
                HttpServerSession task = new HttpServerSession(client);

                // Get ip address of client to display
                InetAddress ip = client.getInetAddress();
                System.out.println("Connection created with "+ ip);

                // run thread
                exec.execute(task);
                client.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("here");
        }
    }
}
