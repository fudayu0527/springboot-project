package com.ksl.product.service.Impl;

import com.ksl.product.entity.ExternalSku;
import com.ksl.product.mapper.ExternalSkuMapper;
import com.ksl.product.service.ExternalSkuService;
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
 * 外部sku   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ExternalSkuServiceImpl extends ServiceImpl<ExternalSkuMapper, ExternalSku> implements ExternalSkuService {

   @Resource
   private ExternalSkuMapper externalskuMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ExternalSku getExternalSkuInfoById(Integer id){
    ExternalSku externalsku = null;
        try {
    externalsku = externalskuMapper.selectOne(new QueryWrapper<ExternalSku>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == externalsku){
        throw new ApiException("当前查询的:[{externalsku}]不存在");
        }
        return externalsku;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ExternalSku getExternalSkuInfoByName(String externalskuName){
    ExternalSku externalsku = null;
        try {
    externalsku = externalskuMapper.selectOne(new QueryWrapper<ExternalSku>().eq("name", externalskuName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{externalsku}] 未知异常");
        }
        if (null == externalsku){
        throw new ApiException("当前查询的:[{externalsku}]不存在");
        }
        return externalsku;
   }

   @Override
   public boolean updateExternalSku(ExternalSku externalsku){
        int row = 0;
        try {
        row = externalskuMapper.update(externalsku, new UpdateWrapper<ExternalSku>().eq("id", externalsku.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{externalsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertExternalSku(ExternalSku externalsku){
        int row = 0;
        try {
        row = externalskuMapper.insert(externalsku);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{externalsku}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteExternalSku(Integer id){
        int row = 0;
        try {
        row = externalskuMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{externalsku}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteExternalSkuList(List<Integer> ids) {
        int row = 0;
        try {
        row = externalskuMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{externalsku}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getExternalSkuList(Integer pageNo,Integer size){
        Page<ExternalSku> page = new Page<>(pageNo,size);
        Page externalskuPage = externalskuMapper.selectPage(page, new QueryWrapper<ExternalSku>());
        return externalskuPage;
   }
}
