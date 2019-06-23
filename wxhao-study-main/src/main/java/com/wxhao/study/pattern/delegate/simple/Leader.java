package com.wxhao.study.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;


public class Leader implements IEmployee {

    private Map<String, IEmployee> targets = new HashMap<String, IEmployee>();

    public Leader() {
        //A员工更擅长Excel
        targets.put("Excel", new EmployeeA());
        //B员工更擅长PPT
        targets.put("PPT", new EmployeeB());
    }

    @Override
    public void doing(String command) {
        //项目经理自己不干活,把任务指派给别人
        targets.get(command).doing(command);
    }

}
