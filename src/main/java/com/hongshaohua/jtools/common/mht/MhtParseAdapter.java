package com.hongshaohua.jtools.common.mht;

import org.apache.commons.io.FileUtils;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.SingleBody;
import org.apache.james.mime4j.dom.field.ContentLocationField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.stream.Field;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Aska on 2017/9/25.
 */
public abstract class MhtParseAdapter {

    private File mht;

    public MhtParseAdapter(File mht) {
        this.mht = mht;
    }

    public File mht() {
        return mht;
    }

    public void parts(List<Entity> parts) throws Exception {
        for(Entity part : parts) {
            this.part(part);
        }
    }

    public void part(Entity part) throws Exception {
        Field type = part.getHeader().getField("Content-Type");

        Field location = part.getHeader().getField("Content-Location");

        Body body = part.getBody();

        this.part(type, location, body);
    }

    public void part(Field contentType, Field contentLocation, Body body) throws Exception {
        ContentTypeField type = null;
        if(contentType != null && contentType instanceof ContentTypeField) {
            type = (ContentTypeField) contentType;
        }

        ContentLocationField location = null;
        if(contentLocation != null && contentLocation instanceof ContentLocationField) {
            location = (ContentLocationField) contentLocation;
        }

        SingleBody singleBody = null;
        if(body != null && body instanceof SingleBody) {
            singleBody = (SingleBody) body;
        }

        this.part(type, location, singleBody);
    }

    public void part(ContentTypeField contentType, ContentLocationField contentLocation, SingleBody body) throws Exception {
        String mimeType = null;
        String subType = null;
        if(contentType != null) {
            mimeType = contentType.getMimeType();
            subType = contentType.getSubType();
        }

        String location = null;
        if(contentLocation != null) {
            location = contentLocation.getLocation();
        }

        InputStream inputStream = null;
        if(body != null) {
            inputStream = body.getInputStream();
        }

        this.part(mimeType, subType, location, inputStream);
    }

    public abstract void part(String mimeType, String subType, String location, InputStream inputStream) throws Exception;

    protected void write(InputStream inputStream, File dstFile) throws Exception {
        FileUtils.copyInputStreamToFile(inputStream, dstFile);
    }
}
