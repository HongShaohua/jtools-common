package com.hongshaohua.jtools.common.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;

/**
 * Created by Aska on 2017/10/19.
 */
public class XmlUtils {

    private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> T readStr(String str, Class<T> cls) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(cls);

            StringReader sr = new StringReader(str);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T)unmarshaller.unmarshal(sr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            t = null;
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <T> T readFile(String path, Class<T> cls) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T)unmarshaller.unmarshal(new File(path));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            t = null;
        }
        return t;
    }
}
