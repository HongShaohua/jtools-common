package com.hongshaohua.jtools.common.initer;

import java.util.List;

/**
 * Created by shaoh on 2017/5/3.
 */
public class Initer {

    private List<IniterTask> tasks;

    public Initer(List<IniterTask> tasks) {
        this.tasks = tasks;
    }

    public static void run(Initer initer) throws Exception {
        for(IniterTask task : initer.tasks) {
            task.init();
        }
    }
}
