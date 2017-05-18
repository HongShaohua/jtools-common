package com.hongshaohua.jtools.common.initer;

import java.util.List;

/**
 * Created by shaoh on 2017/5/3.
 */
public class ServiceIniter {

    private List<ServiceInitTask> tasks;

    public ServiceIniter(List<ServiceInitTask> tasks) {
        this.tasks = tasks;
    }

    public static void run(ServiceIniter serviceIniter) throws Exception {
        for(ServiceInitTask task : serviceIniter.tasks) {
            task.init();
        }
    }
}
