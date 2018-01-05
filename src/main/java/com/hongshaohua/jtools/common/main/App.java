package com.hongshaohua.jtools.common.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String url = "http://192.168.10.204:10809/admin/verify";
        String content = "abc";
        try {
            URL Url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)Url.openConnection();
            httpURLConnection.setDoOutput(true);   //需要输出
            httpURLConnection.setDoInput(true);   //需要输入
            httpURLConnection.setUseCaches(false);  //不允许缓存
            httpURLConnection.setRequestMethod("POST");   //设置POST方式连接
            //设置请求属性
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpURLConnection.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeBytes(content);
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode=httpURLConnection.getResponseCode();
            if(HttpURLConnection.HTTP_OK==resultCode){
                StringBuffer sb=new StringBuffer();
                String readLine=new String();
                BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
                while((readLine=responseReader.readLine())!=null){
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
