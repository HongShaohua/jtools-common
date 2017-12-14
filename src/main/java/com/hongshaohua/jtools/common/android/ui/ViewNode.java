package com.hongshaohua.jtools.common.android.ui;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Aska on 2017/10/19.
 */

@XmlRootElement(name="node")
public class ViewNode {

    @XmlAttribute(name="index")
    private int index;

    @XmlAttribute(name="text")
    private String text;

    @XmlAttribute(name="resource-id")
    private String resourceId;

    @XmlAttribute(name="class")
    private String cls;

    @XmlAttribute(name="package")
    private String pkg;

    @XmlAttribute(name="content-desc")
    private String contentDesc;

    @XmlAttribute(name="checkable")
    private boolean checkable;

    @XmlAttribute(name="checked")
    private boolean checked;

    @XmlAttribute(name="clickable")
    private boolean clickable;

    @XmlAttribute(name="enabled")
    private boolean enabled;

    @XmlAttribute(name="focusable")
    private boolean focusable;

    @XmlAttribute(name="focused")
    private boolean focused;

    @XmlAttribute(name="scrollable")
    private boolean scrollable;

    @XmlAttribute(name="long-clickable")
    private boolean longClickable;

    @XmlAttribute(name="password")
    private boolean password;

    @XmlAttribute(name="selected")
    private boolean selected;

    @XmlAttribute(name="bounds")
    private String boundsStr;
    private ViewBounds bounds;

    @XmlElement(name="node")
    private List<ViewNode> nodes;

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getCls() {
        return cls;
    }

    public String getPkg() {
        return pkg;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isClickable() {
        return clickable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isFocusable() {
        return focusable;
    }

    public boolean isFocused() {
        return focused;
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public boolean isLongClickable() {
        return longClickable;
    }

    public boolean isPassword() {
        return password;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getBoundsStr() {
        return boundsStr;
    }

    public ViewBounds getBounds() {
        return bounds;
    }

    public List<ViewNode> getNodes() {
        return nodes;
    }

    void fix() {
        this.bounds = ViewBounds.create(this.boundsStr);
        if(this.nodes != null) {
            for(ViewNode node : this.nodes) {
                node.fix();
            }
        }
    }

    ViewNode searchText(String text) {
        if(this.text.equals(text)) {
            return this;
        }
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

    ViewNode searchResourceId(String resourceId) {
        if(this.resourceId.equals(resourceId)) {
            return this;
        }
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
        if(this.cls.equals(cls) && this.contentDesc.equals(contentDesc)) {
            return this;
        }
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
}
