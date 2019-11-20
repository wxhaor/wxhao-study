package com.wxhao.study;

import com.wxhao.study.filetool.FileGroupUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

/**
 * 百度网盘 文件分组 20191013
 * 文件分为 视频 非视频
 * 解压
 */
@Slf4j
public class BdwpV1Test {

    String sourceRootFileName = "D:\\咕泡学院\\百度网盘";

    List<String> fileSources = Arrays.asList(
//            "01.架构师内功心法",
//            "02.架构师审美观",
//            "03.分布式与微服务",
//            "04.分布式与微服务",
            "05.架构师必备工具箱"
    );

    @Test
    public void groupBy() {
        List<String> videoFolderNames = Arrays.asList(
                "录播视频"
        );

        String defaultTargetRootFileName = "D:\\咕泡学院\\BdwpV1Test\\default";
        String videoTargetRootFileName = "D:\\咕泡学院\\BdwpV1Test\\video";
        FileGroupUtils instance = FileGroupUtils.getInstance(
                sourceRootFileName,
                defaultTargetRootFileName,
                videoTargetRootFileName,
                videoFolderNames);
        instance.recursiveFiles(fileSources);
    }

    @Test
    public void check() {

        //校验视频目录
        fileSources.forEach(fileSource -> {
            checkFolder(sourceRootFileName + "\\" + fileSource);
        });
        System.out.println("---校验最后一级目录---");
        map.forEach((s, strings) -> {
            System.out.println(s + "-" + strings.size());
        });
        System.out.println("---校验最后一级目录---");

    }

    private Map<String, List<String>> map = new HashMap<>();

    public void checkFolder(String path) {
        File rootFile = new File(path);
        Stream.of(rootFile.listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                //如果是文件夹就递归
                checkFolder(file.getPath());
            } else {
                File[] files = file.getParentFile().listFiles();
                int folderCount = 0;
                for (File f : files) {
                    if (f.isDirectory()) {
                        folderCount++;
                    }
                }
                if (folderCount == 0) {
                    String name = file.getParentFile().getName();
                    List<String> strings = map.get(name);
                    if (strings == null) {
                        strings = new ArrayList<>();
                    }
                    strings.add(name);
                    map.put(name, strings);
                }
            }
        });
    }


}
