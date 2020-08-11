package com.ksl.product.service;

import com.ksl.product.entity.ProductCategory;
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
public interface ProductCategoryService extends IService<ProductCategory> {
    /**
     * 根据ProductCategory的id获取当前对象
     * @param id
     * @return ProductCategory
     */
    ProductCategory getProductCategoryInfoById(Integer id);

    /**
    * 根据ProductCategory的名称获取当前对象
    * @param productcategoryName
    * @return ProductCategory
    */
    ProductCategory getProductCategoryInfoByName(String productcategoryName);

    /**
    * 修改
    * @param productcategory
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductCategory(ProductCategory productcategory);

    /**
    * 新增
    * @param productcategory
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductCategory(ProductCategory productcategory);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductCategory(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductCategoryList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductCategory>
    */
    Page getProductCategoryList(Integer pageNo, Integer size);
}
