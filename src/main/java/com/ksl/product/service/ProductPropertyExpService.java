package com.ksl.product.service;

import com.ksl.product.entity.ProductPropertyExp;
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
public interface ProductPropertyExpService extends IService<ProductPropertyExp> {
    /**
     * 根据ProductPropertyExp的id获取当前对象
     * @param id
     * @return ProductPropertyExp
     */
    ProductPropertyExp getProductPropertyExpInfoById(Integer id);

    /**
    * 根据ProductPropertyExp的名称获取当前对象
    * @param productpropertyexpName
    * @return ProductPropertyExp
    */
    ProductPropertyExp getProductPropertyExpInfoByName(String productpropertyexpName);

    /**
    * 修改
    * @param productpropertyexp
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateProductPropertyExp(ProductPropertyExp productpropertyexp);

    /**
    * 新增
    * @param productpropertyexp
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertProductPropertyExp(ProductPropertyExp productpropertyexp);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductPropertyExp(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteProductPropertyExpList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ProductPropertyExp>
    */
    Page getProductPropertyExpList(Integer pageNo, Integer size);
}
