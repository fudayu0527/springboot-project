package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductBrand;
import com.ksl.product.mapper.ProductBrandMapper;
import com.ksl.product.service.ProductBrandService;
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
 * 产品品牌表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrand> implements ProductBrandService {

   @Resource
   private ProductBrandMapper productbrandMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductBrand getProductBrandInfoById(Integer id){
    ProductBrand productbrand = null;
        try {
    productbrand = productbrandMapper.selectOne(new QueryWrapper<ProductBrand>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productbrand){
        throw new ApiException("当前查询的:[{productbrand}]不存在");
        }
        return productbrand;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductBrand getProductBrandInfoByName(String productbrandName){
    ProductBrand productbrand = null;
        try {
    productbrand = productbrandMapper.selectOne(new QueryWrapper<ProductBrand>().eq("name", productbrandName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productbrand}] 未知异常");
        }
        if (null == productbrand){
        throw new ApiException("当前查询的:[{productbrand}]不存在");
        }
        return productbrand;
   }

   @Override
   public boolean updateProductBrand(ProductBrand productbrand){
        int row = 0;
        try {
        row = productbrandMapper.update(productbrand, new UpdateWrapper<ProductBrand>().eq("id", productbrand.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productbrand}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductBrand(ProductBrand productbrand){
        int row = 0;
        try {
        row = productbrandMapper.insert(productbrand);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productbrand}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductBrand(Integer id){
        int row = 0;
        try {
        row = productbrandMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productbrand}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductBrandList(List<Integer> ids) {
        int row = 0;
        try {
        row = productbrandMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productbrand}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductBrandList(Integer pageNo,Integer size){
        Page<ProductBrand> page = new Page<>(pageNo,size);
        Page productbrandPage = productbrandMapper.selectPage(page, new QueryWrapper<ProductBrand>());
        return productbrandPage;
   }
}
