package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductCategory;
import com.ksl.product.mapper.ProductCategoryMapper;
import com.ksl.product.service.ProductCategoryService;
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
 * 产品类目表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

   @Resource
   private ProductCategoryMapper productcategoryMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductCategory getProductCategoryInfoById(Integer id){
    ProductCategory productcategory = null;
        try {
    productcategory = productcategoryMapper.selectOne(new QueryWrapper<ProductCategory>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productcategory){
        throw new ApiException("当前查询的:[{productcategory}]不存在");
        }
        return productcategory;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductCategory getProductCategoryInfoByName(String productcategoryName){
    ProductCategory productcategory = null;
        try {
    productcategory = productcategoryMapper.selectOne(new QueryWrapper<ProductCategory>().eq("name", productcategoryName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productcategory}] 未知异常");
        }
        if (null == productcategory){
        throw new ApiException("当前查询的:[{productcategory}]不存在");
        }
        return productcategory;
   }

   @Override
   public boolean updateProductCategory(ProductCategory productcategory){
        int row = 0;
        try {
        row = productcategoryMapper.update(productcategory, new UpdateWrapper<ProductCategory>().eq("id", productcategory.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productcategory}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductCategory(ProductCategory productcategory){
        int row = 0;
        try {
        row = productcategoryMapper.insert(productcategory);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productcategory}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductCategory(Integer id){
        int row = 0;
        try {
        row = productcategoryMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productcategory}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductCategoryList(List<Integer> ids) {
        int row = 0;
        try {
        row = productcategoryMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productcategory}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductCategoryList(Integer pageNo,Integer size){
        Page<ProductCategory> page = new Page<>(pageNo,size);
        Page productcategoryPage = productcategoryMapper.selectPage(page, new QueryWrapper<ProductCategory>());
        return productcategoryPage;
   }
}
