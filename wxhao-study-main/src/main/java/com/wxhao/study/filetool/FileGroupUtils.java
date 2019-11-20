package com.wxhao.study.filetool;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
            List<String> videoFolderNames
    ) {
        FileGroupUtils fileGroupUtils = new FileGroupUtils();
        fileGroupUtils.sourceRootFileName = sourceRootFileName;
        fileGroupUtils.defaultTargetRootFileName = defaultTargetRootFileName;
        fileGroupUtils.videoTargetRootFileName = videoTargetRootFileName;
        fileGroupUtils.videoFolderNames.addAll(videoFolderNames);
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
                    });
                } else {
                    dealFile(filePath, defaultTargetRootFileName, null);
                }
            }
        });
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
                if (consumer != null) {
                    consumer.accept(targetFilePath);
                }
            } else {
                System.out.println("已存在:" + targetFilePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
