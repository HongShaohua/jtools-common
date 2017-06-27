package com.hongshaohua.jtools.common.initer;

/**
 * Created by Aska on 2017/6/22.
 */
public abstract class IniterTaskTemplate<T> implements IniterTask {

    private T t;

    public IniterTaskTemplate() {
    }

    public IniterTaskTemplate(T t) {
        this.t = t;
    }

    @Override
    public void init() throws Exception {
        this.finish(t);
    }

    protected abstract void finish(T t) throws Exception;
}
