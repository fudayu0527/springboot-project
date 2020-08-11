package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ProductDictType;
import com.ksl.product.service.Impl.ProductDictTypeServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 产品数据类型字典表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("产品数据类型字典表 ")
@RestController
@RequestMapping("/productdicttype")
public class ProductDictTypeController {

   @Resource
   private ProductDictTypeServiceImpl productdicttypeService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getProductDictTypeList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page productdicttypePage = productdicttypeService.getProductDictTypeList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(productdicttypePage.getTotal(),productdicttypePage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ProductDictType> getProductDictTypeInfoById(@PathVariable("id") Integer id) {
          return Result.success(productdicttypeService.getProductDictTypeInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ProductDictType> getProductDictTypeInfoById(@RequestParam("name") String name) {
          return Result.success(productdicttypeService.getProductDictTypeInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertProductDictType(@RequestBody ProductDictType productdicttype) {
          return Result.success(productdicttypeService.insertProductDictType(productdicttype));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateProductDictType(@RequestBody ProductDictType productdicttype) {
          return Result.success(productdicttypeService.updateProductDictType(productdicttype));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delProductDictType(@PathVariable("id") Integer id) {
          return Result.success(productdicttypeService.deleteProductDictType(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delProductDictTypeList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(productdicttypeService.deleteProductDictTypeList(ids));
     }
}
