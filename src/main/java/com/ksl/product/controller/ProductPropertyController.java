package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ProductProperty;
import com.ksl.product.service.Impl.ProductPropertyServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 属性值表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("属性值表 ")
@RestController
@RequestMapping("/productproperty")
public class ProductPropertyController {

   @Resource
   private ProductPropertyServiceImpl productpropertyService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getProductPropertyList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page productpropertyPage = productpropertyService.getProductPropertyList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(productpropertyPage.getTotal(),productpropertyPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ProductProperty> getProductPropertyInfoById(@PathVariable("id") Integer id) {
          return Result.success(productpropertyService.getProductPropertyInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ProductProperty> getProductPropertyInfoById(@RequestParam("name") String name) {
          return Result.success(productpropertyService.getProductPropertyInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertProductProperty(@RequestBody ProductProperty productproperty) {
          return Result.success(productpropertyService.insertProductProperty(productproperty));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateProductProperty(@RequestBody ProductProperty productproperty) {
          return Result.success(productpropertyService.updateProductProperty(productproperty));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delProductProperty(@PathVariable("id") Integer id) {
          return Result.success(productpropertyService.deleteProductProperty(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delProductPropertyList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(productpropertyService.deleteProductPropertyList(ids));
     }
}
