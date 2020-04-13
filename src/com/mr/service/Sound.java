package com.mr.service;

import java.io.FileNotFoundException;

/**
 * 音效工具类，定义了游戏中所有的有效文件，并且为每一个音效单独编写播放方法
 */
public class Sound {
    static final String DIR = "music/";                 // 音乐文件夹
    static final String BACKGROUND = "background.wav";  // 背景音乐
    static final String JUMP = "jump.wav";              // 跳跃音效
    static final String HIT = "hit.wav";                // 撞击音效

    /**
     * 播放声音
     * @param file 音乐文件完整名称
     * @param circulate 是否循环播放
     */
    private static void play(String file, boolean circulate) {
        try {
            MusicPlayer player = new MusicPlayer(file, circulate);      // 创建播放器
            player.play();                                              // 播放器开始播放
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放跳跃音效
     */
    static public void jump() {
        play(DIR + JUMP, false);                        // 播放一次跳跃音乐
    }

    /**
     * 播放撞击音效
     */
    static public void hit() {
        play(DIR + HIT, false);                         // 播放一次撞击音效
    }

    /**
     * 播放背景音乐
     */
    static public void background() {
        play(DIR + BACKGROUND, true);                   // 循环播放背景音乐
    }
}

