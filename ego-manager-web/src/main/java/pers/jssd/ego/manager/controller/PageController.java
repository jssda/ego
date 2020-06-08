package pers.jssd.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jssdjing@gmail.com
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{path}")
    public String showPage(@PathVariable String path) {
        return path;
    }
}
