package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.SkuSameRelated;
import com.ksl.product.service.Impl.SkuSameRelatedServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 同款sku关联表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("同款sku关联表 ")
@RestController
@RequestMapping("/skusamerelated")
public class SkuSameRelatedController {

   @Resource
   private SkuSameRelatedServiceImpl skusamerelatedService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getSkuSameRelatedList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page skusamerelatedPage = skusamerelatedService.getSkuSameRelatedList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(skusamerelatedPage.getTotal(),skusamerelatedPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<SkuSameRelated> getSkuSameRelatedInfoById(@PathVariable("id") Integer id) {
          return Result.success(skusamerelatedService.getSkuSameRelatedInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<SkuSameRelated> getSkuSameRelatedInfoById(@RequestParam("name") String name) {
          return Result.success(skusamerelatedService.getSkuSameRelatedInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertSkuSameRelated(@RequestBody SkuSameRelated skusamerelated) {
          return Result.success(skusamerelatedService.insertSkuSameRelated(skusamerelated));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateSkuSameRelated(@RequestBody SkuSameRelated skusamerelated) {
          return Result.success(skusamerelatedService.updateSkuSameRelated(skusamerelated));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delSkuSameRelated(@PathVariable("id") Integer id) {
          return Result.success(skusamerelatedService.deleteSkuSameRelated(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delSkuSameRelatedList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(skusamerelatedService.deleteSkuSameRelatedList(ids));
     }
}
