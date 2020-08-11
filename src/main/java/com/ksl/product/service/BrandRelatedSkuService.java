package com.ksl.product.service;

import com.ksl.product.entity.BrandRelatedSku;
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
public interface BrandRelatedSkuService extends IService<BrandRelatedSku> {
    /**
     * 根据BrandRelatedSku的id获取当前对象
     * @param id
     * @return BrandRelatedSku
     */
    BrandRelatedSku getBrandRelatedSkuInfoById(Integer id);

    /**
    * 根据BrandRelatedSku的名称获取当前对象
    * @param brandrelatedskuName
    * @return BrandRelatedSku
    */
    BrandRelatedSku getBrandRelatedSkuInfoByName(String brandrelatedskuName);

    /**
    * 修改
    * @param brandrelatedsku
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateBrandRelatedSku(BrandRelatedSku brandrelatedsku);

    /**
    * 新增
    * @param brandrelatedsku
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertBrandRelatedSku(BrandRelatedSku brandrelatedsku);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteBrandRelatedSku(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteBrandRelatedSkuList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<BrandRelatedSku>
    */
    Page getBrandRelatedSkuList(Integer pageNo, Integer size);
}
