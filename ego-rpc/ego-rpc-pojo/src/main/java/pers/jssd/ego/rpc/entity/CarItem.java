package pers.jssd.ego.rpc.entity;

import pers.jssd.ego.rpc.pojo.TbItem;

import java.io.Serializable;

/**
 * 购物车实体类
 *
 * @author jssdjing@gmail.com
 */
public class CarItem implements Serializable {
    
    /**
     * 购买的对象
     */
    private TbItem item;
    
    /**
     * 购买的数量
     */
    private Integer num;
    
    public TbItem getItem() {
        return item;
    }
    
    public void setItem(TbItem item) {
        this.item = item;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
}
