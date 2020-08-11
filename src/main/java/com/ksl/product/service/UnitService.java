package com.ksl.product.service;

import com.ksl.product.entity.Unit;
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
public interface UnitService extends IService<Unit> {
    /**
     * 根据Unit的id获取当前对象
     * @param id
     * @return Unit
     */
    Unit getUnitInfoById(Integer id);

    /**
    * 根据Unit的名称获取当前对象
    * @param unitName
    * @return Unit
    */
    Unit getUnitInfoByName(String unitName);

    /**
    * 修改
    * @param unit
    * @return  true代表更新成功  false代表更新失败
    */
    boolean updateUnit(Unit unit);

    /**
    * 新增
    * @param unit
    * @return true代表新增成功  false代表新增失败
    */
    boolean insertUnit(Unit unit);

    /**
    * 删除
    * @param id
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteUnit(Integer id);

    /**
    * 批量删除
    * @param ids 要删除的集合列表
    * @return true代表删除成功  false代表删除失败
    */
    boolean deleteUnitList(List<Integer> ids);

    /**
    * 分页查询
    * @param pageNo  页数
    * @param size    一页最大的条数
    * @return  Page<Unit>
    */
    Page getUnitList(Integer pageNo, Integer size);
}
