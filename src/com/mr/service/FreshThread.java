package com.mr.service;

import com.mr.frame.GamePanel;
import com.mr.view.MainFrame;
import com.mr.view.ScoreDialog;

import java.awt.*;

public class FreshThread extends Thread {
    public static final int FREASH = 10;
    GamePanel p;

    public FreshThread(GamePanel p) {
        this.p = p;
    }

    public void run() {
        while (!p.isFinish()) {
            p.repaint();
            try {
                Thread.sleep(FREASH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Container c = p.getParent();                        // 获取面板父容器
        while (!(c instanceof MainFrame)) {                 // 如果父容器不是主窗体类
            c = c.getParent();                              // 继续获取父容器的父容器
        }
        MainFrame frame = (MainFrame)c;                     // 将容器强制转换为主窗体类
        new ScoreDialog(frame);                             // 弹出的成绩对话框
        frame.restart();                                    // 主窗体重载开始游戏
    }
}
