package com.mr.view;

import com.mr.frame.GamePanel;
import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public MainFrame() {
        restart();                                              // 开始
        setBounds(340, 150, 820, 260);       // 设置最外层窗体横纵坐标和宽高
        setTitle("跑酷");                                        // 设置标题
        Sound.background();                                     // 播放背景音乐
        ScoreRecorder.init();                                   // 读取得分纪录
        addListener();                                          // 添加监听
        setDefaultCloseOperation(EXIT_ON_CLOSE);                // 关闭窗体则停止程序
    }

    public void restart() {
        Container c = getContentPane();                         // 获取主容器对象
        c.removeAll();                                          // 删除容器中的所有组件
        GamePanel panel = new GamePanel();                      // 创建新的游戏面板
        c.add(panel);
        addKeyListener(panel);                                  // 添加键盘事件
        c.validate();                                           // 容器重新验证所有组件
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {                 // 添加窗体监听
            @Override
            public void windowOpened(WindowEvent e) {           // 窗体关闭前
                ScoreRecorder.saveScore();                      // 保存得分纪录
            }
        });
    }
}

