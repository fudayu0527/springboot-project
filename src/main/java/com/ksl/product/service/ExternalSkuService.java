package com.ksl.product.service;

import com.ksl.product.entity.ExternalSku;
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
public interface ExternalSkuService extends IService<ExternalSku> {
    /**
     * 根据ExternalSku的id获取当前对象
     * @param id
     * @return ExternalSku
     */
    ExternalSku getExternalSkuInfoById(Integer id);

    /**
    * 根据ExternalSku的名称获取当前对象
    * @param externalskuName
    * @return ExternalSku
    */
    ExternalSku getExternalSkuInfoByName(String externalskuName);

    /**
    * 修改
    * @param externalsku
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateExternalSku(ExternalSku externalsku);

    /**
    * 新增
    * @param externalsku
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertExternalSku(ExternalSku externalsku);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteExternalSku(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteExternalSkuList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<ExternalSku>
    */
    Page getExternalSkuList(Integer pageNo, Integer size);
}
