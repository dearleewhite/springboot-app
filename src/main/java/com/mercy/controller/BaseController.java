package com.mercy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mercy.entity.Type;
import com.mercy.entity.tools.Pager;
import com.mercy.vo.TypeVo;
import com.mercy.service.TypeService;
import com.mercy.utils.BeanUtils;
import com.mercy.utils.PageUtil;
import com.mercy.vo.BusinessPage;
import com.mercy.vo.CommonResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("listWithTree")
    public CommonResultVo<List<Type>> listWithTree(@RequestParam("loadChildren") Boolean loadChildren,
                                                   @RequestParam("showLevel") Integer showLevel,
                                                   @RequestParam("name") String name){
       return CommonResultVo.success(typeService.listWithTree(loadChildren, showLevel, name));
    }
    @GetMapping("listWithTreePage")
    public CommonResultVo<BusinessPage<TypeVo>> listWithTreePage(@RequestParam("loadChildren") Boolean loadChildren,
                                                               @RequestParam("showLevel") Integer showLevel,
                                                               @RequestParam("name") String name,
                                                               @ModelAttribute Pager pager){
        IPage<Type> iPage = typeService.listWithTreePage(loadChildren, showLevel, name, pager);
        return CommonResultVo.success(PageUtil.convert(iPage, o -> BeanUtils.copyProperties(o, TypeVo.class)));
    }



}
