package com.wxhao.study;

import com.wxhao.study.filetool.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 新版 兼容
 */
@Slf4j
public class GPGroupV2Test {
    static String groupFolder = "04.分布式与微服务";
    String bdwpFolder = "D:\\咕泡学院\\百度网盘\\" + groupFolder;

    @Test
    public void group() {
        File bdwpFile = new File(bdwpFolder);

        Stream.of(FolderConfig.values()).forEach(folderConfig -> {
            //FileUtils.deleteChildrenFile(folderConfig.getRootFolder());
        });
        Stream.of(FolderConfig.values()).forEach(folderConfig -> {
            File targetFile = new File(folderConfig.getRootFolder());
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
        });
        Stream.of(FolderConfig.values()).forEach(folderConfig -> {
            FileUtils.deleteChildrenFile(folderConfig.getRootFolder());
        });
        recursiveFile(bdwpFile.getPath(), "");
    }

    public void recursiveFile(String path, String mdFolderName) {
        File rootFile = new File(path);
        Stream.of(rootFile.listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                //如果是文件夹就递归
                String fileName = mdFolderName + "\\" + file.getName();
                //createFolder(file, fileName);
                recursiveFile(file.getPath(), fileName);
            } else {
                String parentFileName = file.getParentFile().getName();
                if (!copyFile(parentFileName, file)) {
                    log.info("sourceFilePath:" + file.getPath());
                }
            }
        });
    }

    private void createFolder(File file, String fileName) {
        //不创建上级目录
        for (FolderConfig folderConfig : FolderConfig.values()) {
            for (String keyword : folderConfig.keywords) {
                if (file.getName().contains(keyword)) {
                    return;
                }
            }
        }
        for (FolderConfig folderConfig : FolderConfig.values()) {
            String pathname = folderConfig.getRootFolder() + "\\" + fileName;
            boolean mkdir = new File(pathname).mkdir();
            //log.info("mkdir:" + mkdir + " pathname:" + pathname);
        }
    }

    public boolean copyFile(String parentFileName, File file) {
        for (FolderConfig folderConfig : FolderConfig.values()) {
            for (String keyword : folderConfig.keywords) {
                if (parentFileName.contains(keyword)) {
                    try {
                        String targetFilePath = file.getParentFile().getParentFile().getPath();
                        targetFilePath = targetFilePath.replace(bdwpFolder, folderConfig.getRootFolder());
                        File targetFile = new File(targetFilePath);
                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }
                        FileUtils.copyFile(file.getPath(), targetFilePath + "\\" + file.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }


    @Getter
    @AllArgsConstructor
    public enum FolderConfig {

        COURSEWARE("D:\\咕泡学院\\study-courseware\\" + groupFolder, Arrays.asList("课后作业", "课堂笔记", "预习资料", "上课课件")),
        PROJECT("D:\\咕泡学院\\study-project\\" + groupFolder, Arrays.asList("课程源码", "课堂源码")),
        VIDEO("D:\\咕泡学院\\study-video\\" + groupFolder, Arrays.asList("录播视频")),
        PERSONAL("D:\\咕泡学院\\study-wxhao\\" + groupFolder, new ArrayList<>()),
        ;

        private String rootFolder;

        private List<String> keywords;
    }


    public interface GroupStrategy {


    }


}
