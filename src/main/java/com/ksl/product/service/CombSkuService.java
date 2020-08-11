package com.ksl.product.service;

import com.ksl.product.entity.CombSku;
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
public interface CombSkuService extends IService<CombSku> {
    /**
     * 根据CombSku的id获取当前对象
     * @param id
     * @return CombSku
     */
    CombSku getCombSkuInfoById(Integer id);

    /**
    * 根据CombSku的名称获取当前对象
    * @param combskuName
    * @return CombSku
    */
    CombSku getCombSkuInfoByName(String combskuName);

    /**
    * 修改
    * @param combsku
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateCombSku(CombSku combsku);

    /**
    * 新增
    * @param combsku
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertCombSku(CombSku combsku);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteCombSku(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteCombSkuList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<CombSku>
    */
    Page getCombSkuList(Integer pageNo, Integer size);
}
