package com.convenientbuy.common.pojo;

import java.util.List;

/**
 * Created by bonismo@hotmail.com
 * 下午10:04 on 16/8/18.
 */
public class EUDataGridResult {

    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
