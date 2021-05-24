package com.kaoshi.tyg.entity;

import java.util.Date;

public class ExerciseOption {
    public ExerciseOption() {
    }

    private Long id;
    private String content;

    private Long exerciseTitleId;
    private String subjectName;
    private Integer exerciseType;
    private Integer isTrue;

    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getExerciseTitleId() {
        return exerciseTitleId;
    }

    public void setExerciseTitleId(Long exerciseTitleId) {
        this.exerciseTitleId = exerciseTitleId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Integer exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Integer getIsTrue() {
        return isTrue;
    }


    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
