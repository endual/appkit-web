package org.popkit.appkit.controller;

import org.popkit.appkit.entity.BasicDo;
import org.popkit.appkit.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author guobao.jiang
 * @date 3/12/15
 * @time 11:27 PM
 */
@Controller
public class DemoController {
    public DemoController() {}

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String show(@ModelAttribute("model")ModelMap modelMap) {
        String info = "Hello world!";
        try {
            List<BasicDo> allValues = demoService.queryAllUsersInfo();
            info += " size:" + allValues.size();
            //demoService.insert("aborn", "Shanghai");
        }catch (Exception e) {
            // do nothing
        }

        modelMap.addAttribute("info", info);
        modelMap.addAttribute("pageTitle", "示例页面");
        modelMap.addAttribute("class", this.getClass().toString());
        return "demo";
    }
}
