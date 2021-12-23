package com.mercy.controller;

import com.mercy.annotation.Access;
import com.mercy.entity.Type;
import com.mercy.service.TypeService;
import com.mercy.vo.CommonResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Huwl
 * @Date: 2020-12-15 15:13
 **/

@RestController
@RequestMapping("/base/")
public class BaseController {

    @Autowired
    private TypeService typeService;

    @GetMapping("admin")
    @Access(authorities = {"admin"})
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

    @GetMapping("listWithTree")
    public CommonResultVo<List<Type>> listWithTree(@RequestParam("loadChildren") Boolean loadChildren,
                                                   @RequestParam("showLevel") Integer showLevel,
                                                   @RequestParam("name") String name){
       return CommonResultVo.success(typeService.listWithTree(loadChildren, showLevel, name));
    }



}
