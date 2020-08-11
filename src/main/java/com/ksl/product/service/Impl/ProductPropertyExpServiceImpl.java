package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductPropertyExp;
import com.ksl.product.mapper.ProductPropertyExpMapper;
import com.ksl.product.service.ProductPropertyExpService;
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
 * 属性值拓展表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductPropertyExpServiceImpl extends ServiceImpl<ProductPropertyExpMapper, ProductPropertyExp> implements ProductPropertyExpService {

   @Resource
   private ProductPropertyExpMapper productpropertyexpMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductPropertyExp getProductPropertyExpInfoById(Integer id){
    ProductPropertyExp productpropertyexp = null;
        try {
    productpropertyexp = productpropertyexpMapper.selectOne(new QueryWrapper<ProductPropertyExp>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productpropertyexp){
        throw new ApiException("当前查询的:[{productpropertyexp}]不存在");
        }
        return productpropertyexp;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductPropertyExp getProductPropertyExpInfoByName(String productpropertyexpName){
    ProductPropertyExp productpropertyexp = null;
        try {
    productpropertyexp = productpropertyexpMapper.selectOne(new QueryWrapper<ProductPropertyExp>().eq("name", productpropertyexpName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productpropertyexp}] 未知异常");
        }
        if (null == productpropertyexp){
        throw new ApiException("当前查询的:[{productpropertyexp}]不存在");
        }
        return productpropertyexp;
   }

   @Override
   public boolean updateProductPropertyExp(ProductPropertyExp productpropertyexp){
        int row = 0;
        try {
        row = productpropertyexpMapper.update(productpropertyexp, new UpdateWrapper<ProductPropertyExp>().eq("id", productpropertyexp.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productpropertyexp}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductPropertyExp(ProductPropertyExp productpropertyexp){
        int row = 0;
        try {
        row = productpropertyexpMapper.insert(productpropertyexp);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productpropertyexp}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductPropertyExp(Integer id){
        int row = 0;
        try {
        row = productpropertyexpMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productpropertyexp}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductPropertyExpList(List<Integer> ids) {
        int row = 0;
        try {
        row = productpropertyexpMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productpropertyexp}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductPropertyExpList(Integer pageNo,Integer size){
        Page<ProductPropertyExp> page = new Page<>(pageNo,size);
        Page productpropertyexpPage = productpropertyexpMapper.selectPage(page, new QueryWrapper<ProductPropertyExp>());
        return productpropertyexpPage;
   }
}
