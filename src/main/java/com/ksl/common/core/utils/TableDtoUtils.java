package com.ksl.common.core.utils;



import com.ksl.common.core.domain.TableDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ksl
 * @date 2021/2/8 - 21:04
 */
public class TableDtoUtils {

    /**
     * 补0操作，不满4位前面都是0
     *
     * @param num 编号
     * @return 补零过后的数字
     */
    public static String zeroFillFour(Integer num) {
        return String.format("%04d", num);
    }

    /**
     * 补0操作，不满3位前面都是0
     *
     * @param num 编号
     * @return 补零过后的数字
     */
    public static String zeroFillThree(Integer num) {
        return String.format("%03d", num);
    }

    /**
     * 获取信息的封装
     *
     * @param total 总记录条数
     * @param obj   数据对象
     * @return layui数据表格渲染的格式
     */
    public static TableDto setTableDto(long total, Object obj) {
        TableDto tableDto = new TableDto();
        tableDto.setCode(0);
        tableDto.setCount(total);
        tableDto.setMsg("");
        tableDto.setData(obj);
        return tableDto;
    }

    /**
     * 无数据信息的封装
     */
    public static TableDto setTableDto() {
        TableDto tableDto = new TableDto();
        tableDto.setCode(0);
        tableDto.setCount(0);
        tableDto.setMsg("无数据");
        tableDto.setData(null);
        return tableDto;
    }

    /**
     * 获取两个包含关系集合中不同的元素
     * strings1 比较全
     * strings2
     */
    public List<String> getDifferent(List<String> strings1, List<String> strings2) {
        List<String> differentStrs = new ArrayList<>();
        for (String s : strings1) {
            if (!strings2.contains(s)) {
                differentStrs.add(s);
            }
        }
        return differentStrs;
    }
    public List<Integer> getDifferents(List<Integer> strings1, List<Integer> strings2) {
        List<Integer> differentStrs = new ArrayList<>();
        for (Integer s : strings1) {
            if (!strings2.contains(s)) {
                differentStrs.add(s);
            }
        }
        return differentStrs;
    }
}
