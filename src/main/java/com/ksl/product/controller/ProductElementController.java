package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ProductElement;
import com.ksl.product.service.Impl.ProductElementServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 产品元素表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("产品元素表 ")
@RestController
@RequestMapping("/productelement")
public class ProductElementController {

   @Resource
   private ProductElementServiceImpl productelementService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getProductElementList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page productelementPage = productelementService.getProductElementList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(productelementPage.getTotal(),productelementPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ProductElement> getProductElementInfoById(@PathVariable("id") Integer id) {
          return Result.success(productelementService.getProductElementInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ProductElement> getProductElementInfoById(@RequestParam("name") String name) {
          return Result.success(productelementService.getProductElementInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertProductElement(@RequestBody ProductElement productelement) {
          return Result.success(productelementService.insertProductElement(productelement));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateProductElement(@RequestBody ProductElement productelement) {
          return Result.success(productelementService.updateProductElement(productelement));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delProductElement(@PathVariable("id") Integer id) {
          return Result.success(productelementService.deleteProductElement(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delProductElementList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(productelementService.deleteProductElementList(ids));
     }
}
