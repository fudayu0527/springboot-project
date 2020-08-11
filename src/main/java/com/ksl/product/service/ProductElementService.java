package com.ksl.product.service;

import com.ksl.product.entity.ProductElement;
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
public interface ProductElementService extends IService<ProductElement> {
    /**
     * 根据ProductElement的id获取当前对象
     * @param id
     * @return ProductElement
     */
    ProductElement getProductElementInfoById(Integer id);

    /**
    * 根据ProductElement的名称获取当前对象
    * @param productelementName
    * @return ProductElement
    */
    ProductElement getProductElementInfoByName(String productelementName);

    /**
    * 修改
    * @param productelement
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductElement(ProductElement productelement);

    /**
    * 新增
    * @param productelement
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductElement(ProductElement productelement);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductElement(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductElementList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductElement>
    */
    Page getProductElementList(Integer pageNo, Integer size);
}
