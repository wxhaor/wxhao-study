package com.wxhao;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

/**
 * maven 插件
 *
 * @author wxhao
 * @date 2019/5/26
 */
@Mojo(name = "study", defaultPhase = LifecyclePhase.PACKAGE)
public class StudyMojo extends AbstractMojo {

    /**
     * 获取变量
     */
    @Parameter
    private String msg;

    /**
     * 获取数组
     */
    @Parameter
    private List<String> options;

    /**
     * 获取系统变量 project
     */
    @Parameter(defaultValue = "${project}")
    private MavenProject mavenProject;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("帅比浩-插件:" + msg + options);
        System.out.println("帅比浩-插件:" + mavenProject);
    }

}
