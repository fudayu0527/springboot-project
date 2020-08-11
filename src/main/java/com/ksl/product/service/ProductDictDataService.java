package com.ksl.product.service;

import com.ksl.product.entity.ProductDictData;
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
public interface ProductDictDataService extends IService<ProductDictData> {
    /**
     * 根据ProductDictData的id获取当前对象
     * @param id
     * @return ProductDictData
     */
    ProductDictData getProductDictDataInfoById(Integer id);

    /**
    * 根据ProductDictData的名称获取当前对象
    * @param productdictdataName
    * @return ProductDictData
    */
    ProductDictData getProductDictDataInfoByName(String productdictdataName);

    /**
    * 修改
    * @param productdictdata
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductDictData(ProductDictData productdictdata);

    /**
    * 新增
    * @param productdictdata
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductDictData(ProductDictData productdictdata);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductDictData(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductDictDataList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductDictData>
    */
    Page getProductDictDataList(Integer pageNo, Integer size);
}
