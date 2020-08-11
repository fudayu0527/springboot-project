package com.ksl.product.service.Impl;

import com.ksl.product.entity.SkuCodeRule;
import com.ksl.product.mapper.SkuCodeRuleMapper;
import com.ksl.product.service.SkuCodeRuleService;
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
 * sku生成规则表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class SkuCodeRuleServiceImpl extends ServiceImpl<SkuCodeRuleMapper, SkuCodeRule> implements SkuCodeRuleService {

   @Resource
   private SkuCodeRuleMapper skucoderuleMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public SkuCodeRule getSkuCodeRuleInfoById(Integer id){
    SkuCodeRule skucoderule = null;
        try {
    skucoderule = skucoderuleMapper.selectOne(new QueryWrapper<SkuCodeRule>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == skucoderule){
        throw new ApiException("当前查询的:[{skucoderule}]不存在");
        }
        return skucoderule;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public SkuCodeRule getSkuCodeRuleInfoByName(String skucoderuleName){
    SkuCodeRule skucoderule = null;
        try {
    skucoderule = skucoderuleMapper.selectOne(new QueryWrapper<SkuCodeRule>().eq("name", skucoderuleName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skucoderule}] 未知异常");
        }
        if (null == skucoderule){
        throw new ApiException("当前查询的:[{skucoderule}]不存在");
        }
        return skucoderule;
   }

   @Override
   public boolean updateSkuCodeRule(SkuCodeRule skucoderule){
        int row = 0;
        try {
        row = skucoderuleMapper.update(skucoderule, new UpdateWrapper<SkuCodeRule>().eq("id", skucoderule.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skucoderule}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertSkuCodeRule(SkuCodeRule skucoderule){
        int row = 0;
        try {
        row = skucoderuleMapper.insert(skucoderule);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skucoderule}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteSkuCodeRule(Integer id){
        int row = 0;
        try {
        row = skucoderuleMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{skucoderule}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteSkuCodeRuleList(List<Integer> ids) {
        int row = 0;
        try {
        row = skucoderuleMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{skucoderule}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getSkuCodeRuleList(Integer pageNo,Integer size){
        Page<SkuCodeRule> page = new Page<>(pageNo,size);
        Page skucoderulePage = skucoderuleMapper.selectPage(page, new QueryWrapper<SkuCodeRule>());
        return skucoderulePage;
   }
}
