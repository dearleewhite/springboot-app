package com.example.controller;

import com.example.vo.CommonResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-15 15:13
 **/

@RestController
@RequestMapping("/base/")
public class BaseController {

    @GetMapping("success")
    public CommonResultVo getMessage(){
        return CommonResultVo.success();
    }

    @GetMapping("messageSuccess")
    public CommonResultVo getMessageSuccess(){
        return CommonResultVo.success("success");
    }


    @GetMapping("error")
    public CommonResultVo getError(){
        return CommonResultVo.error();
    }

    @GetMapping("messageError")
    public CommonResultVo getMessageError(){
        return CommonResultVo.error("error");
    }



}
