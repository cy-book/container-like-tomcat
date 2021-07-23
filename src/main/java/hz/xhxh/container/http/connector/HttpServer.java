package hz.xhxh.container.http.connector;


import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import static hz.xhxh.container.utility.Constants.port;

public class HttpServer
{

   // public static final String WEB_ROOT = System.getProperty("user.dir")+File.separator+"webapps";

    private static final String SHUTDOWN_COMMAND = "SHUTDOWN";

    private boolean shutdown = false;

    public void await()
    {
        ServerSocket serverSocket = null;
        //int port = 9980;

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

                if(request.getUri().startsWith("/servlet")){
                    ServletProcessor servletProcessor = new ServletProcessor();
                    servletProcessor.process(request,response);
                }else {
                    new StaticResourceProcessor().process(request,response);
                }

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
                input.close();
                output.close();
            }catch(Exception e){

                e.printStackTrace();
                continue;
            }

        }

    }

}
