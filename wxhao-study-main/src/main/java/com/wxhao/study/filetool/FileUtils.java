package com.wxhao.study.filetool;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author wxhao
 * @date 2019/8/13
 */
public class FileUtils {


    public static void deleteChildrenFile(String path) {
        File file = new File(path);
        Stream.of(file.listFiles()).forEach(f -> {
            System.out.println(file.getPath());
            if (f.isDirectory()) {
                if (f.list().length > 0) {
                    deleteChildrenFile(f.getPath());
                }
            }
            f.delete();
        });
    }

    /**
     * 复制文件
     *
     * @param sourceFilePath
     * @param targetFilePath
     * @throws IOException
     */
    public static void copyFile(String sourceFilePath, String targetFilePath)
            throws IOException {

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

}
