package com.ksl.product.service.Impl;

import com.ksl.product.entity.BrandRelatedSku;
import com.ksl.product.mapper.BrandRelatedSkuMapper;
import com.ksl.product.service.BrandRelatedSkuService;
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
 * 品牌关联sku表  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class BrandRelatedSkuServiceImpl extends ServiceImpl<BrandRelatedSkuMapper, BrandRelatedSku> implements BrandRelatedSkuService {

   @Resource
   private BrandRelatedSkuMapper brandrelatedskuMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public BrandRelatedSku getBrandRelatedSkuInfoById(Integer id){
    BrandRelatedSku brandrelatedsku = null;
        try {
    brandrelatedsku = brandrelatedskuMapper.selectOne(new QueryWrapper<BrandRelatedSku>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == brandrelatedsku){
        throw new ApiException("当前查询的:[{brandrelatedsku}]不存在");
        }
        return brandrelatedsku;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public BrandRelatedSku getBrandRelatedSkuInfoByName(String brandrelatedskuName){
    BrandRelatedSku brandrelatedsku = null;
        try {
    brandrelatedsku = brandrelatedskuMapper.selectOne(new QueryWrapper<BrandRelatedSku>().eq("name", brandrelatedskuName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedsku}] 未知异常");
        }
        if (null == brandrelatedsku){
        throw new ApiException("当前查询的:[{brandrelatedsku}]不存在");
        }
        return brandrelatedsku;
   }

   @Override
   public boolean updateBrandRelatedSku(BrandRelatedSku brandrelatedsku){
        int row = 0;
        try {
        row = brandrelatedskuMapper.update(brandrelatedsku, new UpdateWrapper<BrandRelatedSku>().eq("id", brandrelatedsku.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertBrandRelatedSku(BrandRelatedSku brandrelatedsku){
        int row = 0;
        try {
        row = brandrelatedskuMapper.insert(brandrelatedsku);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteBrandRelatedSku(Integer id){
        int row = 0;
        try {
        row = brandrelatedskuMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedsku}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteBrandRelatedSkuList(List<Integer> ids) {
        int row = 0;
        try {
        row = brandrelatedskuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{brandrelatedsku}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getBrandRelatedSkuList(Integer pageNo,Integer size){
        Page<BrandRelatedSku> page = new Page<>(pageNo,size);
        Page brandrelatedskuPage = brandrelatedskuMapper.selectPage(page, new QueryWrapper<BrandRelatedSku>());
        return brandrelatedskuPage;
   }
}
