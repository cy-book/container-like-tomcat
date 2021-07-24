package hz.xhxh.container.http.connector;

import java.io.IOException;
public class StaticResourceProcessor {
    public  void process(Request request, Response response){
        try {
            response.sendStaticSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    对于无法加载的资源，发送一个404页面，并标注错误信息
     */
    public void processNotFound(Request request, Response response,String message){
       try( var output = response.getWriter()) {
           String notfound = "HTTP/1.1 404 File Not Found\r\n" +
                   "Content-Type: text/html\r\n" +
                   "\r\n" +
                   "<h1>File Not Found</h1>\r\n" +
                   String.format("<h2>error Message: %s</h2>",message);
           output.print(notfound);
       }catch (IOException ignored){
       }
    }
}
