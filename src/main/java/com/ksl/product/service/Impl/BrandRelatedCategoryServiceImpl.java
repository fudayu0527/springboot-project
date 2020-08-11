package com.ksl.product.service.Impl;

import com.ksl.product.entity.BrandRelatedCategory;
import com.ksl.product.mapper.BrandRelatedCategoryMapper;
import com.ksl.product.service.BrandRelatedCategoryService;
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
 * 品牌关联类目表 一个品牌可以关联多个一级或二级类目  服务实现类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
@Slf4j
@Service
public class BrandRelatedCategoryServiceImpl extends ServiceImpl<BrandRelatedCategoryMapper, BrandRelatedCategory> implements BrandRelatedCategoryService {

   @Resource
   private BrandRelatedCategoryMapper brandrelatedcategoryMapper;

   /**
   * 注意当前的id主键 是默认的"id"，具体情况根据数据库主键的名称进行修改
   */
   @Override
   public BrandRelatedCategory getBrandRelatedCategoryInfoById(Integer id){
    BrandRelatedCategory brandrelatedcategory = null;
        try {
    brandrelatedcategory = brandrelatedcategoryMapper.selectOne(new QueryWrapper<BrandRelatedCategory>().eq("id", id));
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null == brandrelatedcategory){
        throw new ApiException("当前查询的:[{brandrelatedcategory}]不存在");
        }
        return brandrelatedcategory;
   }

   /**
   * 注意当前的name 名称 是默认的"name"，具体情况根据数据库对应的名称进行修改
   */
   @Override
   public BrandRelatedCategory getBrandRelatedCategoryInfoByName(String brandrelatedcategoryName){
    BrandRelatedCategory brandrelatedcategory = null;
        try {
    brandrelatedcategory = brandrelatedcategoryMapper.selectOne(new QueryWrapper<BrandRelatedCategory>().eq("name", brandrelatedcategoryName));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedcategory}] 未知异常");
        }
        if (null == brandrelatedcategory){
        throw new ApiException("当前查询的:[{brandrelatedcategory}]不存在");
        }
        return brandrelatedcategory;
   }

   @Override
   public boolean updateBrandRelatedCategory(BrandRelatedCategory brandrelatedcategory){
        int row = 0;
        try {
        row = brandrelatedcategoryMapper.update(brandrelatedcategory, new UpdateWrapper<BrandRelatedCategory>().eq("id", brandrelatedcategory.getId()));
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedcategory}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean insertBrandRelatedCategory(BrandRelatedCategory brandrelatedcategory){
        int row = 0;
        try {
        row = brandrelatedcategoryMapper.insert(brandrelatedcategory);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedcategory}] 未知异常");
        }
        return row > 0;
   }

   @Override
   public boolean deleteBrandRelatedCategory(Integer id){
        int row = 0;
        try {
        row = brandrelatedcategoryMapper.deleteById(id);
        } catch (Exception e) {
           e.printStackTrace();
           log.error("[{brandrelatedcategory}] 未知异常");
        }
        return row > 0;
   }

    @Override
    public boolean deleteBrandRelatedCategoryList(List<Integer> ids) {
        int row = 0;
        try {
        row = brandrelatedcategoryMapper.deleteBatchIds(ids);
        } catch (Exception e) {
        e.printStackTrace();
        log.error("[{brandrelatedcategory}] 未知异常");
        }
        return row>0;
   }

   @Override
   public Page getBrandRelatedCategoryList(Integer pageNo,Integer size){
        Page<BrandRelatedCategory> page = new Page<>(pageNo,size);
        Page brandrelatedcategoryPage = brandrelatedcategoryMapper.selectPage(page, new QueryWrapper<BrandRelatedCategory>());
        return brandrelatedcategoryPage;
   }
}
