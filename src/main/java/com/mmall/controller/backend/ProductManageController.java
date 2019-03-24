package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.model.Product;
import com.mmall.service.FileService;
import com.mmall.service.ProductService;
import com.mmall.service.UserService;
import com.mmall.util.PropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 20:14
 **/
@Api(value = "/manage/product", description = "后台管理产品接口")
@RestController
@RequestMapping(value = "/manage/product", method = {RequestMethod.GET, RequestMethod.POST})
public class ProductManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "添加或者更新产品")
    @RequestMapping("/save")
    public ServerResponse productSave(HttpServletRequest request, Product product) {

        return productService.saveOrUpdateProduct(product);
    }

    @ApiOperation(value = "产品上下架")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productId", value = "用户名", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "密码", required = true, dataType = "int")
    })
    @RequestMapping("/set_sale_status")
    public ServerResponse setSaleStatus(HttpServletRequest request, @RequestParam int productId, @RequestParam int status) {

        return productService.setSaleStatus(productId, status);
    }

    @ApiOperation(value = "产品详情")
    @ApiImplicitParam(paramType = "query", name = "productId", value = "用户名", required = true, dataType = "int")
    @RequestMapping("/detail")
    public ServerResponse getDetail(HttpServletRequest request, @RequestParam int productId) {

        return productService.manageProductDeatil(productId);
    }

    @ApiOperation(value = "产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int")
    })
    @RequestMapping("/list")
    public ServerResponse getList(HttpServletRequest request,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return productService.getProductList(pageNum, pageSize);
    }

    @ApiOperation(value = "查询产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productName", value = "产品名称", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "productId", value = "产品id", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", defaultValue = "10", dataType = "int")
    })
    @RequestMapping("/search")
    public ServerResponse productSearch(HttpServletRequest request, String productName, Integer productId,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return productService.searchProduct(productName, productId, pageNum, pageSize);
    }

    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(paramType = "query", name = "upload_file", value = "上传的文件", required = false, dataType = "MultipartFile")
    @RequestMapping("/upload")
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {

        String path = request.getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @ApiOperation(value = "富文本上传")
    @ApiImplicitParam(paramType = "query", name = "upload_file", value = "上传的文件", required = false, dataType = "MultipartFile")
    @RequestMapping("/richtext_img_upload")
    public Map richtextImgUpload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        Map resultMap = Maps.newHashMap();
        String path = request.getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {

            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);

        response.setHeader("Access-Control-Allow-Headers", "X-File-Name");
        return resultMap;
    }
}
