package hz.xhxh.container.startup;

import hz.xhxh.container.http.connector.HttpServer;
import hz.xhxh.container.utility.Constants;

public class Bootstrap {
    public static void main(String...args)
    {
        var server = new HttpServer();
        System.out.println(Constants.WEB_ROOT);
        System.out.println(Constants.port);
        server.await();

    }
}
