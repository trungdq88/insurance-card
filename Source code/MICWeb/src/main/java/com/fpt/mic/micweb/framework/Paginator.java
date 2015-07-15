package com.fpt.mic.micweb.framework;

import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/28/15.
 */
public class Paginator {
    /**
     * Tag for this paginator. Use when we have more than 1 list in a single page.
     */
    String tag = "";

    /**
     * Number of item displayed per page
     */
    int itemPerPage = 1;


    /**
     * Callback to get item from data source
     */
    IGetItems getItemsCallback;
    /**
     * Callback to get page size
     */
    IGetItemSize getItemSizeCallback;

    public Paginator() {
    }

    public Paginator(String tag) {
        this.tag = tag;
    }

    /**
     * Get current page number, start from 1
     * @return
     */
    public int getCurrentPage(String pageStr, String tag) {
        // If not current tag, display page 1
        if (!tag.equals(this.tag)) return 1;

        int page;
        if (pageStr == null || pageStr.isEmpty()) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(pageStr);
                if (page == 0) page = 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                page = 1;
            }
        }
        return page;
    }

    // @Overload
    public int getCurrentPage(String pageStr) {
        return getCurrentPage(pageStr, "");
    }

    /**
     * Get items displayed on current page
     * @return
     */
    public List getItemsOnCurrentPage(String pageStr, String tag) {
        int currentPage = getCurrentPage(pageStr, tag);
        // Page 1: 0 - 9
        // Page 2: 10 - 19
        // Page 3: 20 - 29
        int offset = (currentPage - 1) * itemPerPage;
        return getItemsCallback.getItems(offset, itemPerPage);
    }

    // @Overload
    public List getItemsOnCurrentPage(String pageStr) {
        return getItemsOnCurrentPage(pageStr, "");
    }

    public int getPageSize() {
        int size = (int) Math.ceil(getItemSizeCallback.getItemSize() * 1.0 / itemPerPage);
        if (size == 0) size = 1;
        return size;
    }

    public Long getItemSize() {
        return getItemSizeCallback.getItemSize();
    }

    /// Default getters, setters

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public IGetItems getGetItemsCallback() {
        return getItemsCallback;
    }

    public void setGetItemsCallback(IGetItems getItemsCallback) {
        this.getItemsCallback = getItemsCallback;
    }

    public IGetItemSize getGetItemSizeCallback() {
        return getItemSizeCallback;
    }

    public void setGetItemSizeCallback(IGetItemSize getItemSizeCallback) {
        this.getItemSizeCallback = getItemSizeCallback;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /// Interfaces

    public interface IGetItems {
        List getItems(int offset, int count);
    }
    public interface IGetItemSize {
        Long getItemSize();
    }
}
