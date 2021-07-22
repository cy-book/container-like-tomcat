package ch01;

import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class Response

{
    private static final int  BUFFER_SIZE = 1024;
    private OutputStream output;
    private Request request;
    
    public Response(OutputStream output)
    {
        this.output = output;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    public void sendStaticSource() throws IOException
    {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{
            File file = new File(HttpServer.WEB_ROOT,request.getUri());

            if(file.exists())
            {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes,0,BUFFER_SIZE);
                String found = "HTTP/1.1 200 OK\r\n"+
                    "Content-text:text/plain\r\n"+
                    "\r\n";
                output.write(found.getBytes());
                while(ch!=-1)
                {
                    output.write(bytes,0,ch);
                    ch = fis.read(bytes,0,BUFFER_SIZE);
                }
            }
            else
            {
                String notfound = "HTTP/1.1 404 File Not Found\r\n"+
                    "Content-Type: text/html\r\n"+
                    "Content-Length: 23\r\n"+
                    "\r\n"+
                    "<h1>File Not Found</h1>";
                output.write(notfound.getBytes());
            
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           if(fis!=null) fis.close();
        }
    }

}
