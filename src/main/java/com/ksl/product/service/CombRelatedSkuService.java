package com.ksl.product.service;

import com.ksl.product.entity.CombRelatedSku;
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
public interface CombRelatedSkuService extends IService<CombRelatedSku> {
    /**
     * 根据CombRelatedSku的id获取当前对象
     * @param id
     * @return CombRelatedSku
     */
    CombRelatedSku getCombRelatedSkuInfoById(Integer id);

    /**
    * 根据CombRelatedSku的名称获取当前对象
    * @param combrelatedskuName
    * @return CombRelatedSku
    */
    CombRelatedSku getCombRelatedSkuInfoByName(String combrelatedskuName);

    /**
    * 修改
    * @param combrelatedsku
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateCombRelatedSku(CombRelatedSku combrelatedsku);

    /**
    * 新增
    * @param combrelatedsku
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertCombRelatedSku(CombRelatedSku combrelatedsku);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteCombRelatedSku(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteCombRelatedSkuList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<CombRelatedSku>
    */
    Page getCombRelatedSkuList(Integer pageNo, Integer size);
}
