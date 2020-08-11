package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.BrandRelatedCategory;
import com.ksl.product.service.Impl.BrandRelatedCategoryServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 品牌关联类目表 一个品牌可以关联多个一级或二级类目  前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("品牌关联类目表 一个品牌可以关联多个一级或二级类目")
@RestController
@RequestMapping("/brandrelatedcategory")
public class BrandRelatedCategoryController {

   @Resource
   private BrandRelatedCategoryServiceImpl brandrelatedcategoryService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getBrandRelatedCategoryList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page brandrelatedcategoryPage = brandrelatedcategoryService.getBrandRelatedCategoryList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(brandrelatedcategoryPage.getTotal(),brandrelatedcategoryPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<BrandRelatedCategory> getBrandRelatedCategoryInfoById(@PathVariable("id") Integer id) {
          return Result.success(brandrelatedcategoryService.getBrandRelatedCategoryInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<BrandRelatedCategory> getBrandRelatedCategoryInfoById(@RequestParam("name") String name) {
          return Result.success(brandrelatedcategoryService.getBrandRelatedCategoryInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertBrandRelatedCategory(@RequestBody BrandRelatedCategory brandrelatedcategory) {
          return Result.success(brandrelatedcategoryService.insertBrandRelatedCategory(brandrelatedcategory));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateBrandRelatedCategory(@RequestBody BrandRelatedCategory brandrelatedcategory) {
          return Result.success(brandrelatedcategoryService.updateBrandRelatedCategory(brandrelatedcategory));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delBrandRelatedCategory(@PathVariable("id") Integer id) {
          return Result.success(brandrelatedcategoryService.deleteBrandRelatedCategory(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delBrandRelatedCategoryList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(brandrelatedcategoryService.deleteBrandRelatedCategoryList(ids));
     }
}
