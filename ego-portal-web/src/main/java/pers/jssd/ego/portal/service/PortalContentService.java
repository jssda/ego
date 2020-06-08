package pers.jssd.ego.portal.service;

/**
 * 前台内容服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface PortalContentService {
    
    /**
     * 通过内容类别, 加载所有内容信息
     *
     * @return 返回内容信息所封装而成的json串
     * @param categoryId 加载内容的id
     */
    String loadContentByCid(Long categoryId);

}
