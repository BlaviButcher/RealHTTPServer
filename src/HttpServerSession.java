import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class HttpServerSession implements Runnable
{

    Socket client;
    @Override
    public void run()
    {
        if(client.isClosed())
        {
            System.out.println("Client Disconnected");
            return;
        }
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String request = reader.readLine();
            System.out.println(request);
        }catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }
    }
    public HttpServerSession(Socket s)
    {
        client = s;
    }
}
