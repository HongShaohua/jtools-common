package com.hongshaohua.jtools.common.android;

/**
 * Created by Aska on 2018/5/9.
 */
public class AndroidTouch {

    private AndroidShell shell;
    private String devinput;

    private int contact = 510;
    private int cacheX = -1;
    private int cacheY = -1;


    public AndroidTouch(String adb, String device, String devinput) {
        this.shell = new AndroidShell(adb, device);
        this.devinput = devinput;
    }

    public AndroidTouch(String adb, String devinput) {
        this(adb, null, devinput);
    }

    public AndroidShell shell() {
        return this.shell;
    }

    public String devinput() {
        return devinput;
    }

    public synchronized boolean open() {
        return this.shell.open();
    }

    public synchronized boolean close() {
        return this.shell.close();
    }

    private synchronized String sendevent(int type, int code, int value) {
        return "sendevent " + this.devinput + " " + type + " " + code + " " + value;
    }

    public synchronized void down(int x, int y) {
        String cmd = "";
        cmd += sendevent(3, 57, this.contact++) + "\n";
        cmd += sendevent(3, 53, x) + "\n";
        cmd += sendevent(3, 54, y) + "\n";
        cmd += sendevent(1, 330, 1) + "\n";
        cmd += sendevent(0, 0, 0);

        this.cacheX = x;
        this.cacheY = y;
        this.shell.exec(cmd);
    }

    public synchronized void move(int x, int y) {
        String cmd = "";
        if(this.cacheX != x) {
            this.cacheX = x;
            cmd += sendevent(3, 53, x);
        }
        if(this.cacheY != y) {
            this.cacheY = y;
            if(!cmd.trim().isEmpty()) {
                cmd += "\n";
            }
            cmd += sendevent(3, 54, y);
        }
        if(cmd.trim().isEmpty()) {
            return;
        }
        cmd += "\n" + sendevent(0, 0, 0);
        this.shell.exec(cmd);
    }

    public synchronized void up() {
        String cmd = "";
        cmd += sendevent(3, 57, -1) + "\n";
        cmd += sendevent(1, 330, 0) + "\n";
        cmd += sendevent(0, 0, 0);
        this.cacheX = -1;
        this.cacheY = -1;
        this.shell.exec(cmd);
    }
}
