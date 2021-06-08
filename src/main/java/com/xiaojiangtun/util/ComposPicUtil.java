package com.xiaojiangtun.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

@Slf4j
public class ComposPicUtil {

    public static BufferedImage cmposPic(String pic1,String pic2,String text){
        try {
            Image src1 = ImageIO.read(new URL(pic1));//背景图
            Image src2 = ImageIO.read(new URL(pic2));//二维码
            int backgroundWidth = src1.getWidth(null);
            int backgroundHeight = src1.getHeight(null);
            BufferedImage thumbImage = new BufferedImage(backgroundWidth, backgroundHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = thumbImage.createGraphics();
            thumbImage = g.getDeviceConfiguration().createCompatibleImage(backgroundWidth, backgroundHeight, Transparency.TRANSLUCENT);
            g.dispose();
            g = thumbImage.createGraphics();
            g.drawImage(src1.getScaledInstance(backgroundWidth, backgroundHeight, Image.SCALE_DEFAULT), 0, 0, null);
            g.drawImage(src2.getScaledInstance(220, 220, Image.SCALE_DEFAULT), (backgroundWidth / 2 - 220 / 2), 527, null);

            if (StringUtils.isNotBlank(text)){
                g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g.setColor(new Color(128,29,32));
                g.setFont(new Font("宋体",Font.BOLD,18));
                g.drawString(text+">",55,50);
            }
            return thumbImage;
        }catch (Exception e){
           log.error("图片合成失败:",e);
        }
        return null;

    }
}
