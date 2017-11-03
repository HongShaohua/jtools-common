package com.hongshaohua.jtools.common.mht;

import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.message.DefaultMessageBuilder;
import org.apache.james.mime4j.stream.MimeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Aska on 2017/9/25.
 */
public class MhtParser {

    private static final Logger logger = LoggerFactory.getLogger(MhtParser.class);

    public static boolean parse(MhtParseAdapter adapter) {
        try {
            MimeConfig parserConfig = new MimeConfig.Builder()
                    .setMaxHeaderLen(-1)
                    .setMaxHeaderCount(-1)
                    .setMaxLineLen(-1)
                    .build();
            DefaultMessageBuilder builder = new DefaultMessageBuilder();
            builder.setMimeEntityConfig(parserConfig);

            InputStream mhtStream = new FileInputStream(adapter.mht());
            Message message = builder.parseMessage(mhtStream);

            if(message.getBody() instanceof Multipart) {
                Multipart multipart = (Multipart) message.getBody();
                List<Entity> parts = multipart.getBodyParts();
                adapter.parts(parts);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
