package com.ksl.product.service;

import com.ksl.product.entity.SkuCodeRule;
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
public interface SkuCodeRuleService extends IService<SkuCodeRule> {
    /**
     * 根据SkuCodeRule的id获取当前对象
     * @param id
     * @return SkuCodeRule
     */
    SkuCodeRule getSkuCodeRuleInfoById(Integer id);

    /**
    * 根据SkuCodeRule的名称获取当前对象
    * @param skucoderuleName
    * @return SkuCodeRule
    */
    SkuCodeRule getSkuCodeRuleInfoByName(String skucoderuleName);

    /**
    * 修改
    * @param skucoderule
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateSkuCodeRule(SkuCodeRule skucoderule);

    /**
    * 新增
    * @param skucoderule
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertSkuCodeRule(SkuCodeRule skucoderule);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuCodeRule(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteSkuCodeRuleList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<SkuCodeRule>
    */
    Page getSkuCodeRuleList(Integer pageNo, Integer size);
}
