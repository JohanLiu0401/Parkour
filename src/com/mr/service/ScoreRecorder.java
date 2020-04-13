package com.mr.service;

import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

public class ScoreRecorder {
    private static final String SCOREFILE = "data/source";      // 成绩记录文件
    private static int scores[] = new int[3];                   // 记录当前得分最高前三名的数组

    /**
     * 使用该类前先调用init(),从成绩记录文件中读取到历史前三名数据
     */
    public static void init() {
        File f = new File(SCOREFILE);                       // 创建记录文件
        if (!f.exists()) {                                  // 如果文件不存在
            try {
                f.createNewFile();                          // 创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;                                         // 停止方法
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(f);                                   // 文件字节输入流
            isr = new InputStreamReader(fis);                               // 字节流转字符流
            br = new BufferedReader(isr);                                   // 缓冲字符流
            String value = br.readLine();                                   // 读取一行
            if (!(value == null || "".equals(value))) {                     // 如果不为空值
                String vs[] = value.split(",");                       // 分割字符串
                if (vs.length < 3) {                                        // 如果分割结果小于3
                    Arrays.fill(scores, 0);                             // 数组填充0
                } else {
                    for (int i = 0; i < 3; i++) {
                        scores[i] = Integer.parseInt(vs[i]);                // 将记录文件中的值赋给当前分数数组
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                                                         // 依次关闭流
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("缓冲区关闭异常");
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("字符流关闭异常");
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("字节流关闭异常");
            }
        }
    }

    /**
     * 游戏程序停止时，记录最新的前三名成绩
     * 该方法将当前成绩数组中的值写入到成绩记录文件中
     */
    public static void saveScore() {
        String value = scores[0] + "," + scores[1] + "," + scores[2];           // 拼接得数组
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(SCOREFILE);                              // 文件字节输出流
            osw = new OutputStreamWriter(fos);                                  // 字节流转字符流
            bw = new BufferedWriter(osw);                                       // 缓冲字符流
            bw.write(value);                                                    // 写入拼接后的字符串
            bw.flush();                                                         // 字符流刷新
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                                                             // 依次关闭流
            try {
                bw.close();
                System.out.println("缓冲区关闭异常");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
                System.out.println("字符流关闭异常");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("字节流关闭异常");
            }
        }
    }

    /**
     * 添加新成绩
     * @param score 新成绩
     */
    static public void addNewScore(int score) {
        int tmp[] = Arrays.copyOf(scores, 4);         // 在得分数组基础上创建一个长度为4的临时数组
        tmp[3] = score;                                         // 将新分数赋值给第四个数组
        Arrays.sort(tmp);                                       // 临时数组升序排列
        scores = Arrays.copyOfRange(tmp, 1, 4);        // 将后三个元素赋值给得分数组
    }

    /**
     * 成绩数组为私有属性，需要该方法获取
     * @return  成绩数组
     */
    static public int[] getScores() {
        return scores;
    }

}
