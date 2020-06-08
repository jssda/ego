package pers.jssd.ego.beans;

import java.io.Serializable;

/**
 * 上传文件控件响应信息
 *
 * @author jssdjing@gmail.com
 */
public class PictureResult implements Serializable {

    /**
     * 是否上传成功 1: 失败, 0成功
     */
    private Integer error;

    /**
     * 上传成功之后的图片回显地址
     */
    private String url;

    /**
     * 响应到客户端的提示信息
     */
    private String massage;


    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
