package pers.jssd.ego.manager.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.jssd.ego.beans.PictureResult;
import pers.jssd.ego.manager.service.ManagerItemService;

/**
 * 上传图片控制器
 *
 * @author jssdjing@gmail.com
 */
@Controller
@RequestMapping("/pic")
public class ItemPicController {

    private final ManagerItemService managerItemService;

    public ItemPicController(ManagerItemService managerItemService) {
        this.managerItemService = managerItemService;
    }

    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public PictureResult picUpload(@RequestParam("uploadFile") MultipartFile uploadFile) {
        return managerItemService.uploadImage(uploadFile);
    }

}
