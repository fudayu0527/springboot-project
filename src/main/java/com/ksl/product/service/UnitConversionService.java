package com.ksl.product.service;

import com.ksl.product.entity.UnitConversion;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
public interface UnitConversionService extends IService<UnitConversion> {
    /**
     * 根据UnitConversion的id获取当前对象
     * @param id
     * @return UnitConversion
     */
    UnitConversion getUnitConversionInfoById(Integer id);

    /**
    * 根据UnitConversion的名称获取当前对象
    * @param unitconversionName
    * @return UnitConversion
    */
    UnitConversion getUnitConversionInfoByName(String unitconversionName);

    /**
    * 修改
    * @param unitconversion
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateUnitConversion(UnitConversion unitconversion);

    /**
    * 新增
    * @param unitconversion
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertUnitConversion(UnitConversion unitconversion);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteUnitConversion(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteUnitConversionList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<UnitConversion>
    */
    Page getUnitConversionList(Integer pageNo, Integer size);
}
