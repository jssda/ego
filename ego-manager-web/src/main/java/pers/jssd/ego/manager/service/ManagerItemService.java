package pers.jssd.ego.manager.service;

import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.beans.PictureResult;
import pers.jssd.ego.rpc.pojo.TbItem;

/**
 * 后台管理的服务接口
 *
 * @author jssdjing@gmail.com
 */
public interface ManagerItemService {

    /**
     * 完成商品信息的分页查询
     *
     * @param page 当前页
     * @param rows 每页多少条数据
     * @return 返回封装了分页信息的查询数据
     */
    PageResult<TbItem> findItem(Integer page, Integer rows);

    /**
     * 上架商品
     *
     * @param ids Long[] 需要上架的商品id
     * @return 返回是否上架成功的响应对象EgoResult
     */
    EgoResult reshelfItem(Long[] ids);

    /**
     * 下架商品
     *
     * @param ids Long[] 需要下架的商品id
     * @return 返回是否下架成功的响应对象EgoResult
     */
    EgoResult instockItem(Long[] ids);

    /**
     * 删除商品信息
     *
     * @param ids 需要删除的商品id
     * @return 返回是否删除商品的响应信息
     */
    EgoResult removeItem(Long[] ids);

    /**
     * 上传文件
     *
     * @param file 需要上传的文件
     * @return 返回是否上传成功的图片组件响应对象PictureResult
     */
    PictureResult uploadImage(MultipartFile file);

    /**
     * 新增商品信息
     *
     * @param item 商品信息
     * @param desc 商品的描述信息
     * @param itemParams 商品参数信息
     * @return 返回是否添加成功的响应对象EgoResult
     */
    EgoResult saveItem(TbItem item, String desc, String itemParams);

    /**
     * 更新商品信息
     *
     * @param item 需要更新的商品信息
     * @param desc 需要更新的商品描述信息
     * @param itemParams 商品的规格参数
     * @return 返回是否更新成功的响应对象EgoResult
     */
    EgoResult updateItem(TbItem item, String desc, String itemParams);
}
