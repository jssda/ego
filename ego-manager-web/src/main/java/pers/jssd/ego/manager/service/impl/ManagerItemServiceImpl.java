package pers.jssd.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ego.beans.EgoResult;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.beans.PictureResult;
import pers.jssd.ego.manager.service.ManagerItemService;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.pojo.TbItemDesc;
import pers.jssd.ego.rpc.pojo.TbItemParamItem;
import pers.jssd.ego.rpc.service.ItemService;
import pers.jssd.ego.utils.FtpUtil;
import pers.jssd.ego.utils.IDUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    /**
     * 操作商品的远程服务
     */
    private final ItemService itemService;

    @Value("${FTP_HOST}")
    private String ftpHost;

    @Value("${FTP_PORT}")
    private Integer ftpPort;

    @Value("${FTP_USERNAME}")
    private String ftpUserName;

    @Value("${FTP_PASSWORD}")
    private String ftpPassword;

    @Value("${WORK_DIR}")
    private String workDir;

    @Value("${IMAGE_HTTP_PATH}")
    private String imageHttpPath;

    @Autowired
    public ManagerItemServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public PageResult<TbItem> findItem(Integer page, Integer rows) {
        return itemService.findItem(page, rows);
    }

    @Override
    public EgoResult reshelfItem(Long[] ids) {
        return itemService.updateItemStatus(Arrays.asList(ids), true);
    }

    @Override
    public EgoResult instockItem(Long[] ids) {
        return itemService.updateItemStatus(Arrays.asList(ids), false);
    }

    @Override
    public EgoResult removeItem(Long[] ids) {
        return itemService.removeItem(Arrays.asList(ids));
    }

    @Override
    public PictureResult uploadImage(MultipartFile file) {
        boolean flag = false;
        String fileName = IDUtils.genImageName();
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = fileName + extensionName;

            flag = FtpUtil.picUpload(ftpHost, ftpPort, ftpUserName, ftpPassword, workDir, fileName, inputStream);
            System.out.println("flag = " + flag);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PictureResult result;
        result = new PictureResult();
        if (flag) {
            result.setError(0);
            result.setUrl(imageHttpPath + "/" + fileName);
            result.setMassage("ok");
        } else {
            result.setError(1);
            result.setUrl("error");
            result.setMassage("error");
        }

        return result;
    }

    @Override
    public EgoResult saveItem(TbItem item, String desc, String itemParams) {
        // 封装前台没有传递过来的item数据
        Long id = IDUtils.genItemId();
        item.setId(id);
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);

        // 封装itemDesc数据
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);


        // 调用远程服务对象, 添加数据
        return itemService.insertItem(item, itemDesc, itemParamItem);
    }


    @Override
    public EgoResult updateItem(TbItem item, String desc, String itemParams) {

        // 封装前台没有传递过来的item数据
        Date date = new Date();
        item.setUpdated(date);

        // 封装itemDesc数据
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setUpdated(date);
        itemParamItem.setCreated(date);
        
        // 调用远程服务对象, 添加数据
        return itemService.updateItem(item, itemDesc, itemParamItem);
    }
}
