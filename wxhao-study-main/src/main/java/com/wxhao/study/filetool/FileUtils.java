package com.wxhao.study.filetool;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author wxhao
 * @date 2019/8/13
 */
public class FileUtils {

    /**
     * 递归删除文件夹
     *
     * @param path
     */
    public static void deleteChildrenFile(String path) {
        File file = new File(path);
        Stream.of(file.listFiles()).forEach(f -> {
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

        if (targetFile.exists()) {
            return;
        }

        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 8];
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

    /**
     * 不存在就创建目录
     *
     * @param path
     */
    public static void ifNotExistsMkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdir()) {
                System.out.println("mkdirFalse:" + path);
            }
        }
    }



    /**
     * 解压文件到指定目录
     */
    @SuppressWarnings({"rawtypes", "resource"})
    public static void unZip(String zipPath, String descDir) throws IOException {
        //log.info("文件:{}. 解压路径:{}. 解压开始.",zipPath,descDir);
        try {
            File zipFile = new File(zipPath);
            System.err.println(zipFile.getName());
            if (!zipFile.exists()) {
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                System.err.println(zipEntryName);
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName);
                System.err.println(outPath);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('\\')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


}
