package pers.jssd.ego.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 前台商品类目响应对象. 会转换成json字符串响应
 *
 * @author jssdjing@gmail.com
 */
public class CatResult implements Serializable {

    private List<?> data;
    
    public List<?> getData() {
        return data;
    }
    
    public void setData(List<?> data) {
        this.data = data;
    }
}
