package com.disparter.util;

import java.io.Serializable;
import java.util.List;

import com.disparter.hibernate.SortType;

public class QueryDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;
    
    private Integer pageSize;
    
    private String order;
    
    private SortType orderDirection;
    
    private Integer totalResults;
    
    private T filter;
    
    private List<T> list;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public SortType getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(SortType orderDirection) {
        this.orderDirection = orderDirection;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
