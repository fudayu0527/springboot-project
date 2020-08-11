package com.ksl.product.service;

import com.ksl.product.entity.SkuSameRelated;
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
public interface SkuSameRelatedService extends IService<SkuSameRelated> {
    /**
     * 根据SkuSameRelated的id获取当前对象
     * @param id
     * @return SkuSameRelated
     */
    SkuSameRelated getSkuSameRelatedInfoById(Integer id);

    /**
    * 根据SkuSameRelated的名称获取当前对象
    * @param skusamerelatedName
    * @return SkuSameRelated
    */
    SkuSameRelated getSkuSameRelatedInfoByName(String skusamerelatedName);

    /**
    * 修改
    * @param skusamerelated
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateSkuSameRelated(SkuSameRelated skusamerelated);

    /**
    * 新增
    * @param skusamerelated
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertSkuSameRelated(SkuSameRelated skusamerelated);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuSameRelated(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuSameRelatedList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<SkuSameRelated>
    */
    Page getSkuSameRelatedList(Integer pageNo, Integer size);
}
