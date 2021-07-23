package hz.xhxh.container.http.connector;

import hz.xhxh.container.utility.MyServletConstants;
import hz.xhxh.container.utility.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ServletProcessor {
    //private static URLClassLoader ucl = new URLClassLoader(new URL[]{});
    private static URLClassLoader ucl;
    /*
    初始化类加载器，可以加载servlets目录下的类
     */
    static{
        URL servletsUrl = null;
        try {
            servletsUrl = new File(Constants.WEB_ROOT + File.separator + "servlets").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ucl  = new URLClassLoader(new URL[]{servletsUrl});
    }

    /*
    解析 匹配servlet 的uri
    需要判断是否能够加载servlet类
     */
    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/')+1);
        String className = MyServletConstants.getClassName(servletName);
        if(null == className){
            processError(request,response,String.format("Servlet %s is not exists in config file",servletName));
            return;
        }
        Class<?> myServlet = null;
        try {
            myServlet = ucl.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            processError(request,response,String.format("Servlet %s can't load",className));
            return;
        }

        Servlet servlet = null;
        try {
            assert myServlet != null;
            servlet = (Servlet)myServlet.getDeclaredConstructor().newInstance();
            servlet.service(request, response);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void processError(Request request,Response response,String message){
        new StaticResourceProcessor().processNotFound(request,response,message);
    }
}
