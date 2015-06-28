package com.fpt.mic.micweb.framework;

import java.util.List;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 6/28/15.
 */
public class Paginator {
    /**
     * Number of item displayed per page
     */
    int itemPerPage = 10;


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

    /**
     * Get current page number, start from 1
     * @return
     */
    public int getCurrentPage(String pageStr) {
        int page;
        if (pageStr == null || pageStr.isEmpty()) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                page = 1;
            }
        }
        return page;
    }

    /**
     * Get items displayed on current page
     * @return
     */
    public List getItemsOnCurrentPage(String pageStr) {
        int currentPage = getCurrentPage(pageStr);
        // Page 1: 0 - 9
        // Page 2: 10 - 19
        // Page 3: 20 - 29
        int offset = (currentPage - 1) * itemPerPage;
        return getItemsCallback.getItems(offset, itemPerPage);
    }

    public int getPageSize() {
        return (int) Math.ceil(getItemSizeCallback.getItemSize() * 1.0 / itemPerPage);
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

    /// Interfaces

    public interface IGetItems {
        List getItems(int offset, int count);
    }
    public interface IGetItemSize {
        Long getItemSize();
    }
}
