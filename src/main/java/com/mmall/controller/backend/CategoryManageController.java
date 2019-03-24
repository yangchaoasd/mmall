package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.CategoryService;
import com.mmall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 18:43
 **/
@Api(value = "/manage/category", description = "后台管理分类接口")
@RestController
@RequestMapping(value = "/manage/category", method = {RequestMethod.GET, RequestMethod.POST})
public class CategoryManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "添加分类")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryName", value = "分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "父类id", defaultValue = "0", dataType = "int")
    })
    @RequestMapping("/add_category")
    public ServerResponse addCategory(HttpServletRequest request, @RequestParam String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {

        return categoryService.addCategory(categoryName, parentId);
    }

    @ApiOperation(value = "设置分类名称")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "categoryId", value = "分类Id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "categoryName", value = "分类名称", required = true, dataType = "String")
    })
    @RequestMapping("/set_category_name")
    public ServerResponse setCategoryName(HttpServletRequest request, @RequestParam int categoryId, @RequestParam String categoryName) {

        return categoryService.updateCategoryName(categoryId, categoryName);
    }

    @ApiOperation(value = "获取子节点分类信息，不进行递归")
    @ApiImplicitParam(paramType = "query", name = "categoryId", value = "分类Id", defaultValue = "0", dataType = "int")
    @RequestMapping("/get_category")
    public ServerResponse getChildrenParallelCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {

        return categoryService.getChildrenParallelCategory(categoryId);
    }

    @ApiOperation(value = "获取当前节点和子节点的id，递归")
    @ApiImplicitParam(paramType = "query", name = "categoryId", value = "分类Id", defaultValue = "0", dataType = "int")
    @RequestMapping("/get_deep_category")
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {

        return categoryService.selectCategoryAndChildrenById(categoryId);
    }
}
