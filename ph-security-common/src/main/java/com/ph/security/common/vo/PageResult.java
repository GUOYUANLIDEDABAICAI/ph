package com.ph.security.common.vo;

import java.util.List;

public class PageResult<T> {
    int total;
    List<T> rows;

    public PageResult(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    PageResult<T> total(int total){
        this.total = total;
        return this;
    }
    PageResult<T> total(List<T> rows){
        this.rows = rows;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
