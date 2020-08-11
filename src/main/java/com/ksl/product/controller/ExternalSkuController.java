package com.ksl.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ksl.common.core.domain.TableDto;
import com.ksl.common.core.utils.TableDtoUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.ksl.product.entity.ExternalSku;
import com.ksl.product.service.Impl.ExternalSkuServiceImpl;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.web.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * <p>
 * 外部sku   前端控制器
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Api("外部sku ")
@RestController
@RequestMapping("/externalsku")
public class ExternalSkuController {

   @Resource
   private ExternalSkuServiceImpl externalskuService;

   /**
    * 分页获取列表
    */
    @GetMapping("/all")
    public TableDto getExternalSkuList(HttpServletRequest req) {
          int pageNo = Integer.parseInt(req.getParameter("page"));
          int pageSize = Integer.parseInt(req.getParameter("limit"));
          Page externalskuPage = externalskuService.getExternalSkuList(pageNo,pageSize);
          return TableDtoUtils.setTableDto(externalskuPage.getTotal(),externalskuPage.getRecords());
    }

   /**
    * 根据id获取信息
    */
    @GetMapping("/{id}")
    public Result<ExternalSku> getExternalSkuInfoById(@PathVariable("id") Integer id) {
          return Result.success(externalskuService.getExternalSkuInfoById(id));
    }

   /**
    * 根据名称获取信息
    */
    @GetMapping("/n")
    public Result<ExternalSku> getExternalSkuInfoById(@RequestParam("name") String name) {
          return Result.success(externalskuService.getExternalSkuInfoByName(name));
    }

   /**
    * 新增
    */
    @PostMapping("/add")
    public Result insertExternalSku(@RequestBody ExternalSku externalsku) {
          return Result.success(externalskuService.insertExternalSku(externalsku));
    }

    /**
     * 更新
     */
     @PostMapping("/update")
     public Result updateExternalSku(@RequestBody ExternalSku externalsku) {
          return Result.success(externalskuService.updateExternalSku(externalsku));
     }

    /**
     * 根据id删除
     */
     @DeleteMapping("/{id}")
     public Result delExternalSku(@PathVariable("id") Integer id) {
          return Result.success(externalskuService.deleteExternalSku(id));
     }

    /**
     * 根据id列表批量删除
     */
     @DeleteMapping("/dels")
     public Result delExternalSkuList(@RequestParam("ids") List<Integer> ids) {
        return Result.success(externalskuService.deleteExternalSkuList(ids));
     }
}
