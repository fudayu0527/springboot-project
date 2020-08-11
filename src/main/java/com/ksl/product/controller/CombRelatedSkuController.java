package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.CombRelatedSku;
import com.ksl.product.service.Impl.CombRelatedSkuServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 组合产品关联sku表  前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("组合产品关联sku表")
@RestController
@RequestMapping("/combrelatedsku")
public class CombRelatedSkuController {

   @Resource
   private CombRelatedSkuServiceImpl combrelatedskuService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getCombRelatedSkuList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page combrelatedskuPage = combrelatedskuService.getCombRelatedSkuList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(combrelatedskuPage.getTotal(),combrelatedskuPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<CombRelatedSku> getCombRelatedSkuInfoById(@PathVariable("id") Integer id) {
          return Result.success(combrelatedskuService.getCombRelatedSkuInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<CombRelatedSku> getCombRelatedSkuInfoById(@RequestParam("name") String name) {
          return Result.success(combrelatedskuService.getCombRelatedSkuInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertCombRelatedSku(@RequestBody CombRelatedSku combrelatedsku) {
          return Result.success(combrelatedskuService.insertCombRelatedSku(combrelatedsku));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateCombRelatedSku(@RequestBody CombRelatedSku combrelatedsku) {
          return Result.success(combrelatedskuService.updateCombRelatedSku(combrelatedsku));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delCombRelatedSku(@PathVariable("id") Integer id) {
          return Result.success(combrelatedskuService.deleteCombRelatedSku(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delCombRelatedSkuList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(combrelatedskuService.deleteCombRelatedSkuList(ids));
     }
}
