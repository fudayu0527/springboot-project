package com.ksl.product.service;

import com.ksl.product.entity.Sku;
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
public interface SkuService extends IService<Sku> {
    /**
     * 根据Sku的id获取当前对象
     * @param id
     * @return Sku
     */
    Sku getSkuInfoById(Integer id);

    /**
    * 根据Sku的名称获取当前对象
    * @param skuName
    * @return Sku
    */
    Sku getSkuInfoByName(String skuName);

    /**
    * 修改
    * @param sku
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateSku(Sku sku);

    /**
    * 新增
    * @param sku
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertSku(Sku sku);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSku(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<Sku>
    */
    Page getSkuList(Integer pageNo, Integer size);
}
