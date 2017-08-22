package com.wonders.core.utils;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class MatrixCodeUtil {

    private static final BASE64Encoder BASE64_ENCODER = new BASE64Encoder();

    private static final String str = "1234567890";

    private static Random random = new Random();

    private static int r(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    /**
     * 将生成的随机码图片生成base64编码字符串，在页面上可直接用img标签显示
     * 示例：<img src="data:image/{format};base64,{XXXX}"/>
     * {format}	图片格式，{XXXX}	编码后字符串
     *
     * @param width  生成图片的宽度
     * @param height 生成图片的高度
     * @return
     * @throws Exception 生成随机码失败
     */
    public static RandomCodeVo createRandomCode(int width, int height) throws Exception {
        RandomCodeVo ranVo = new RandomCodeVo();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //在图片上画一个矩形当背景
        Graphics g = img.getGraphics();
        g.setColor(new Color(r(50, 250), r(50, 250), r(50, 250)));
        g.fillRect(0, 0, width, height);

        String tr = "";
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(r(50, 180), r(50, 180), r(50, 180)));
            g.setFont(new Font("黑体", Font.PLAIN, 40));
            char c = str.charAt(r(0, str.length()));
            g.drawString(String.valueOf(c), 10 + i * 30, r(height - 30, height));
            tr += String.valueOf(c);
        }
        ranVo.setImageCont(tr);

        //画随机线
        for (int i = 0; i < 2; i++) {
            g.setColor(new Color(r(50, 180), r(50, 180), r(50, 180)));
            g.drawLine(r(0, width), r(0, height), r(0, width), r(0, height));
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        if (!ImageIO.write(img, "png", bos)) {
            throw new IOException("Could not write an image of format "
                    + "png");
        }

        ranVo.setImageUrl(BASE64_ENCODER.encode(bos.toByteArray()));

        return ranVo;
    }

    public static class RandomCodeVo {
        /**
         * 验证码图片
         */
        private String imageUrl;
        /**
         * 验证码图片内容
         */
        private String imageCont;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageCont() {
            return imageCont;
        }

        public void setImageCont(String imageCont) {
            this.imageCont = imageCont;
        }

        @Override
        public String toString() {
            return "RandomCodeVo [imageUrl=" + imageUrl + ", imageCont="
                    + imageCont + "]";
        }
    }

}
