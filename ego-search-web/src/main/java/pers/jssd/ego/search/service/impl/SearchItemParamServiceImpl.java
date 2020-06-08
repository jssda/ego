package pers.jssd.ego.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.pojo.TbItemParamItem;
import pers.jssd.ego.rpc.service.ItemParamItemService;
import pers.jssd.ego.search.service.SearchItemParamService;
import pers.jssd.ego.utils.JsonUtil;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class SearchItemParamServiceImpl implements SearchItemParamService {
    
    private final ItemParamItemService itemParamItemService;
    
    @Autowired
    public SearchItemParamServiceImpl(ItemParamItemService itemParamItemService) {
        this.itemParamItemService = itemParamItemService;
    }
    
    @Override
    public String loadItemParamService(Long id) {
        TbItemParamItem itemParamItemById = itemParamItemService.findItemParamItemById(id);
        String result = null;
        if (itemParamItemById != null) {
            result = getItemParamData(itemParamItemById.getParamData());
        }
        return result;
    }
    
    private String getItemParamData(String paramData) {
        //解析json字符串
        List<Map> listMap = JsonUtil.jsonToList(paramData, Map.class);
        
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m1 : listMap) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">").append(m1.get("group")).append("</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for (Map m2 : list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">").append(m2.get("k")).append("</td>\n");
                sb.append("            <td>" + m2.get("v") + "</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        // 返回html片段
        return sb.toString();
        
    }
    
}
