package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.Sku;
import com.ksl.product.service.Impl.SkuServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * sku信息表  前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("sku信息表")
@RestController
@RequestMapping("/sku")
public class SkuController {

   @Resource
   private SkuServiceImpl skuService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getSkuList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page skuPage = skuService.getSkuList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(skuPage.getTotal(),skuPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<Sku> getSkuInfoById(@PathVariable("id") Integer id) {
          return Result.success(skuService.getSkuInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<Sku> getSkuInfoById(@RequestParam("name") String name) {
          return Result.success(skuService.getSkuInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertSku(@RequestBody Sku sku) {
          return Result.success(skuService.insertSku(sku));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateSku(@RequestBody Sku sku) {
          return Result.success(skuService.updateSku(sku));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delSku(@PathVariable("id") Integer id) {
          return Result.success(skuService.deleteSku(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delSkuList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(skuService.deleteSkuList(ids));
     }
}