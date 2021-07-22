package hz.xhxh.container.utility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public final class Constants {
    private static final Properties userProperties = new Properties();
    static{
        try {
            userProperties.load(
                ClassLoader.getSystemResourceAsStream("application.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String WEB_ROOT;
    static {
        var files =  userProperties.getProperty("web_root");
        WEB_ROOT = Objects.requireNonNullElseGet(files, () -> System.getProperty("user.dir") + File.separator + "webapps");
    }
    public static final int port;
    static {
        var pp = userProperties.getProperty("port");
        if(null == pp){
            port = 8080;
        }else {
            boolean flag = true;
            for(var c : pp.getBytes(StandardCharsets.UTF_8)){
                if(c>'9' || c<'0') {
                    flag = false; break;
                }
            }
            if(flag) {
                port = Integer.parseInt(pp);
            }else {
                port = 8080;
            }
        }
    }
}
