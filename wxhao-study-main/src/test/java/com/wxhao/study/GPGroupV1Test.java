package com.wxhao.study;

import com.wxhao.study.filetool.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author wxhao
 * @date 2019/8/13
 */
public class GPGroupV1Test {


    @Test
    public void copyFilter() {
        String source = "C:\\down\\bdy\\05.分布式服务治理";
        String target = "D:\\咕泡学院\\2.[学习资料]\\04.架构师必备技术栈\\05.分布式服务治理";

        File sourceFile = new File(source);
        Stream.of(sourceFile.listFiles()).forEach(file -> {
            File createFile = new File(target + "\\" + file.getName());
            createFile.mkdir();
        });

    }

    @Test
    public void groupFile() {
        FileUtils.deleteChildrenFile(videoTarget);
        FileUtils.deleteChildrenFile(documentTarget);
        File parentFile = new File(source);
        Stream.of(parentFile.listFiles()).forEach(file -> {
            copyFile(file.getPath());
        });
    }

    String source = "C:\\down\\bdy\\05.分布式服务治理";
    String documentTarget = "D:\\新建文件夹\\文件";
    String videoTarget = "D:\\新建文件夹\\视频";

    public void copyFile(String path) {
        File rootFile = new File(path);
        if (rootFile.isDirectory()) {
            String relativePath = path.replace(source, "");
            String videoPath = videoTarget + relativePath;
            String documentPath = documentTarget + relativePath;
            new File(videoPath).mkdir();
            new File(documentPath).mkdir();

            Stream.of(rootFile.listFiles()).forEach(file -> {
                copyFile(file.getPath());
            });
        } else {
            String videoName = "录播视频";
            //上级目录名称
            String parentFileName = rootFile.getParentFile().getName();
            String relativePath = rootFile.getParentFile().getParentFile().getName().replace(source, "");

            String filterRelativePath = rootFile.getParentFile().getPath().replace(source, "");
            String deleteVideoPath = videoTarget + filterRelativePath;
            String deleteDocumentPath = documentTarget + filterRelativePath;
            new File(deleteVideoPath).delete();
            new File(deleteDocumentPath).delete();

            if (videoName.equals(parentFileName)) {
                try {
                    String videoPath = videoTarget + "\\" +  relativePath + "\\" + rootFile.getName();
                    System.out.println(videoPath);
                    FileUtils.copyFile(rootFile.getPath(), videoPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String documentPath = documentTarget + "\\" +  relativePath + "\\" + rootFile.getName();
                    System.out.println(documentPath);
                    FileUtils.copyFile(rootFile.getPath(), documentPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
