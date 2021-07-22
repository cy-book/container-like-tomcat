package ch01;

import java.io.InputStream;
import java.io.IOException;

public class Request

{
    private InputStream input;
    private String uri;

    public Request(InputStream input)
    {
        this.input = input;
    }

    public void parse()
    {
        StringBuilder builder = new StringBuilder(2048);
        int l;
        byte[] buffer = new byte[2048];

        try{
            l = input.read(buffer);
        }catch(IOException e){
            e.printStackTrace();
            l = -1;
        }

        for(int i=0;i<l;i++)
        {
            builder.append((char)buffer[i]);
        }

        System.out.println(builder.toString());

        uri = parseUri(builder.toString());

    }

    public String parseUri(String requestString)
    {
        int index1,index2;

        index1 = requestString.indexOf(' ');
        if(index1!=-1){
            index2 = requestString.indexOf(' ',index1+1);
            if(index2>index1)
            return requestString.substring(index1+1,index2);
        }

        return null;
    }


    public String getUri()
    {
         return uri;    
    }




}

