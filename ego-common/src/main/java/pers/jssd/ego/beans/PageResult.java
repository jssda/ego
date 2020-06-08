package pers.jssd.ego.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
public class PageResult<T> implements Serializable {

    private List<T> rows;
    private Long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public PageResult() {
    }

    public PageResult(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }
}
