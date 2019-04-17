package com.kwdz.blog.web.common.error;


import com.kwdz.blog.api.common.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 统一异常处理(针对控制层)
 */
@Slf4j
@RestController
@RequestMapping("${error.path:/error}")
public class GlobalErrorController implements ErrorController {

    @Autowired
    ErrorBuilder errorInfoBuilder;

    /**
     * 返回错误页
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("common/error");
        Throwable ex = errorInfoBuilder.getError(request);
        mv.addObject("reason", ex.getMessage());
        mv.addObject("status", errorInfoBuilder.getHttpStatus(request).value());
        mv.addObject("uri", errorInfoBuilder.getErrorUri(request));
        mv.addObject("date", new Date());
        log.error("统一异常处理：", ex);
        return mv;
    }

    /**
     * 返回错误信息
     */
    @RequestMapping
    public ResultModel errorJson(HttpServletRequest request) {
        Throwable ex = errorInfoBuilder.getError(request);
        log.error("统一异常处理：", ex);
        return ResultModel.of(null, false, "统一异常处理：" + ex.getMessage());
    }

    //获取默认路径
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
