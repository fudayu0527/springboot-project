package com.ksl.product.service.Impl;

import com.ksl.product.entity.ProductDictData;
import com.ksl.product.mapper.ProductDictDataMapper;
import com.ksl.product.service.ProductDictDataService;
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
 * 产品数据字典表   服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class ProductDictDataServiceImpl extends ServiceImpl<ProductDictDataMapper, ProductDictData> implements ProductDictDataService {

   @Resource
   private ProductDictDataMapper productdictdataMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public ProductDictData getProductDictDataInfoById(Integer id){
    ProductDictData productdictdata = null;
        try {
    productdictdata = productdictdataMapper.selectOne(new QueryWrapper<ProductDictData>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == productdictdata){
        throw new ApiException("当前查询的:[{productdictdata}]不存在");
        }
        return productdictdata;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public ProductDictData getProductDictDataInfoByName(String productdictdataName){
    ProductDictData productdictdata = null;
        try {
    productdictdata = productdictdataMapper.selectOne(new QueryWrapper<ProductDictData>().eq("name", productdictdataName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdictdata}] 未知异常");
        }
        if (null == productdictdata){
        throw new ApiException("当前查询的:[{productdictdata}]不存在");
        }
        return productdictdata;
   }

   @Override
   public boolean updateProductDictData(ProductDictData productdictdata){
        int row = 0;
        try {
        row = productdictdataMapper.update(productdictdata, new UpdateWrapper<ProductDictData>().eq("id", productdictdata.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdictdata}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertProductDictData(ProductDictData productdictdata){
        int row = 0;
        try {
        row = productdictdataMapper.insert(productdictdata);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdictdata}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteProductDictData(Integer id){
        int row = 0;
        try {
        row = productdictdataMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{productdictdata}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteProductDictDataList(List<Integer> ids) {
        int row = 0;
        try {
        row = productdictdataMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{productdictdata}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getProductDictDataList(Integer pageNo,Integer size){
        Page<ProductDictData> page = new Page<>(pageNo,size);
        Page productdictdataPage = productdictdataMapper.selectPage(page, new QueryWrapper<ProductDictData>());
        return productdictdataPage;
   }
}
