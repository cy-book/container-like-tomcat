package hz.xhxh.container.utility;

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

    public static Properties getUserProperties(){
        return userProperties;
    }

    public static final String WEB_ROOT;
    static {
        var files =  userProperties.getProperty("web_root");
        WEB_ROOT = Objects.requireNonNullElseGet(files, () -> ConstantsAssist.WEB_ROOT_DEFAULT);
    }
    public static final int port;
    static {
        var pp = userProperties.getProperty("port");
        if(null == pp){
            port = ConstantsAssist.PORT_DEFAULT;
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
                port = ConstantsAssist.PORT_DEFAULT;
            }
        }
    }
}
