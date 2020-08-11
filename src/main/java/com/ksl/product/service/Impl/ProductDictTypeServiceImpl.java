package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductDictType;
import com.ksl.product.mapper.ProductDictTypeMapper;
import com.ksl.product.service.ProductDictTypeService;
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
 * 产品数据类型字典表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductDictTypeServiceImpl extends ServiceImpl<ProductDictTypeMapper, ProductDictType> implements ProductDictTypeService {

   @Resource
   private ProductDictTypeMapper productdicttypeMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductDictType getProductDictTypeInfoById(Integer id){
    ProductDictType productdicttype = null;
        try {
    productdicttype = productdicttypeMapper.selectOne(new QueryWrapper<ProductDictType>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productdicttype){
        throw new ApiException("当前查询的:[{productdicttype}]不存在");
        }
        return productdicttype;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductDictType getProductDictTypeInfoByName(String productdicttypeName){
    ProductDictType productdicttype = null;
        try {
    productdicttype = productdicttypeMapper.selectOne(new QueryWrapper<ProductDictType>().eq("name", productdicttypeName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdicttype}] 未知异常");
        }
        if (null == productdicttype){
        throw new ApiException("当前查询的:[{productdicttype}]不存在");
        }
        return productdicttype;
   }

   @Override
   public boolean updateProductDictType(ProductDictType productdicttype){
        int row = 0;
        try {
        row = productdicttypeMapper.update(productdicttype, new UpdateWrapper<ProductDictType>().eq("id", productdicttype.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdicttype}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductDictType(ProductDictType productdicttype){
        int row = 0;
        try {
        row = productdicttypeMapper.insert(productdicttype);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdicttype}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductDictType(Integer id){
        int row = 0;
        try {
        row = productdicttypeMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdicttype}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductDictTypeList(List<Integer> ids) {
        int row = 0;
        try {
        row = productdicttypeMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productdicttype}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductDictTypeList(Integer pageNo,Integer size){
        Page<ProductDictType> page = new Page<>(pageNo,size);
        Page productdicttypePage = productdicttypeMapper.selectPage(page, new QueryWrapper<ProductDictType>());
        return productdicttypePage;
   }
}
