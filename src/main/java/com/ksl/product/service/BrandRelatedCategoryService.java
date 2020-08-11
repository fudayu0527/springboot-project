package com.ksl.product.service;

import com.ksl.product.entity.BrandRelatedCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ksl
 * @since 2021-11-19
 */
public interface BrandRelatedCategoryService extends IService<BrandRelatedCategory> {
    /**
     * 根据BrandRelatedCategory的id获取当前对象
     * @param id
     * @return BrandRelatedCategory
     */
    BrandRelatedCategory getBrandRelatedCategoryInfoById(Integer id);

    /**
    * 根据BrandRelatedCategory的名称获取当前对象
    * @param brandrelatedcategoryName
    * @return BrandRelatedCategory
    */
    BrandRelatedCategory getBrandRelatedCategoryInfoByName(String brandrelatedcategoryName);

    /**
    * 修改
    * @param brandrelatedcategory
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateBrandRelatedCategory(BrandRelatedCategory brandrelatedcategory);

    /**
    * 新增
    * @param brandrelatedcategory
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertBrandRelatedCategory(BrandRelatedCategory brandrelatedcategory);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteBrandRelatedCategory(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteBrandRelatedCategoryList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<BrandRelatedCategory>
    */
    Page getBrandRelatedCategoryList(Integer pageNo, Integer size);
}
