package com.kaoshi.tyg.mapper;


import com.kaoshi.tyg.entity.ExerciseOption;
import com.kaoshi.tyg.entity.ExerciseTitle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExerciseTitleMapper {

    List<ExerciseTitle> findAll();

    List<ExerciseTitle> findByParentId(Long id);

    List<ExerciseTitle> findByParentIds(Long[] id);

    List<ExerciseOption> findOptionByTitleId(Long id);
}
