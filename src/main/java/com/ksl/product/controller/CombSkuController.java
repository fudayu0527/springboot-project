package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.CombSku;
import com.ksl.product.service.Impl.CombSkuServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 组合产品列表  前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("组合产品列表")
@RestController
@RequestMapping("/combsku")
public class CombSkuController {

   @Resource
   private CombSkuServiceImpl combskuService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getCombSkuList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page combskuPage = combskuService.getCombSkuList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(combskuPage.getTotal(),combskuPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<CombSku> getCombSkuInfoById(@PathVariable("id") Integer id) {
          return Result.success(combskuService.getCombSkuInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<CombSku> getCombSkuInfoById(@RequestParam("name") String name) {
          return Result.success(combskuService.getCombSkuInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertCombSku(@RequestBody CombSku combsku) {
          return Result.success(combskuService.insertCombSku(combsku));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateCombSku(@RequestBody CombSku combsku) {
          return Result.success(combskuService.updateCombSku(combsku));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delCombSku(@PathVariable("id") Integer id) {
          return Result.success(combskuService.deleteCombSku(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delCombSkuList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(combskuService.deleteCombSkuList(ids));
     }
}
