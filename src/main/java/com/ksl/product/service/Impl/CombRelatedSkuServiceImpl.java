package com.ksl.product.service.Impl;

import com.ksl.product.entity.CombRelatedSku;
import com.ksl.product.mapper.CombRelatedSkuMapper;
import com.ksl.product.service.CombRelatedSkuService;
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
 * 组合产品关联sku表  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class CombRelatedSkuServiceImpl extends ServiceImpl<CombRelatedSkuMapper, CombRelatedSku> implements CombRelatedSkuService {

   @Resource
   private CombRelatedSkuMapper combrelatedskuMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public CombRelatedSku getCombRelatedSkuInfoById(Integer id){
    CombRelatedSku combrelatedsku = null;
        try {
    combrelatedsku = combrelatedskuMapper.selectOne(new QueryWrapper<CombRelatedSku>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == combrelatedsku){
        throw new ApiException("当前查询的:[{combrelatedsku}]不存在");
        }
        return combrelatedsku;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public CombRelatedSku getCombRelatedSkuInfoByName(String combrelatedskuName){
    CombRelatedSku combrelatedsku = null;
        try {
    combrelatedsku = combrelatedskuMapper.selectOne(new QueryWrapper<CombRelatedSku>().eq("name", combrelatedskuName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combrelatedsku}] 未知异常");
        }
        if (null == combrelatedsku){
        throw new ApiException("当前查询的:[{combrelatedsku}]不存在");
        }
        return combrelatedsku;
   }

   @Override
   public boolean updateCombRelatedSku(CombRelatedSku combrelatedsku){
        int row = 0;
        try {
        row = combrelatedskuMapper.update(combrelatedsku, new UpdateWrapper<CombRelatedSku>().eq("id", combrelatedsku.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combrelatedsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertCombRelatedSku(CombRelatedSku combrelatedsku){
        int row = 0;
        try {
        row = combrelatedskuMapper.insert(combrelatedsku);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combrelatedsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteCombRelatedSku(Integer id){
        int row = 0;
        try {
        row = combrelatedskuMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combrelatedsku}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteCombRelatedSkuList(List<Integer> ids) {
        int row = 0;
        try {
        row = combrelatedskuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{combrelatedsku}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getCombRelatedSkuList(Integer pageNo,Integer size){
        Page<CombRelatedSku> page = new Page<>(pageNo,size);
        Page combrelatedskuPage = combrelatedskuMapper.selectPage(page, new QueryWrapper<CombRelatedSku>());
        return combrelatedskuPage;
   }
}
