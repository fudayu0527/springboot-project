package com.ksl.product.service;

import com.ksl.product.entity.ProductBrand;
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
public interface ProductBrandService extends IService<ProductBrand> {
    /**
     * 根据ProductBrand的id获取当前对象
     * @param id
     * @return ProductBrand
     */
    ProductBrand getProductBrandInfoById(Integer id);

    /**
    * 根据ProductBrand的名称获取当前对象
    * @param productbrandName
    * @return ProductBrand
    */
    ProductBrand getProductBrandInfoByName(String productbrandName);

    /**
    * 修改
    * @param productbrand
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductBrand(ProductBrand productbrand);

    /**
    * 新增
    * @param productbrand
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductBrand(ProductBrand productbrand);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductBrand(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductBrandList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductBrand>
    */
    Page getProductBrandList(Integer pageNo, Integer size);
}
