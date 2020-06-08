package pers.jssd.ego.beans;

import java.io.Serializable;

/**
 * 商品的上架, 下架, 删除的响应信息封装
 *
 * @author jssdjing@gmail.com
 */
public class EgoResult implements Serializable {

    /**
     * 响应状态码
     */
    private Integer status;


    /**
     * 响应内容
     */
    private Object data;


    /**
     * 响应信息
     */
    private String msg;

    public EgoResult(Integer status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public EgoResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public EgoResult(Object data) {
        this(200, "ok");
        this.data = data;
    }

    public EgoResult() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回成功的响应信息
     * <pre>
     *     响应状态码为200, 响应消息为ok, 响应内容为null
     * </pre>
     *
     * @return 返回响应封装对象EgoResult
     */
    public static EgoResult ok() {
        return new EgoResult(null);
    }
}
