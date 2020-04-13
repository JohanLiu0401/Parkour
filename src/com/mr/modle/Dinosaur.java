package com.mr.modle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.mr.service.FreshThread;
import com.mr.service.Sound;

import static com.mr.service.FreshThread.FREASH;

public class Dinosaur {
    public BufferedImage image;                             // 主图片
    private BufferedImage image1, image2, image3;           // 跑步图片
    public int x, y;                                        // 坐标
    private int jumpValue = 0;                              // 跳跃的增变量
    private boolean jumpState = false;                      // 跳跃状态
    private int stepTimer = 0;                              // 踏步计时器
    private final int JUMP_HIGHT = 120;                     // 跳起最大高度
    private final int LOWEST_Y = 120;                       // 落地最低坐标

    public Dinosaur() {
        x = 50;                                             // 跑酷对象横坐标固定在50像素的位置
        y = LOWEST_Y;                                       // 跑酷对象纵坐标为落地时的坐标
        try {
            image1 = ImageIO.read(new File("image/dinosaur_1.png"));
            image2 = ImageIO.read(new File("image/dinosaur_2.png"));
            image3 = ImageIO.read(new File("image/dinosaur_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 踏步动作
     */
    private void step() {
        // 每过250毫秒，更换一张图片，因为共有3张图片，所以除以3取余，轮流展示这3张
        int tmp = stepTimer / 50 % 3;
        switch (tmp) {
            case 1:
                image = image1;
                break;
            case 2:
                image = image2;
                break;
            default:
                image = image3;
        }
        stepTimer += FREASH;
    }

    /**
     * 跳跃动作
     */
    public void jump() {
        if (!jumpState) {
            Sound.jump();       // 如果不处于跳跃状态，则播放跳跃状态
        }
        jumpState = true;       // 将状态改为处于跳跃状态
    }

    /**
     * 移动方法
     * 该方法将所有动作效果封装起来，然后交由游戏面板调用，每一帧画面都会执行一次move()方法。
     */
    public void move() {
        step();
        if (jumpState) {                                    // 如果正在跳跃
            if (y >= LOWEST_Y) {                            // 如果纵坐标大于等于最低点
                jumpValue = -4;                             // 增变量为负值
            }
            if (y <= LOWEST_Y - JUMP_HIGHT) {               // 如果跳过最高点
                jumpValue = 4;                              // 增变量为正值
            }
            y += jumpValue;                                 // 改变纵坐标
            if ( y>= LOWEST_Y) {                            // 如果再次落地
                jumpState = false;                          // 将状态改为非跳跃状态
            }
        }
    }

    /**
     * 获取脚部边界对象
     * @return 脚部边界对象
     */
    public Rectangle getFootBounds() {
        return new Rectangle(x + 30, y + 59, 29, 18);
    }

    /**
     * 获取头部边界
     * @return  头部边界对象
     */
    public Rectangle getHeadBounds() {
        return new Rectangle(x + 66, y + 25, 32, 22);
    }
}
