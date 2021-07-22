package hz.xhxh.container.http.connector;


import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.IOException;

public class HttpServer
{

    public static final String WEB_ROOT = System.getProperty("user.dir")+File.separator+"ROOT";

    private static final String SHUTDOWN_COMMAND = "SHUTDOWN";

    private boolean shutdown = false;



    public void await()
    {
        ServerSocket serverSocket = null;
        int port = 9980;

        try{
            serverSocket = new ServerSocket(port,1,InetAddress.getByName("localhost"));
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown)
        {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try{
                socket = serverSocket.accept();

                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticSource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
                input.close();
                output.close();
            }catch(IOException e){

                e.printStackTrace();
                continue;
            }


        }



    }

}
