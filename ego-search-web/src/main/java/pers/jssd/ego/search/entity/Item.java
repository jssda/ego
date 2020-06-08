package pers.jssd.ego.search.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 前台搜索出来的商品显示封装
 *
 * @author jssdjing@gmail.com
 */
public class Item {
    
    @Field("id")
    private String id;
    @Field("title")
    private String title;
    @Field("sell_point")
    private String sell_point;
    @Field("price")
    private Long price;
    @Field("image")
    private String image;
    @Field("category_name")
    private String category_name;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSell_point() {
        return sell_point;
    }
    
    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }
    
    public Long getPrice() {
        return price;
    }
    
    public void setPrice(Long price) {
        this.price = price;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getCategory_name() {
        return category_name;
    }
    
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    
    public String[] getImages(){
        return image.split(",");
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", sell_point='" + sell_point + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
