package com.hongshaohua.jtools.common.android.ui;

import com.hongshaohua.jtools.common.xml.XmlUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Aska on 2017/10/19.
 */
@XmlRootElement(name="hierarchy")
public class ViewHierarchy {

    @XmlAttribute(name="rotation")
    private int rotation;

    @XmlElement(name="node")
    private List<ViewNode> nodes;

    public int getRotation() {
        return rotation;
    }

    public List<ViewNode> getNodes() {
        return nodes;
    }

    public ViewNode searchText(String text) {
        if(this.nodes != null) {
            for(ViewNode node : this.nodes) {
                ViewNode dst = node.searchText(text);
                if(dst != null) {
                    return dst;
                }
            }
        }
        return null;
    }

    public ViewNode searchResourceId(String resourceId) {
        if(this.nodes != null) {
            for(ViewNode node : this.nodes) {
                ViewNode dst = node.searchResourceId(resourceId);
                if(dst != null) {
                    return dst;
                }
            }
        }
        return null;
    }

    public ViewNode searchClsAndContentDest(String cls, String contentDesc) {
        if(this.nodes != null) {
            for(ViewNode node : this.nodes) {
                ViewNode dst = node.searchClsAndContentDest(cls, contentDesc);
                if(dst != null) {
                    return dst;
                }
            }
        }
        return null;
    }

    public static ViewHierarchy create(String str) {
        ViewHierarchy hierarchy = XmlUtils.readStr(str, ViewHierarchy.class);
        if(hierarchy.nodes != null) {
            for(ViewNode node : hierarchy.nodes) {
                node.fix();
            }
        }
        return hierarchy;
    }
}
