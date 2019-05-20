package com.kwdz.blog.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/5/20 0:30
 */

@Controller
@RequestMapping("/edit/")
public class EditorController {

    @GetMapping("wang")
    public String wang() {
        return "wang";
    }

    @GetMapping("ueditor")
    public String ueditor() {
        return "ueditor";
    }
}
