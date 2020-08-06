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

        while(!(client.isClosed()))
        {
            try
            {
                // fileName to be sent to client
                String fileName = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());
                String request = reader.readLine();
                System.out.println(request);
                // Split request
                String parts[] = request.split(" ");
                // output filename requested
                if (parts[0].compareTo("GET") == 0)
                {
                    String filename = parts[1].substring(1);
                    System.out.println(filename);
                }


                if (parts[1].compareTo("/") == 0)
                {
                    System.out.println("works");
                    fileName = "index.html";
                }
                else if (parts[1].compareTo("/HttpServer") == 0 || parts[1].compareTo("/HttpServer.java") == 0)
                    fileName = "file.txt";
                else if (parts[1].compareTo("/photo") == 0)
                    fileName = "image-analysis.png";
                    // if //HttpServer or "".java was not requested then give 404 error
                else
                {

                    writeLn(bos, "HTTP/1.1 404 NOT FOUND");
                    writeLn(bos, "");
                    writeLn(bos, "Nothing to see here");

                    // log file not found
                    System.out.println("File not found: " + parts[1].substring(1));

                    bos.flush();
                    client.close();
                    return;
                }

                writeLn(bos, "HTTP/1.1 200 OK");
                writeLn(bos, "");
                //writeLn(bos, "Hello World");

                // declare byte array
                byte buf[] = new byte[2048];
                // Get input stream of file
                FileInputStream fis = new FileInputStream(fileName);
                // Read one byte (one character)
                int character = fis.read(buf);
                // while not end of byte
                while (character != -1)
                {
                    Thread.currentThread().sleep(50);
                    // send to buffer
                    bos.write(buf, 0, character);
                    //Should i flush every-time?
                    //bos.flush();
                    // read next character
                    character = fis.read(buf);


                }
                // Do I only need to flush at the end?
                bos.flush();
                client.close();
            } catch (Exception e)
            {
                System.err.println("Exception: " + e);
                return;
            }
        }
        return;
    }
    public HttpServerSession(Socket s)
    {
        client = s;
    }
    private void writeLn(BufferedOutputStream bos, String s) throws IOException
    {
        String news = s + "\r\n";
        byte[] array = news.getBytes();
        for(int i=0; i<array.length; i++){
            bos.write(array[i]);
        }
        return;
    }
}
