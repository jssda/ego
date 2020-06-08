package pers.jssd.ego.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 前台商品类目节点
 *
 * @author jssdjing@gmail.com
 */
public class CatNode implements Serializable {
    //@JsonProperty 指定将 java 对象转化为 json 格式的时候，对应的 key
    
    /**
     * 商品类目对应的url
     */
    @JsonProperty(value = "u")
    private String url;
    
    /**
     * 商品类目的名字
     */
    @JsonProperty(value = "n")
    private String name;
    
    /**
     * 类目数据
     */
    @JsonProperty(value="i")
    private List<?> list;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<?> getList() {
        return list;
    }
    
    public void setList(List<?> list) {
        this.list = list;
    }
}
