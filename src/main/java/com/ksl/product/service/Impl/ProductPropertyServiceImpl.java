package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductProperty;
import com.ksl.product.mapper.ProductPropertyMapper;
import com.ksl.product.service.ProductPropertyService;
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
 * 属性值表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductPropertyServiceImpl extends ServiceImpl<ProductPropertyMapper, ProductProperty> implements ProductPropertyService {

   @Resource
   private ProductPropertyMapper productpropertyMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductProperty getProductPropertyInfoById(Integer id){
    ProductProperty productproperty = null;
        try {
    productproperty = productpropertyMapper.selectOne(new QueryWrapper<ProductProperty>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productproperty){
        throw new ApiException("当前查询的:[{productproperty}]不存在");
        }
        return productproperty;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductProperty getProductPropertyInfoByName(String productpropertyName){
    ProductProperty productproperty = null;
        try {
    productproperty = productpropertyMapper.selectOne(new QueryWrapper<ProductProperty>().eq("name", productpropertyName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productproperty}] 未知异常");
        }
        if (null == productproperty){
        throw new ApiException("当前查询的:[{productproperty}]不存在");
        }
        return productproperty;
   }

   @Override
   public boolean updateProductProperty(ProductProperty productproperty){
        int row = 0;
        try {
        row = productpropertyMapper.update(productproperty, new UpdateWrapper<ProductProperty>().eq("id", productproperty.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productproperty}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductProperty(ProductProperty productproperty){
        int row = 0;
        try {
        row = productpropertyMapper.insert(productproperty);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productproperty}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductProperty(Integer id){
        int row = 0;
        try {
        row = productpropertyMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productproperty}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductPropertyList(List<Integer> ids) {
        int row = 0;
        try {
        row = productpropertyMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productproperty}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductPropertyList(Integer pageNo,Integer size){
        Page<ProductProperty> page = new Page<>(pageNo,size);
        Page productpropertyPage = productpropertyMapper.selectPage(page, new QueryWrapper<ProductProperty>());
        return productpropertyPage;
   }
}
