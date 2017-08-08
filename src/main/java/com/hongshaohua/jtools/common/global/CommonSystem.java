package com.hongshaohua.jtools.common.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aska on 2017/8/8.
 */
public class CommonSystem {

    private static final Logger logger = LoggerFactory.getLogger(CommonSystem.class);

    private static CommonSystem instance = null;

    public static CommonSystem getInstance() {
        if(instance == null) {
            instance = new CommonSystem();
        }
        return instance;
    }

    private CommonSystem() {
    }

    public static long currentSystemTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
