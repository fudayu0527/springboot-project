package com.ksl.product.service.Impl;

import com.ksl.product.entity.SkuStock;
import com.ksl.product.mapper.SkuStockMapper;
import com.ksl.product.service.SkuStockService;
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
 * sku库存表  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

   @Resource
   private SkuStockMapper skustockMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public SkuStock getSkuStockInfoById(Integer id){
    SkuStock skustock = null;
        try {
    skustock = skustockMapper.selectOne(new QueryWrapper<SkuStock>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == skustock){
        throw new ApiException("当前查询的:[{skustock}]不存在");
        }
        return skustock;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public SkuStock getSkuStockInfoByName(String skustockName){
    SkuStock skustock = null;
        try {
    skustock = skustockMapper.selectOne(new QueryWrapper<SkuStock>().eq("name", skustockName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skustock}] 未知异常");
        }
        if (null == skustock){
        throw new ApiException("当前查询的:[{skustock}]不存在");
        }
        return skustock;
   }

   @Override
   public boolean updateSkuStock(SkuStock skustock){
        int row = 0;
        try {
        row = skustockMapper.update(skustock, new UpdateWrapper<SkuStock>().eq("id", skustock.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skustock}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertSkuStock(SkuStock skustock){
        int row = 0;
        try {
        row = skustockMapper.insert(skustock);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skustock}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteSkuStock(Integer id){
        int row = 0;
        try {
        row = skustockMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skustock}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteSkuStockList(List<Integer> ids) {
        int row = 0;
        try {
        row = skustockMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{skustock}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getSkuStockList(Integer pageNo,Integer size){
        Page<SkuStock> page = new Page<>(pageNo,size);
        Page skustockPage = skustockMapper.selectPage(page, new QueryWrapper<SkuStock>());
        return skustockPage;
   }
}
