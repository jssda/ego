import pers.jssd.ego.rpc.pojo.TbItem;
import pers.jssd.ego.utils.JsonUtil;

/**
 * @author jssdjing@gmail.com
 */
public class TestJackson {
    
    public static void main(String[] args) {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setImage("aaaaa,bbbbb");
        item.setNum(999);
    
        System.out.println("item = " + item);
        String s = JsonUtil.objectToJson(item);
        System.out.println("s = " + s);
    
        item = JsonUtil.jsonToPojo(s, TbItem.class);
        System.out.println("item = " + item);
    }
    
}
