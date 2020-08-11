package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.BrandRelatedSku;
import com.ksl.product.service.Impl.BrandRelatedSkuServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 品牌关联sku表  前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("品牌关联sku表")
@RestController
@RequestMapping("/brandrelatedsku")
public class BrandRelatedSkuController {

   @Resource
   private BrandRelatedSkuServiceImpl brandrelatedskuService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getBrandRelatedSkuList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page brandrelatedskuPage = brandrelatedskuService.getBrandRelatedSkuList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(brandrelatedskuPage.getTotal(),brandrelatedskuPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<BrandRelatedSku> getBrandRelatedSkuInfoById(@PathVariable("id") Integer id) {
          return Result.success(brandrelatedskuService.getBrandRelatedSkuInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<BrandRelatedSku> getBrandRelatedSkuInfoById(@RequestParam("name") String name) {
          return Result.success(brandrelatedskuService.getBrandRelatedSkuInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertBrandRelatedSku(@RequestBody BrandRelatedSku brandrelatedsku) {
          return Result.success(brandrelatedskuService.insertBrandRelatedSku(brandrelatedsku));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateBrandRelatedSku(@RequestBody BrandRelatedSku brandrelatedsku) {
          return Result.success(brandrelatedskuService.updateBrandRelatedSku(brandrelatedsku));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delBrandRelatedSku(@PathVariable("id") Integer id) {
          return Result.success(brandrelatedskuService.deleteBrandRelatedSku(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delBrandRelatedSkuList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(brandrelatedskuService.deleteBrandRelatedSkuList(ids));
     }
}
