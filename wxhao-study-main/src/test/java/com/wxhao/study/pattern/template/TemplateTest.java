package com.wxhao.study.pattern.template;

import com.wxhao.study.pattern.template.course.BigDataCourse;
import com.wxhao.study.pattern.template.course.JavaCourse;
import com.wxhao.study.pattern.template.course.NetworkCourse;
import com.wxhao.study.pattern.template.jdbc.dao.MemberDao;
import org.junit.Test;

import java.util.List;

/**
 * 模版模式
 * @author wxhao
 * @date 2019/6/20
 */
public class TemplateTest {


    @Test
    public void testNetworkCourse() {
        System.out.println("---Java架构师课程---");
        NetworkCourse javaCourse = new JavaCourse();
        javaCourse.createCourse();

        System.out.println("---大数据课程---");
        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
    }

    @Test
    public void TestMemberDao() {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
