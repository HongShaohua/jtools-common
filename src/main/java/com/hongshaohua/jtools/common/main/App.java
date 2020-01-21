package com.hongshaohua.jtools.common.main;

import com.hongshaohua.jtools.common.android.AndroidShell;
import com.hongshaohua.jtools.common.android.AndroidTouch;

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

        genSN();

        test();

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

    public static void genSN() {
        try {
            VismartySN sn = new VismartySN();
            for(int i = 0; i < 100; i++) {
                VismartySNInfo info1 = new VismartySNInfo(210, 1548399057, i);
                sn.gen(info1, VismartySN.password);

                VismartySNInfo info2 = new VismartySNInfo(info1.getSn());
                sn.parse(info2, VismartySN.password);
                System.out.println(String.format("%02d", i) + " - " + info2.getSn());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void test() {
        AndroidTouch touch = new AndroidTouch("C:\\Users\\shaoh\\AppData\\Local\\Android\\sdk\\platform-tools\\adb.exe", "/dev/input/event1");
        touch.open();
        touch.down(100, 100);
        touch.move(110, 100);
        touch.move(120, 100);
        touch.move(130, 100);
        touch.move(140, 100);
        touch.move(150, 100);
        touch.move(160, 100);
        touch.up();
        touch.close();







        AndroidShell shell = new AndroidShell("C:\\Users\\shaoh\\AppData\\Local\\Android\\sdk\\platform-tools\\adb.exe");
        shell.open();
        int x1 = 100;
        int y1 = 100;
        int x2 = 200;
        int y2 = 100;

        /*
        String str = "0003 002f 00000000\n" +
                "0003 0039 0000021b\n" +
                "0003 0035 00000244\n" +
                "0001 014a 00000000\n" +
                "0000 0000 00000000";

        String res = null;
        StringReader stringReader = new StringReader(str);
        BufferedReader reader = new BufferedReader(stringReader);
        try {
            String line;
            while((line = reader.readLine()) != null) {
                String[] ss = line.split(" ");
                long type = Long.parseLong(ss[0], 16);
                long code = Long.parseLong(ss[1], 16);
                long value = Long.parseLong(ss[2], 16);
                String in = "sendevent /dev/input/event1 " + type + " " + code + " " + value;

                shell.exec(in);
                if(res == null) {
                    res = in;
                } else {
                    res = res + "\n" + in;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //shell.flush();

        //shell.exec("input swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
        //shell.exec("input swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
        shell.close();
    }
}
