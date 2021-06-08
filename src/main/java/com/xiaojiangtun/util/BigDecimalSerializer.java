package com.xiaojiangtun.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 序列化 保留两位小数并去除小数点后的末位0
 * @Author: zhangyong
 * @Date: 2020/4/16 11:47
 */
@Component
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bigDecimal==null) {
            return;
        }
        jsonGenerator.writeNumber(bigDecimal.setScale(2, RoundingMode.FLOOR).stripTrailingZeros().toPlainString());
    }
}
