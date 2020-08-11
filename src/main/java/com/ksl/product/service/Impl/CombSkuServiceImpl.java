package com.ksl.product.service.Impl;

import com.ksl.product.entity.CombSku;
import com.ksl.product.mapper.CombSkuMapper;
import com.ksl.product.service.CombSkuService;
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
 * 组合产品列表  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class CombSkuServiceImpl extends ServiceImpl<CombSkuMapper, CombSku> implements CombSkuService {

   @Resource
   private CombSkuMapper combskuMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public CombSku getCombSkuInfoById(Integer id){
    CombSku combsku = null;
        try {
    combsku = combskuMapper.selectOne(new QueryWrapper<CombSku>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == combsku){
        throw new ApiException("当前查询的:[{combsku}]不存在");
        }
        return combsku;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public CombSku getCombSkuInfoByName(String combskuName){
    CombSku combsku = null;
        try {
    combsku = combskuMapper.selectOne(new QueryWrapper<CombSku>().eq("name", combskuName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combsku}] 未知异常");
        }
        if (null == combsku){
        throw new ApiException("当前查询的:[{combsku}]不存在");
        }
        return combsku;
   }

   @Override
   public boolean updateCombSku(CombSku combsku){
        int row = 0;
        try {
        row = combskuMapper.update(combsku, new UpdateWrapper<CombSku>().eq("id", combsku.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertCombSku(CombSku combsku){
        int row = 0;
        try {
        row = combskuMapper.insert(combsku);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteCombSku(Integer id){
        int row = 0;
        try {
        row = combskuMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{combsku}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteCombSkuList(List<Integer> ids) {
        int row = 0;
        try {
        row = combskuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{combsku}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getCombSkuList(Integer pageNo,Integer size){
        Page<CombSku> page = new Page<>(pageNo,size);
        Page combskuPage = combskuMapper.selectPage(page, new QueryWrapper<CombSku>());
        return combskuPage;
   }
}
