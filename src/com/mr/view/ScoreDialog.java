package com.mr.view;

import com.mr.service.ScoreRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDialog extends JDialog {

    public ScoreDialog(JFrame frame) {
        super(frame, true);                                                 // 调用父类构造方法, 阻塞父窗体
        int scores[] = ScoreRecorder.getScores();                           // 获取当前的前三名成绩
        JPanel scoreP = new JPanel(new GridLayout(4, 1));       //  成绩面板, 4行1列
        scoreP.setBackground(Color.WHITE);                                  // 白色背景
        JLabel title = new JLabel("得分排行", JLabel.CENTER);           // 标题标签，局中
        title.setFont(new Font("黑体", Font.BOLD, 20));         // 设置字体
        title.setForeground(Color.RED);                                     // 红色前景色

        // 第一名标签，居中显示
        JLabel first = new JLabel("第一名: "+ scores[2], JLabel.CENTER);

        // 第二名标签，居中显示
        JLabel second = new JLabel("第二名: "+ scores[2], JLabel.CENTER);

        // 第三名标签，居中显示
        JLabel third = new JLabel("第三名: "+ scores[2], JLabel.CENTER);


        JPanel buttonP = new JPanel(new GridLayout(1, 2));                                      // 按钮面板
        JButton restart = new JButton("重新开始");                      // 重新开始按钮
        JButton close = new JButton( "关闭游戏");
        restart.addActionListener(new ActionListener() {                    // 按钮添加事件监听
            @Override
            public void actionPerformed(ActionEvent e) {                    // 当点击时
                dispose();                                                  // 销毁对话框
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        scoreP.add(title);                                                  // 成绩面板添加标签
        scoreP.add(first);
        scoreP.add(second);
        scoreP.add(third);
        buttonP.add(restart);
        buttonP.add(close);

        Container c = getContentPane();                                     // 获取主容器
        c.setLayout(new BorderLayout());                                    // 使用边界布局
        c.add(scoreP, BorderLayout.CENTER);                                 // 成绩面板放中间
        c.add(buttonP, BorderLayout.SOUTH);                                 // 按钮放底部


        setTitle("游戏结束");                                                // 对话框标题
        int width, height;
        width = height = 200;                                               // 对话框均为200
        // 获得主窗体中居中位置的横坐标
        int x = frame.getX() + (frame.getWidth() - width) / 2;
        // 获得主窗体中居中位置的纵坐标
        int y = frame.getY() + (frame.getHeight() - height) / 2;
        setBounds(x, y, width, height);                                     // 设置坐标和宽高
        setVisible(true);                                                   // 显示对话框
    }

}
