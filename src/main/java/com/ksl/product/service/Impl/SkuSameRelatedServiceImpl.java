package com.ksl.product.service.Impl;

import com.ksl.product.entity.SkuSameRelated;
import com.ksl.product.mapper.SkuSameRelatedMapper;
import com.ksl.product.service.SkuSameRelatedService;
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
 * 同款sku关联表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class SkuSameRelatedServiceImpl extends ServiceImpl<SkuSameRelatedMapper, SkuSameRelated> implements SkuSameRelatedService {

   @Resource
   private SkuSameRelatedMapper skusamerelatedMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public SkuSameRelated getSkuSameRelatedInfoById(Integer id){
    SkuSameRelated skusamerelated = null;
        try {
    skusamerelated = skusamerelatedMapper.selectOne(new QueryWrapper<SkuSameRelated>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == skusamerelated){
        throw new ApiException("当前查询的:[{skusamerelated}]不存在");
        }
        return skusamerelated;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public SkuSameRelated getSkuSameRelatedInfoByName(String skusamerelatedName){
    SkuSameRelated skusamerelated = null;
        try {
    skusamerelated = skusamerelatedMapper.selectOne(new QueryWrapper<SkuSameRelated>().eq("name", skusamerelatedName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skusamerelated}] 未知异常");
        }
        if (null == skusamerelated){
        throw new ApiException("当前查询的:[{skusamerelated}]不存在");
        }
        return skusamerelated;
   }

   @Override
   public boolean updateSkuSameRelated(SkuSameRelated skusamerelated){
        int row = 0;
        try {
        row = skusamerelatedMapper.update(skusamerelated, new UpdateWrapper<SkuSameRelated>().eq("id", skusamerelated.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skusamerelated}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertSkuSameRelated(SkuSameRelated skusamerelated){
        int row = 0;
        try {
        row = skusamerelatedMapper.insert(skusamerelated);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skusamerelated}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteSkuSameRelated(Integer id){
        int row = 0;
        try {
        row = skusamerelatedMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skusamerelated}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteSkuSameRelatedList(List<Integer> ids) {
        int row = 0;
        try {
        row = skusamerelatedMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{skusamerelated}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getSkuSameRelatedList(Integer pageNo,Integer size){
        Page<SkuSameRelated> page = new Page<>(pageNo,size);
        Page skusamerelatedPage = skusamerelatedMapper.selectPage(page, new QueryWrapper<SkuSameRelated>());
        return skusamerelatedPage;
   }
}
