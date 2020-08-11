package com.ksl.product.service.Impl;

import com.ksl.product.entity.Unit;
import com.ksl.product.mapper.UnitMapper;
import com.ksl.product.service.UnitService;
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
 * 计量单位   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {

   @Resource
   private UnitMapper unitMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public Unit getUnitInfoById(Integer id){
    Unit unit = null;
        try {
    unit = unitMapper.selectOne(new QueryWrapper<Unit>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == unit){
        throw new ApiException("当前查询的:[{unit}]不存在");
        }
        return unit;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public Unit getUnitInfoByName(String unitName){
    Unit unit = null;
        try {
    unit = unitMapper.selectOne(new QueryWrapper<Unit>().eq("name", unitName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unit}] 未知异常");
        }
        if (null == unit){
        throw new ApiException("当前查询的:[{unit}]不存在");
        }
        return unit;
   }

   @Override
   public boolean updateUnit(Unit unit){
        int row = 0;
        try {
        row = unitMapper.update(unit, new UpdateWrapper<Unit>().eq("id", unit.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unit}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertUnit(Unit unit){
        int row = 0;
        try {
        row = unitMapper.insert(unit);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unit}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteUnit(Integer id){
        int row = 0;
        try {
        row = unitMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{unit}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteUnitList(List<Integer> ids) {
        int row = 0;
        try {
        row = unitMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{unit}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getUnitList(Integer pageNo,Integer size){
        Page<Unit> page = new Page<>(pageNo,size);
        Page unitPage = unitMapper.selectPage(page, new QueryWrapper<Unit>());
        return unitPage;
   }
}
