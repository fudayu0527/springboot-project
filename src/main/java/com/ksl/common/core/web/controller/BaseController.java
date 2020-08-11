package com.ksl.common.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ksl.common.core.constant.HttpStatus;
import com.ksl.common.core.domain.Result;
import com.ksl.common.core.utils.DateUtils;
import com.ksl.common.core.utils.sql.SqlUtil;
import com.ksl.common.core.web.page.PageDomain;
import com.ksl.common.core.web.page.TableDataInfo;
import com.ksl.common.core.web.page.TableSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author ksl
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected <T> TableDataInfo<T> getDataTable(List<T> list) {
        TableDataInfo<T> rspData = new TableDataInfo<T>();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected Result<Void> toAjax(int rows) {
        return rows > 0 ? Result.success() : Result.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected Result<Void> toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public Result<Void> success() {
        return Result.success();
    }

    /**
     * 返回失败消息
     */
    public Result<Void> error() {
        return Result.error();
    }

    /**
     * 返回成功消息
     */
    public Result<Void> success(String message) {
        return Result.successMsg(message);
    }

    /**
     * 返回失败消息
     */
    public Result<Void> error(String message) {
        return Result.error(message);
    }
}
