package com.ksl.product.service.Impl;

import com.ksl.product.entity.Sku;
import com.ksl.product.mapper.SkuMapper;
import com.ksl.product.service.SkuService;
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
 * sku信息表  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

   @Resource
   private SkuMapper skuMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public Sku getSkuInfoById(Integer id){
    Sku sku = null;
        try {
    sku = skuMapper.selectOne(new QueryWrapper<Sku>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == sku){
        throw new ApiException("当前查询的:[{sku}]不存在");
        }
        return sku;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public Sku getSkuInfoByName(String skuName){
    Sku sku = null;
        try {
    sku = skuMapper.selectOne(new QueryWrapper<Sku>().eq("name", skuName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{sku}] 未知异常");
        }
        if (null == sku){
        throw new ApiException("当前查询的:[{sku}]不存在");
        }
        return sku;
   }

   @Override
   public boolean updateSku(Sku sku){
        int row = 0;
        try {
        row = skuMapper.update(sku, new UpdateWrapper<Sku>().eq("id", sku.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{sku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertSku(Sku sku){
        int row = 0;
        try {
        row = skuMapper.insert(sku);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{sku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteSku(Integer id){
        int row = 0;
        try {
        row = skuMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{sku}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteSkuList(List<Integer> ids) {
        int row = 0;
        try {
        row = skuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{sku}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getSkuList(Integer pageNo,Integer size){
        Page<Sku> page = new Page<>(pageNo,size);
        Page skuPage = skuMapper.selectPage(page, new QueryWrapper<Sku>());
        return skuPage;
   }
}
