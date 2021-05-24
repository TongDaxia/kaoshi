package com.kaoshi.tyg.entity.dto;

import java.util.List;
import java.util.Map;

public  class SubExerciseDTO{

    private String subType ;
    private String title ;
    private List<Map<String,Integer>>  option;// [{"":0},{"":1},{"":0},{"":0}],
    private String jiexi ;


    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, Integer>>  getOption() {
        return option;
    }

    public void setOption(List<Map<String, Integer>> option) {
        this.option = option;
    }

    public String getJiexi() {
        return jiexi;
    }

    public void setJiexi(String jiexi) {
        this.jiexi = jiexi;
    }
}
