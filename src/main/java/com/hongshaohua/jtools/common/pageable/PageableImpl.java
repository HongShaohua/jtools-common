package com.hongshaohua.jtools.common.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by shaoh on 2017/4/26.
 */
public class PageableImpl implements Pageable {

    private static final int FIRST_PAGE_NUMBER = 1;

    /**
     * 默认第1页开始
     */
    private int pageNumber = FIRST_PAGE_NUMBER;
    /**
     * 默认一页50条
     */
    private int pageSize = 50;
    /**
     * 默认排序条件
     */
    private Sort sort;

    public PageableImpl(int pageNumber, int pageSize, Sort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public PageableImpl(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageableImpl(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PageableImpl() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (getPageNumber() - FIRST_PAGE_NUMBER) * getPageSize();
    }

    public Sort getSort() {
        return sort;
    }

    public Pageable next() {
        int nextPageNumber = this.getPageNumber() + 1;
        return new PageableImpl(nextPageNumber, this.getPageSize(), this.getSort());
    }

    public Pageable previousOrFirst() {
        int prePageNumber = this.getPageNumber() - 1;
        if(prePageNumber < FIRST_PAGE_NUMBER) {
            prePageNumber = FIRST_PAGE_NUMBER;
        }
        return new PageableImpl(prePageNumber, this.getPageSize(), this.getSort());
    }

    public Pageable first() {
        return new PageableImpl(FIRST_PAGE_NUMBER, this.getPageSize());
    }

    public boolean hasPrevious() {
        int prePageNumber = this.getPageNumber() - 1;
        if(prePageNumber < FIRST_PAGE_NUMBER) {
            return false;
        }
        return true;
    }
}
