package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ProductCategory;
import com.ksl.product.service.Impl.ProductCategoryServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 产品类目表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("产品类目表 ")
@RestController
@RequestMapping("/productcategory")
public class ProductCategoryController {

   @Resource
   private ProductCategoryServiceImpl productcategoryService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getProductCategoryList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page productcategoryPage = productcategoryService.getProductCategoryList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(productcategoryPage.getTotal(),productcategoryPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ProductCategory> getProductCategoryInfoById(@PathVariable("id") Integer id) {
          return Result.success(productcategoryService.getProductCategoryInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ProductCategory> getProductCategoryInfoById(@RequestParam("name") String name) {
          return Result.success(productcategoryService.getProductCategoryInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertProductCategory(@RequestBody ProductCategory productcategory) {
          return Result.success(productcategoryService.insertProductCategory(productcategory));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateProductCategory(@RequestBody ProductCategory productcategory) {
          return Result.success(productcategoryService.updateProductCategory(productcategory));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delProductCategory(@PathVariable("id") Integer id) {
          return Result.success(productcategoryService.deleteProductCategory(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delProductCategoryList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(productcategoryService.deleteProductCategoryList(ids));
     }
}
