package com.example.wood121.viewdemos.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 根据values/dimens.xml, 自动计算比例并生成不同分辨率的dimens.xml
 * 注意用dp和sp，不要用dip，否则生成可能会出错；xml值不要有空格
 * <p>
 * 基准：我的红米Note 4x 大小角度
 */
public class DimenTool {

    public static void gen() {

        File file = new File("./app/src/main/res/values/dimens.xml");//这里如果找不到使用绝对路径即可
        BufferedReader reader = null;
        //添加分辨率
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw260 = new StringBuilder();
        StringBuilder sw320 = new StringBuilder();
        StringBuilder sw400 = new StringBuilder();
        StringBuilder sw440 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw560 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    int num = Integer.valueOf(tempString.substring(tempString.indexOf(">") + 1, tempString.indexOf("</dimen>") - 2));

                    //主要核心就再这里了，如下3种分辨率分别缩小 0.6、0.75、0.9倍(根据实际情况自己随意DIY)
                    sw240.append(start).append((int) Math.round(num * 0.67)).append(end).append("\n");
                    sw260.append(start).append((int) Math.round(num * 0.72)).append(end).append("\n");
                    sw320.append(start).append((int) Math.round(num * 0.89)).append(end).append("\n");
                    sw400.append(start).append((int) Math.round(num * 1.11)).append(end).append("\n");
                    sw440.append(start).append((int) Math.round(num * 1.22)).append(end).append("\n");
                    sw480.append(start).append((int) Math.round(num * 1.33)).append(end).append("\n");
                    sw560.append(start).append((int) Math.round(num * 1.55)).append(end).append("\n");
                    sw600.append(start).append((int) Math.round(num * 1.67)).append(end).append("\n");
                } else {
                    sw240.append(tempString).append("\n");
                    sw260.append(tempString).append("\n");
                    sw320.append(tempString).append("\n");
                    sw400.append(tempString).append("\n");
                    sw440.append(tempString).append("\n");
                    sw480.append(tempString).append("\n");
                    sw560.append(tempString).append("\n");
                    sw600.append(tempString).append("\n");
                }
                line++;
            }
            reader.close();
            System.out.println("<!--  sw240 -->");
            System.out.println(sw240);
            System.out.println("<!--  sw260 -->");
            System.out.println(sw260);
            System.out.println("<!--  sw320 -->");
            System.out.println(sw320);
            System.out.println("<!--  sw400 -->");
            System.out.println(sw400);
            System.out.println("<!--  sw440 -->");
            System.out.println(sw440);
            System.out.println("<!--  sw480 -->");
            System.out.println(sw480);
            System.out.println("<!--  sw560 -->");
            System.out.println(sw560);
            System.out.println("<!--  sw600 -->");
            System.out.println(sw600);

            String sw240file = "./app/src/main/res/values-sw240dp/dimens.xml";
            String sw260file = "./app/src/main/res/values-sw260dp/dimens.xml";
            String sw320file = "./app/src/main/res/values-sw320dp/dimens.xml";
            String sw400file = "./app/src/main/res/values-sw400dp/dimens.xml";
            String sw440file = "./app/src/main/res/values-sw440dp/dimens.xml";
            String sw480file = "./app/src/main/res/values-sw480dp/dimens.xml";
            String sw560file = "./app/src/main/res/values-sw560dp/dimens.xml";
            String sw600file = "./app/src/main/res/values-sw600dp/dimens.xml";
            writeFile(sw240file, sw240.toString());
            writeFile(sw260file, sw260.toString());
            writeFile(sw320file, sw320.toString());
            writeFile(sw400file, sw400.toString());
            writeFile(sw440file, sw440.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw560file, sw560.toString());
            writeFile(sw600file, sw600.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        gen();
    }
}