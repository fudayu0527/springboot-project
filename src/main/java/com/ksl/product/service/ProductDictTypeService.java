package com.ksl.product.service;

import com.ksl.product.entity.ProductDictType;
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
public interface ProductDictTypeService extends IService<ProductDictType> {
    /**
     * 根据ProductDictType的id获取当前对象
     * @param id
     * @return ProductDictType
     */
    ProductDictType getProductDictTypeInfoById(Integer id);

    /**
    * 根据ProductDictType的名称获取当前对象
    * @param productdicttypeName
    * @return ProductDictType
    */
    ProductDictType getProductDictTypeInfoByName(String productdicttypeName);

    /**
    * 修改
    * @param productdicttype
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductDictType(ProductDictType productdicttype);

    /**
    * 新增
    * @param productdicttype
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductDictType(ProductDictType productdicttype);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductDictType(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductDictTypeList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductDictType>
    */
    Page getProductDictTypeList(Integer pageNo, Integer size);
}
