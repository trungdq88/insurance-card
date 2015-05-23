package com.fpt.mic.micweb.framework;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
public class JspPage extends ResponseObject {
    private static final String VIEWS_DIR = "WEB-INF/views/";
    private String path;

    public JspPage(String path) {
        this.path = VIEWS_DIR + path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
