package com.hongshaohua.jtools.common.android;

/**
 * Created by Aska on 2017/10/20.
 */
public class AndroidCtrlConf {

    private String adbPath;
    private String phoneTmpDirPath;
    private String localTmpDirPath;

    public AndroidCtrlConf(String adbPath, String phoneTmpDirPath, String localTmpDirPath) {
        this.adbPath = adbPath;
        this.phoneTmpDirPath = phoneTmpDirPath;
        this.localTmpDirPath = localTmpDirPath;
    }

    public String adbPath() {
        return adbPath;
    }

    public String phoneTmpDirPath() {
        return phoneTmpDirPath;
    }

    public String localTmpDirPath() {
        return localTmpDirPath;
    }
}
