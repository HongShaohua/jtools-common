package com.hongshaohua.jtools.common.android;

import com.hongshaohua.jtools.common.android.ui.ViewHierarchy;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Aska on 2017/9/25.
 */
public class AndroidCtrl {

    private static final Logger logger = LoggerFactory.getLogger(AndroidCtrl.class);

    private AndroidCtrlConf conf;
    private String adbPath;

    public AndroidCtrl(AndroidCtrlConf conf) {
        this.conf = conf;
    }

    private synchronized String adbPath() {
        if(this.adbPath == null) {
            if(this.conf.device() == null) {
                this.adbPath = this.conf.adbPath();
            } else {
                this.adbPath = this.conf.adbPath() + " -s " + this.conf.device();
            }
        }
        return this.adbPath;
    }

    private synchronized String cmd(String cmd) {
        return this.adbPath() + cmd;
    }

    private synchronized String phoneTmpFilePath(String filename) {
        return this.conf.phoneTmpDirPath() + "/" + filename;
    }

    private synchronized String localTmpFilePath(String filename) {
        return this.conf.localTmpDirPath() + "/" + filename;
    }

    private synchronized String shell(String params) {
        return this.cmd(" shell " + params);
    }

    private synchronized String pull(String params) {
        return this.cmd(" pull " + params);
    }

    private synchronized void execCmd(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public synchronized void tap(int x, int y) {
        String cmd = this.shell("input tap " + x + " " + y);
        this.execCmd(cmd);
    }

    public synchronized void swipe(int x1, int y1, int x2, int y2) {
        String cmd = this.shell("input swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
        this.execCmd(cmd);
    }

    public synchronized void press(int x, int y) {
        String cmd = this.shell("input press " + x + " " + y);
        this.execCmd(cmd);
    }

    public synchronized void roll(int dx, int dy) {
        String cmd = this.shell("input roll " + dx + " " + dy);
        this.execCmd(cmd);
    }

    public synchronized void text(String text) {
        String cmd = this.shell("input text \"" + text + "\"");
        this.execCmd(cmd);
    }

    private synchronized void keyevent(String keyevent) {
        String cmd = this.shell("input keyevent \"" + keyevent + "\"");
        this.execCmd(cmd);
    }

    public synchronized void home() {
        this.keyevent("KEYCODE_HOME");
    }

    public synchronized void back() {
        this.keyevent("KEYCODE_BACK");
    }

    public synchronized void enter() {
        this.keyevent("KEYCODE_ENTER");
    }

    public synchronized void screenshot(String phonePath, String localPath) {
        String screenshotCmd = this.shell("/system/bin/screencap -p " + phonePath);
        this.execCmd(screenshotCmd);

        String pullCmd = this.pull(phonePath + " " + localPath);
        this.execCmd(pullCmd);
    }

    public synchronized void screenshot(String path) {
        String filename = "screenshot__" + System.currentTimeMillis() + ".png";
        String phoneTmpPath = this.phoneTmpFilePath(filename);
        this.screenshot(phoneTmpPath, path);

        String delCmd = this.shell("rm -rf " + phoneTmpPath);
        this.execCmd(delCmd);
    }

    public synchronized String screenshot() {
        String filename = "screenshot__" + System.currentTimeMillis() + ".png";
        String localPath = this.localTmpFilePath(filename);
        this.screenshot(localPath);
        return localPath;
    }

    public synchronized String uiautomator() {
        String filename = "screenscan__" + System.currentTimeMillis() + ".xml";
        String phoneTmpPath = this.phoneTmpFilePath(filename);
        String localTmpPath = this.localTmpFilePath(filename);

        String uiautomatorCmd = this.shell("uiautomator dump " + phoneTmpPath);
        this.execCmd(uiautomatorCmd);

        String pullCmd = this.pull(phoneTmpPath + " " + localTmpPath);
        this.execCmd(pullCmd);

        String delCmd = this.shell("rm -rf " + phoneTmpPath);
        this.execCmd(delCmd);

        try {
            File file = new File(localTmpPath);
            //String content = FileUtils.readFileToString(file, Charset.defaultCharset());
            String content = FileUtils.readFileToString(file, "UTF-8");
            file.delete();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public synchronized ViewHierarchy screenscan() {
        String xmlStr = this.uiautomator();
        return ViewHierarchy.create(xmlStr);
    }

    //借助使用ADB Keyboard输入中文等unicode字符串
    public synchronized void adbKeyboardText(String text) {
        String cmd = this.shell("am broadcast -a ADB_INPUT_TEXT --es msg \"" + text + "\"");
        this.execCmd(cmd);
    }

    //借助使用ADB Keyboard输入回车
    public synchronized void adbKeyboardEnter() {
        String cmd = this.shell("am broadcast -a ADB_EDITOR_CODE --ei code 3");
        this.execCmd(cmd);
    }

    //借助使用ADB Keyboard输入退格
    public synchronized void adbKeyboardDel() {
        String cmd = this.shell("am broadcast -a ADB_INPUT_CODE --ei code 67");
        this.execCmd(cmd);
    }
}
