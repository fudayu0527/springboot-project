package com.ksl.product.service;

import com.ksl.product.entity.SkuStock;
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
public interface SkuStockService extends IService<SkuStock> {
    /**
     * 根据SkuStock的id获取当前对象
     * @param id
     * @return SkuStock
     */
    SkuStock getSkuStockInfoById(Integer id);

    /**
    * 根据SkuStock的名称获取当前对象
    * @param skustockName
    * @return SkuStock
    */
    SkuStock getSkuStockInfoByName(String skustockName);

    /**
    * 修改
    * @param skustock
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateSkuStock(SkuStock skustock);

    /**
    * 新增
    * @param skustock
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertSkuStock(SkuStock skustock);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuStock(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuStockList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<SkuStock>
    */
    Page getSkuStockList(Integer pageNo, Integer size);
}
