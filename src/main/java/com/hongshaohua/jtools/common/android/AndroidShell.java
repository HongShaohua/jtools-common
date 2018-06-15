package com.hongshaohua.jtools.common.android;

import java.io.*;

/**
 * Created by Aska on 2018/5/9.
 */
public class AndroidShell {

    private String adb;
    private String device;
    private String adbCmd = null;

    private Process process;
    private BufferedReader iReader;
    private BufferedWriter oWriter;
    private BufferedReader eReader;

    public AndroidShell(String adb, String device) {
        this.adb = adb;
        this.device = device;
    }

    public AndroidShell(String adb) {
        this(adb, null);
    }

    public String adb() {
        return adb;
    }

    public String device() {
        return device;
    }

    private synchronized String adbCmd() {
        if(this.adbCmd == null) {
            this.adbCmd = this.adb();
            if(this.device() != null) {
                this.adbCmd = this.adbCmd + " -s " + this.device();
            }
        }
        return this.adbCmd;
    }

    private synchronized String shell() {
        return this.adbCmd() + " shell";
    }

    public synchronized boolean open() {
        if(this.process != null) {
            return true;
        }
        try {
            this.process = Runtime.getRuntime().exec(this.shell());
            this.iReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
            this.oWriter = new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream()));
            this.eReader = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(this.eReader != null) {
                    this.eReader.close();
                    this.eReader = null;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(this.oWriter != null) {
                    this.oWriter.close();
                    this.oWriter = null;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(this.iReader != null) {
                    this.iReader.close();
                    this.iReader = null;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(this.process != null) {
                    if(this.process.isAlive()) {
                        this.process.destroy();
                    }
                    this.process = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public synchronized boolean close() {
        if(this.process == null) {
            return true;
        }
        try {
            if(this.eReader != null) {
                this.eReader.close();
                this.eReader = null;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            if(this.oWriter != null) {
                this.oWriter.close();
                this.oWriter = null;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            if(this.iReader != null) {
                this.iReader.close();
                this.iReader = null;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            if(this.process != null) {
                if(this.process.isAlive()) {
                    this.process.destroy();
                }
                this.process.waitFor();
                this.process = null;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    public synchronized String exec(String cmd) {
        try {
            this.oWriter.write(cmd);
            this.oWriter.newLine();
            this.oWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
