import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServer
{
    public static void main(String args[])
    {
        try{
            ServerSocket server = new ServerSocket(8080);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
