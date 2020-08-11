package com.ksl.product.service;

import com.ksl.product.entity.ProductProperty;
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
public interface ProductPropertyService extends IService<ProductProperty> {
    /**
     * 根据ProductProperty的id获取当前对象
     * @param id
     * @return ProductProperty
     */
    ProductProperty getProductPropertyInfoById(Integer id);

    /**
    * 根据ProductProperty的名称获取当前对象
    * @param productpropertyName
    * @return ProductProperty
    */
    ProductProperty getProductPropertyInfoByName(String productpropertyName);

    /**
    * 修改
    * @param productproperty
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductProperty(ProductProperty productproperty);

    /**
    * 新增
    * @param productproperty
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductProperty(ProductProperty productproperty);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductProperty(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductPropertyList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductProperty>
    */
    Page getProductPropertyList(Integer pageNo, Integer size);
}
