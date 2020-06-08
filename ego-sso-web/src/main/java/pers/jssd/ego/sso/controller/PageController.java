package pers.jssd.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class PageController {
    
    @RequestMapping("/{path}")
    public String showPage(@PathVariable String path,
                           @RequestParam(required = false) String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return path;
    }
    
}
