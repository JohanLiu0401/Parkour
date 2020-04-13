package com.mr.modle;

import com.mr.view.BackgroundImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 游戏中的障碍有两种，不管是哪种，都会随背景一起移动，都有可以碰撞的区域。
 */
public class Obstacle {
    public int x, y;                            // 横纵坐标
    public BufferedImage image;
    private BufferedImage stone;                // 石头图片
    private BufferedImage cacti;                // 仙人掌图片
    private int speed;                          // 移动速度

    /**
     * 障碍物构造方法
     */
    public Obstacle() {
        try {
            stone = ImageIO.read(new File("image/stone.png"));
            cacti = ImageIO.read(new File("image/cacti.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();                // 创建随机对象
        if (r.nextInt(2) == 0) {         // 从0和1中取一值，若为0
            image = cacti;                      // 使用仙人掌图片
        } else {
            image = stone;                      // 否则采用石头照片
        }
        x = 800;                                // 初始化横坐标
        y = 200 - image.getHeight();            // 纵坐标
        speed = BackgroundImage.SPEED;          // 移动速度与背景同步
    }

    /**
     * 障碍移动方法
     */
    public void move() {
        x -= speed;
    }

    /**
     * 获取障碍物的有效状态，若障碍物在画面之外返回false，否则返回true
     * @return boolean
     */
    public boolean isLive() {
        if (x <= -image.getWidth()) {
            return false;
        }
        return true;
    }

    /**
     * 获得障碍物的边界对象
     * @return 边界对象
     */
    public Rectangle getBounds() {
        if (image == cacti) {
            return new Rectangle(x + 10, y + 10, 30, image.getHeight());   // 树
        }
        return new Rectangle(x + 10, y + 15, 50, image.getHeight());      // 冰块
    }

}
