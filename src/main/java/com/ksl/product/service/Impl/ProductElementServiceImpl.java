package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductElement;
import com.ksl.product.mapper.ProductElementMapper;
import com.ksl.product.service.ProductElementService;
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
 * 产品元素表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductElementServiceImpl extends ServiceImpl<ProductElementMapper, ProductElement> implements ProductElementService {

   @Resource
   private ProductElementMapper productelementMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductElement getProductElementInfoById(Integer id){
    ProductElement productelement = null;
        try {
    productelement = productelementMapper.selectOne(new QueryWrapper<ProductElement>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productelement){
        throw new ApiException("当前查询的:[{productelement}]不存在");
        }
        return productelement;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductElement getProductElementInfoByName(String productelementName){
    ProductElement productelement = null;
        try {
    productelement = productelementMapper.selectOne(new QueryWrapper<ProductElement>().eq("name", productelementName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productelement}] 未知异常");
        }
        if (null == productelement){
        throw new ApiException("当前查询的:[{productelement}]不存在");
        }
        return productelement;
   }

   @Override
   public boolean updateProductElement(ProductElement productelement){
        int row = 0;
        try {
        row = productelementMapper.update(productelement, new UpdateWrapper<ProductElement>().eq("id", productelement.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productelement}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductElement(ProductElement productelement){
        int row = 0;
        try {
        row = productelementMapper.insert(productelement);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productelement}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductElement(Integer id){
        int row = 0;
        try {
        row = productelementMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productelement}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductElementList(List<Integer> ids) {
        int row = 0;
        try {
        row = productelementMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productelement}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductElementList(Integer pageNo,Integer size){
        Page<ProductElement> page = new Page<>(pageNo,size);
        Page productelementPage = productelementMapper.selectPage(page, new QueryWrapper<ProductElement>());
        return productelementPage;
   }
}
