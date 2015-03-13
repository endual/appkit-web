package org.popkit.appkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author guobao.jiang
 * @date 3/12/15
 * @time 11:27 PM
 */
@Controller
public class DemoController {
    public DemoController() {}

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String show(@ModelAttribute("model")ModelMap modelMap) {
        modelMap.addAttribute("info", "Hello World!");
        modelMap.addAttribute("class", this.getClass().toString());
        return "demo";
    }
}
