package pers.jssd.ego.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jssd.ego.rpc.service.ItemDescService;
import pers.jssd.ego.search.service.SearchItemDescService;

/**
 * @author jssdjing@gmail.com
 */
@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {
    
    private final ItemDescService itemDescService;
    
    @Autowired
    public SearchItemDescServiceImpl(ItemDescService itemDescService) {
        this.itemDescService = itemDescService;
    }
    
    @Override
    public String loadItemDescService(Long id) {
        return itemDescService.getItemDesc(id).getItemDesc();
    }
}
