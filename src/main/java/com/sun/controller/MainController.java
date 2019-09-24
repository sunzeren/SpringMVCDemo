package com.sun.controller;

import com.sun.pojo.ValidatorBean;
import com.sun.service.CompanyService;
import com.sun.service.OtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author by Sun, Date on 2018/8/30.
 * PS: Not easy to write code, please indicate.
 */
@RestController
@Api("SpringBoot测试控制器")
public class MainController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CompanyService companyService;
    @Autowired
    OtherService otherService;

    @RequestMapping(value = "/testForJDBC", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有Company", notes = "返回模型和视图")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        List list = companyService.selectAllCompany();
        mv.addObject("list", list);
        mv.setViewName("login");

        /**调用🐖*/
        otherService.getYhy();
        return mv;
    }


    @RequestMapping(value = "/bean", method = RequestMethod.GET)
    @ApiOperation(value = "测试Validator", notes = "测试校验")
    public void validatorBeanTest(@Validated ValidatorBean bean) {
        System.out.println("bean = " + bean);
    }
}
