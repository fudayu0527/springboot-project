package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ProductPropertyExp;
import com.ksl.product.service.Impl.ProductPropertyExpServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 属性值拓展表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("属性值拓展表 ")
@RestController
@RequestMapping("/productpropertyexp")
public class ProductPropertyExpController {

   @Resource
   private ProductPropertyExpServiceImpl productpropertyexpService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getProductPropertyExpList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page productpropertyexpPage = productpropertyexpService.getProductPropertyExpList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(productpropertyexpPage.getTotal(),productpropertyexpPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ProductPropertyExp> getProductPropertyExpInfoById(@PathVariable("id") Integer id) {
          return Result.success(productpropertyexpService.getProductPropertyExpInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ProductPropertyExp> getProductPropertyExpInfoById(@RequestParam("name") String name) {
          return Result.success(productpropertyexpService.getProductPropertyExpInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertProductPropertyExp(@RequestBody ProductPropertyExp productpropertyexp) {
          return Result.success(productpropertyexpService.insertProductPropertyExp(productpropertyexp));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateProductPropertyExp(@RequestBody ProductPropertyExp productpropertyexp) {
          return Result.success(productpropertyexpService.updateProductPropertyExp(productpropertyexp));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delProductPropertyExp(@PathVariable("id") Integer id) {
          return Result.success(productpropertyexpService.deleteProductPropertyExp(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delProductPropertyExpList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(productpropertyexpService.deleteProductPropertyExpList(ids));
     }
}
