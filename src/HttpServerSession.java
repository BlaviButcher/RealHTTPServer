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
        System.out.println("Connection accepted");
    }
    public HttpServerSession(Socket s)
    {
        client = s;
    }
}
