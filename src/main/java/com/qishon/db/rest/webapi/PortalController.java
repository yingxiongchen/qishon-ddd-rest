package com.qishon.db.rest.webapi;

import com.qishon.db.rest.domain.application.DbRestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * DB RESTful Usage
 *
 * @author Hero
 */
@Controller
public class PortalController {
    @Autowired
    private DbRestManager dbRestManager;

    //    @GetMapping("/usage")
    @GetMapping(value = {"/usage", "/"}) //一个方法对应多个URL
    //    @ResponseBody //如果是返回文本内容是直接作为http的Body内容才用这个注解，如果是返回模板则不要用这个注解，而且模板不需要(而且不能)写扩展名
    public String usage(Model model) {
        model.addAttribute("usageText", dbRestManager.usage());
        model.addAttribute("hostURL", dbRestManager.url());
        return "usageTemplates";
    }


    /*
     *  RESTful API
     * (1)只接收Post 请求
     * (2)接受URL格式：
      * (2.1): /db/rest/{tableName}/{action}/{key}
      * (2.2): /db/rest/{tableName}/{action}?key=xxx
     */
    @ResponseBody
//    @RequestMapping(value = {"/db/rest/{tableName}/{action}/{key}", "/db/rest/{tableName}/{action}**"}, method = RequestMethod.POST)
    @RequestMapping(value = {"/db/rest/{tableName}/{action}/{key}"})
    public String restApi(
            @PathVariable("tableName") String tableName,
            @PathVariable("action") String action,
            @PathVariable("key") String key) {
        return "只能是Post请求,@PathVariable:" + ",tableName=" + tableName
                + ",action=" + action
                + ",key=" + key;
    }

//    @ResponseBody
//    @RequestMapping(value = {"/db/rest/{tableName}/{action}**"})
//    public String restApi2(
//            @PathVariable("tableName") String tableName,
//            @PathVariable("action") String action,
//            @RequestParam(value = "key", required = false, defaultValue = "defaultValue") String key) {
//        return "只能是Post请求,@PathVariable:" + ",tableName=" + tableName
//                + ",action=" + action
//                + ",@RequestParam:"
//                + "key=" + key;
//    }

}
