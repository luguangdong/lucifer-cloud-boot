package com.lucifer.cloud.boot.ums.controller.menu;

import com.lucifer.cloud.boot.ums.util.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lucifer
 * @date 2023/12/10 15:55
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @GetMapping("/loadMenuList")
    public Map loadMenuList() {
        return JsonUtils.jsonCovertToObject(str, Map.class);
    }


    String str = "{\n" +
            "\t\"code\": 200,\n" +
            "\t\"data\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"icon\": \"home-filled\",\n" +
            "\t\t\t\"title\": \"首页\",\n" +
            "\t\t\t\"path\": \"/home\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"icon\": \"histogram\",\n" +
            "\t\t\t\"title\": \"数据展示\",\n" +
            "\t\t\t\"path\": \"/dataShow\",\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"/dataShow/echartsUse\",\n" +
            "\t\t\t\t\t\"title\": \"Echarts使用\",\n" +
            "\t\t\t\t\t\"icon\": \"menu\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"/dataShow/threeJsShow\",\n" +
            "\t\t\t\t\t\"title\": \"threeJS使用\",\n" +
            "\t\t\t\t\t\"icon\": \"menu\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"/dataShow/tableUse\",\n" +
            "\t\t\t\t\t\"title\": \"表格使用以及封装\",\n" +
            "\t\t\t\t\t\"icon\": \"menu\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"icon\": \"message-box\",\n" +
            "\t\t\t\"title\": \"组件管理\",\n" +
            "\t\t\t\"path\": \"/comp\",\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"/comp/puzzle\",\n" +
            "\t\t\t\t\t\"title\": \"拼图验证\",\n" +
            "\t\t\t\t\t\"icon\": \"menu\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"/comp/upload\",\n" +
            "\t\t\t\t\t\"title\": \"图片上传\",\n" +
            "\t\t\t\t\t\"icon\": \"menu\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"msg\": \"成功\"\n" +
            "}";



}
