package pers.jssd.ego.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 封装前台大广告图片实体类
 *
 * @author jssdjing@gmail.com
 */
public class BigPicture implements Serializable {
    
    @JsonProperty("srcB")
    private String srcB;
    @JsonProperty("height")
    private Integer height = 240;
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("width")
    private Integer width = 670;
    @JsonProperty("src")
    private String src;
    @JsonProperty("widthB")
    private Integer widthB = 550;
    @JsonProperty("href")
    private String href;
    @JsonProperty("heightB")
    private Integer heightB = 240;
    
    public String getSrcB() {
        return srcB;
    }
    
    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }
    
    public Integer getHeight() {
        return height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public String getAlt() {
        return alt;
    }
    
    public void setAlt(String alt) {
        this.alt = alt;
    }
    
    public Integer getWidth() {
        return width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public String getSrc() {
        return src;
    }
    
    public void setSrc(String src) {
        this.src = src;
    }
    
    public Integer getWidthB() {
        return widthB;
    }
    
    public void setWidthB(Integer widthB) {
        this.widthB = widthB;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public Integer getHeightB() {
        return heightB;
    }
    
    public void setHeightB(Integer heightB) {
        this.heightB = heightB;
    }
}
