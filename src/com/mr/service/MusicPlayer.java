package com.mr.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable {
    File soundFile;                     // 音乐文件
    Thread thread;                      // 父线程
    boolean circulate;                  // 是否循环播放

    /**
     * 构造方法
     * @param filepath 音乐文件的完整文件名
     * @param circulate 是否循环播放
     * @throws FileNotFoundException
     */
    public MusicPlayer(String filepath, boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if (!soundFile.exists()) {
            throw new FileNotFoundException(filepath + "未找到");
        }
    }

    /**
     * 声明了一个128k的缓冲区字节数组，程序以不断循环的方式将音乐文件以音频输入流格式读入缓冲区，再把缓冲区的数据
     * 写入混音器源数据行中，这样就可以不断向外部设备发送音频信号，实现播放音乐效果
     */
    public void run() {
        byte[] auBuffer = new byte[1024 * 128];             // 创建128k缓冲区
        do {
            AudioInputStream audioInputStream = null;       // 创建音频输入流对象
            SourceDataLine auline = null;                   // 混频器源数据行
            try {
                // 从音乐文件中获取音频输入流
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                // 获取音频格式
                AudioFormat format = audioInputStream.getFormat();

                // 按照源数据行类型和指定音频格式创建数据行对象
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

                // 利用音频系统类获得与指定Line.Info对象中的描述匹配的行对象
                auline = (SourceDataLine) AudioSystem.getLine(info);

                auline.open(format);                        // 按照指定格式打开源数据行
                auline.start();                             // 源数据行开始读写活动
                int byteCount = 0;                          // 记录音频输入流中读取的字节数
                while (byteCount != -1) {                   // 如果音频输入流读出的字节数不为-1
                    byteCount = audioInputStream.read(auBuffer, 0, auBuffer.length);    // 从音频数据流中读取128k数据

                    if (byteCount >= 0) {
                        auline.write(auBuffer, 0, byteCount);       // 如果读出有效数据，将有效数据写入数据行中
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain();                             // 清空数据行
                auline.close();                             // 关闭数据行
            }
        } while (circulate);                                // 根据循环标志判断是否循环播放
    }

    public void play() {
        thread = new Thread(this);          // 创建线程对象,将本类作为参数传入
        thread.start();                            // 开启线程
    }

    public void stop() {
        thread.interrupt();                              // 强制关闭线程
    }
}
