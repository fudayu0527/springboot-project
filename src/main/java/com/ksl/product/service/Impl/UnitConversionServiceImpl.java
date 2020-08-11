package com.ksl.product.service.Impl;

import com.ksl.product.entity.UnitConversion;
import com.ksl.product.mapper.UnitConversionMapper;
import com.ksl.product.service.UnitConversionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ksl.common.core.utils.TableDtoUtils;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * <p>
 * 单位转换   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class UnitConversionServiceImpl extends ServiceImpl<UnitConversionMapper, UnitConversion> implements UnitConversionService {

   @Resource
   private UnitConversionMapper unitconversionMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public UnitConversion getUnitConversionInfoById(Integer id){
    UnitConversion unitconversion = null;
        try {
    unitconversion = unitconversionMapper.selectOne(new QueryWrapper<UnitConversion>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == unitconversion){
        throw new ApiException("当前查询的:[{unitconversion}]不存在");
        }
        return unitconversion;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public UnitConversion getUnitConversionInfoByName(String unitconversionName){
    UnitConversion unitconversion = null;
        try {
    unitconversion = unitconversionMapper.selectOne(new QueryWrapper<UnitConversion>().eq("name", unitconversionName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unitconversion}] 未知异常");
        }
        if (null == unitconversion){
        throw new ApiException("当前查询的:[{unitconversion}]不存在");
        }
        return unitconversion;
   }

   @Override
   public boolean updateUnitConversion(UnitConversion unitconversion){
        int row = 0;
        try {
        row = unitconversionMapper.update(unitconversion, new UpdateWrapper<UnitConversion>().eq("id", unitconversion.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unitconversion}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertUnitConversion(UnitConversion unitconversion){
        int row = 0;
        try {
        row = unitconversionMapper.insert(unitconversion);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unitconversion}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteUnitConversion(Integer id){
        int row = 0;
        try {
        row = unitconversionMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unitconversion}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteUnitConversionList(List<Integer> ids) {
        int row = 0;
        try {
        row = unitconversionMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{unitconversion}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getUnitConversionList(Integer pageNo,Integer size){
        Page<UnitConversion> page = new Page<>(pageNo,size);
        Page unitconversionPage = unitconversionMapper.selectPage(page, new QueryWrapper<UnitConversion>());
        return unitconversionPage;
   }
}
