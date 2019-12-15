package com.wxhao.study.filetool;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileGroupUtils {

    /**
     * 视频目录名称数组
     */
    List<String> videoFolderNames = new ArrayList<>();

    String sourceRootFileName;
    /**
     * 目标目录(默认)
     */
    String defaultTargetRootFileName;
    /**
     * 目标目录(视频)
     */
    String videoTargetRootFileName;


    String videoTargetGPlayerFileName;


    /**
     * 初始化分组实例
     *
     * @param sourceRootFileName        源文件根目录
     * @param defaultTargetRootFileName 默认目标根目录
     * @param videoTargetRootFileName   视频目标根目录
     * @param videoFolderNames          过滤文件夹关键词
     * @return
     */
    public static FileGroupUtils getInstance(
            String sourceRootFileName,
            String defaultTargetRootFileName,
            String videoTargetRootFileName,
            String videoTargetGPlayerFileName,
            List<String> videoFolderNames
    ) {
        FileGroupUtils fileGroupUtils = new FileGroupUtils();
        fileGroupUtils.sourceRootFileName = sourceRootFileName;
        fileGroupUtils.defaultTargetRootFileName = defaultTargetRootFileName;
        fileGroupUtils.videoTargetRootFileName = videoTargetRootFileName;
        fileGroupUtils.videoFolderNames.addAll(videoFolderNames);
        fileGroupUtils.videoTargetGPlayerFileName = videoTargetGPlayerFileName;
        // todo 传list
        FileUtils.ifNotExistsMkdir(defaultTargetRootFileName);
        FileUtils.ifNotExistsMkdir(videoTargetRootFileName);
        return fileGroupUtils;
    }

    public void recursiveFiles(List<String> fileSourcePaths) {
        fileSourcePaths.forEach(fileSourcePath -> {
            recursiveFile(sourceRootFileName + "\\" + fileSourcePath);
        });
    }

    public void recursiveFile(String path) {
        File rootFile = new File(path);
        System.out.println("recursiveFile:"+path);
        Stream.of(rootFile.listFiles()).forEach(file -> {
            String filePath = file.getPath();
            if (file.isDirectory()) {
                //如果是文件夹就递归
                recursiveFile(filePath);
            } else {
                //是文件就处理
                String parentFileName = file.getParentFile().getName();
                if (videoFolderNames.contains(parentFileName)) {
                    dealFile(filePath, videoTargetRootFileName, s -> {
                        //todo 解压
                        upZip(filePath);
                    });
                } else {
                    dealFile(filePath, defaultTargetRootFileName, null);
                }
            }
        });
    }

    private void upZip(String filePath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            File zipFile = new File(filePath);
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                long size = entry.getSize();
                if (size < 1024) {
                    continue;
                }
                in = zip.getInputStream(entry);
                String[] split = zipEntryName.split("/");
                String outPath = videoTargetGPlayerFileName + File.separator + split[split.length - 1];
                // 输出文件路径信息
                File pathFile = new File(outPath);
                if (pathFile.exists()) {
                    continue;
                }
                out = new FileOutputStream(outPath);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("zipError:" + filePath);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void dealFile(String path, String targetRootFileName, Consumer<String> consumer) {
        File sourceFile = new File(path);

        String targetPath = sourceFile.getParentFile().getParentFile().getPath().replace(sourceRootFileName, targetRootFileName);

        File targetFolder = new File(targetPath);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }
        String targetFilePath = targetPath + "\\" + sourceFile.getName();

        try {
            File file = new File(targetFilePath);
            if (!file.exists()) {
                FileUtils.copyFile(path, targetFilePath);
            } else {
                System.out.println("已存在:" + targetFilePath);
            }
            if (consumer != null) {
                consumer.accept(targetFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
