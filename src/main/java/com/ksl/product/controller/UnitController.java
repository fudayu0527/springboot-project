package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.Unit;
import com.ksl.product.service.Impl.UnitServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 计量单位   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("计量单位 ")
@RestController
@RequestMapping("/unit")
public class UnitController {

   @Resource
   private UnitServiceImpl unitService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getUnitList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page unitPage = unitService.getUnitList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(unitPage.getTotal(),unitPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<Unit> getUnitInfoById(@PathVariable("id") Integer id) {
          return Result.success(unitService.getUnitInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<Unit> getUnitInfoById(@RequestParam("name") String name) {
          return Result.success(unitService.getUnitInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertUnit(@RequestBody Unit unit) {
          return Result.success(unitService.insertUnit(unit));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateUnit(@RequestBody Unit unit) {
          return Result.success(unitService.updateUnit(unit));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delUnit(@PathVariable("id") Integer id) {
          return Result.success(unitService.deleteUnit(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delUnitList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(unitService.deleteUnitList(ids));
     }
}
