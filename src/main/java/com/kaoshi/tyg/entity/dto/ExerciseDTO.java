package com.kaoshi.tyg.entity.dto;

import java.util.List;

public class ExerciseDTO extends SubExerciseDTO {

    //类型 0单选，1多选，2 判断，3 母题，// 4 子题
    private  String type;

    private String desc;

    private List<SubExerciseDTO> content;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<SubExerciseDTO> getContent() {
        return content;
    }

    public void setContent(List<SubExerciseDTO> content) {
        this.content = content;
    }



}
