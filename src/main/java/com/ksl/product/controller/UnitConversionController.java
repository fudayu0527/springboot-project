package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.UnitConversion;
import com.ksl.product.service.Impl.UnitConversionServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 单位转换   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("单位转换 ")
@RestController
@RequestMapping("/unitconversion")
public class UnitConversionController {

   @Resource
   private UnitConversionServiceImpl unitconversionService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getUnitConversionList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page unitconversionPage = unitconversionService.getUnitConversionList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(unitconversionPage.getTotal(),unitconversionPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<UnitConversion> getUnitConversionInfoById(@PathVariable("id") Integer id) {
          return Result.success(unitconversionService.getUnitConversionInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<UnitConversion> getUnitConversionInfoById(@RequestParam("name") String name) {
          return Result.success(unitconversionService.getUnitConversionInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertUnitConversion(@RequestBody UnitConversion unitconversion) {
          return Result.success(unitconversionService.insertUnitConversion(unitconversion));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateUnitConversion(@RequestBody UnitConversion unitconversion) {
          return Result.success(unitconversionService.updateUnitConversion(unitconversion));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delUnitConversion(@PathVariable("id") Integer id) {
          return Result.success(unitconversionService.deleteUnitConversion(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delUnitConversionList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(unitconversionService.deleteUnitConversionList(ids));
     }
}
