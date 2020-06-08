package pers.jssd.ego.search.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 封装响应到前台数据模型
 *
 * @author jssdjing@gmail.com
 */
public class SearchResult {
    
    /**
     * 查询到的前台商品集合
     */
    private List<Item> list;
    
    /**
     * 当前页
     */
    private Integer page;
    
    /**
     * 最大页
     */
    @JsonProperty("maxpage")
    private Long maxPage;
    
    /**
     * 总页数
     */
    private Long total;
    
    public List<Item> getList() {
        return list;
    }
    
    public void setList(List<Item> list) {
        this.list = list;
    }
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Long getMaxPage() {
        return maxPage;
    }
    
    public void setMaxPage(Long maxPage) {
        this.maxPage = maxPage;
    }
    
    public Long getTotal() {
        return total;
    }
    
    public void setTotal(Long total) {
        this.total = total;
    }
}
