package hz.xhxh.container.utility;

import hz.xhxh.container.utility.Constants;

import java.util.*;

public class MyServletConstants {
    private static final Properties userProperties = Constants.getUserProperties();

    private static final Map<String,String> map = new HashMap<>();
    static {
        userProperties.forEach( (k,v)->{
            if(k.toString().endsWith("_Servlet")){
                map.put(k.toString().substring(0,k.toString().lastIndexOf('_')),v.toString());
            }
        });
    }

    public static String getClassName(String servletUri){
        return map.get(servletUri);
    }

    public static Set<Map.Entry<String, String>> getEntrySet(){
        return map.entrySet();
    }
}
