package hz.xhxh.container.servlet;

import javax.servlet.*;
import java.io.IOException;

public class PrimitiveServlet implements Servlet {
//    private static  FileInputStream fInput;
//    static {
//        String packagePath = PrimitiveServlet.class.getPackageName();
//        try {
//            fInput = new FileInputStream(
//                    System.getProperty("user.dir") +
//                            "/src/main/java/"+
//                            PrimitiveServlet.class.getName().replace('.','/')+
//                            ".java"
//            );
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public PrimitiveServlet(){
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("primitivesServlet init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        var out = res.getWriter();
        String found = "HTTP/1.1 200 OK\r\n"+
                "Content-text:text/plain\r\n"+
                "\r\n";
        out.print(found);
        out.println("sophisticate is having a lot of experience in life");
        out.println("capability is the natural ability that makes person able to do somethings");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("primitiveServlet destroy");
    }
}
