package com.wxhao.study.spring.v2.mvc;

import java.util.Map;

/**
 * @author wxhao
 * @date 2019/7/27
 */
public class ModelAndView {
    private String viewName;
    private Map<String,?> model;
    public ModelAndView(String viewName) {
        this(viewName,null);
    }
    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public Map<String, ?> getModel() {
        return model;
    }
    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
