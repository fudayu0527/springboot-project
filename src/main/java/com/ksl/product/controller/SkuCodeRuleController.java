package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.SkuCodeRule;
import com.ksl.product.service.Impl.SkuCodeRuleServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * sku生成规则表   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("sku生成规则表 ")
@RestController
@RequestMapping("/skucoderule")
public class SkuCodeRuleController {

   @Resource
   private SkuCodeRuleServiceImpl skucoderuleService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getSkuCodeRuleList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page skucoderulePage = skucoderuleService.getSkuCodeRuleList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(skucoderulePage.getTotal(),skucoderulePage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<SkuCodeRule> getSkuCodeRuleInfoById(@PathVariable("id") Integer id) {
          return Result.success(skucoderuleService.getSkuCodeRuleInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<SkuCodeRule> getSkuCodeRuleInfoById(@RequestParam("name") String name) {
          return Result.success(skucoderuleService.getSkuCodeRuleInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertSkuCodeRule(@RequestBody SkuCodeRule skucoderule) {
          return Result.success(skucoderuleService.insertSkuCodeRule(skucoderule));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateSkuCodeRule(@RequestBody SkuCodeRule skucoderule) {
          return Result.success(skucoderuleService.updateSkuCodeRule(skucoderule));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delSkuCodeRule(@PathVariable("id") Integer id) {
          return Result.success(skucoderuleService.deleteSkuCodeRule(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delSkuCodeRuleList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(skucoderuleService.deleteSkuCodeRuleList(ids));
     }
}
