package hz.xhxh.container.startup;

import hz.xhxh.container.http.connector.HttpServer;

public class Bootstrap {
    public static void main(String...args)
    {
        var server = new HttpServer();

        server.await();

    }
}
