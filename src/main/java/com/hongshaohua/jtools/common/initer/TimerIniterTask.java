package com.hongshaohua.jtools.common.initer;

import java.util.Timer;

/**
 * Created by Aska on 2017/6/22.
 */
public abstract class TimerIniterTask extends IniterTaskTemplate<Timer> {

    private boolean setName = false;
    private String name;
    private boolean setIsDaemon = false;
    private boolean isDaemon;

    public TimerIniterTask(String name, boolean isDaemon) {
        this.setName = true;
        this.name = name;
        this.setIsDaemon = true;
        this.isDaemon = isDaemon;
    }

    public TimerIniterTask(String name) {
        this.setName = true;
        this.name = name;
    }

    public TimerIniterTask(boolean isDaemon) {
        this.setIsDaemon = true;
        this.isDaemon = isDaemon;
    }

    public TimerIniterTask() {
    }

    @Override
    public void init() throws Exception {
        if(this.setName && this.setIsDaemon) {
            this.finish(new Timer(this.name, this.isDaemon));
        } else if(this.setName && !this.setIsDaemon) {
            this.finish(new Timer(this.name));
        } else if(!this.setName && this.setIsDaemon) {
            this.finish(new Timer(this.isDaemon));
        } else {
            this.finish(new Timer());
        }
    }
}
