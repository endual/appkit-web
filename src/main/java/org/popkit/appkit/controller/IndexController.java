package org.popkit.appkit.controller;

import org.apache.commons.collections.CollectionUtils;
import org.popkit.appkit.entity.BasicDo;
import org.popkit.appkit.entity.User;
import org.popkit.appkit.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guobao.jiang
 * @date 3/13/15
 * @time 10:01 AM
 */
@Controller
public class IndexController {
    @Autowired
    private DemoService demoService;

    /**
     * Static list of users to simulate Database
     */
    private static List<User> userList = new ArrayList<User>();

    //Initialize the list with some data for index screen
    static {
        userList.add(new User("用户名", "地址"));
    }

    /**
     * Saves the static list of users in model and renders it
     * via freemarker template.
     *
     * @param model
     * @return The index view (FTL)
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
        List<BasicDo> allValues = demoService.queryAllUsersInfo();
        List<User> userListInfo = new ArrayList<User>();

        if (CollectionUtils.isNotEmpty(allValues)) {
            int index = 0;
            for (BasicDo item : allValues) {
                userListInfo.add(new User(item));
            }
        }

        model.addAttribute("userList", userListInfo);
        model.addAttribute("pageTitle", "示例页面");
        return "index";
    }

    /**
     * Add a new user into static user lists and display the
     * same into FTL via redirect
     *
     * @param user
     * @return Redirect to /index page to display user list
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("user") User user) {

        if (null != user && null != user.getFirstname()
                && null != user.getLastname() && !user.getFirstname().isEmpty()
                && !user.getLastname().isEmpty()) {
            demoService.insert(user.getFirstname(), user.getLastname());
            synchronized (userList) {
                userList.add(user);
            }

        }

        return "redirect:index.html";
    }

}
