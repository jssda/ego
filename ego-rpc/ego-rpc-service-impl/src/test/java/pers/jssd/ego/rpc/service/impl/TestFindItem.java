package pers.jssd.ego.rpc.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.jssd.ego.beans.PageResult;
import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.rpc.service.ItemService;

import java.util.List;

/**
 * @author jssdjing@gmail.com
 */
public class TestFindItem {

    @Test
    @Ignore
    public void findItemTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-dubbo.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-tx.xml"
        );

        ItemService bean = applicationContext.getBean(ItemService.class);
        System.out.println("bean = " + bean);
        PageResult<TbItem> item = bean.findItem(1, 10);
        List<TbItem> rows = item.getRows();
        for (TbItem row : rows) {
            System.out.println("row = " + row);
        }
    }

}
