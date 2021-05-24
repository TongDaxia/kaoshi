package com.kaoshi.tyg.service.impl;

import com.kaoshi.tyg.entity.ExerciseOption;
import com.kaoshi.tyg.entity.ExerciseTitle;
import com.kaoshi.tyg.entity.dto.ExerciseDTO;
import com.kaoshi.tyg.entity.dto.SubExerciseDTO;
import com.kaoshi.tyg.mapper.ExerciseTitleMapper;
import com.kaoshi.tyg.service.ExerciseService;
import com.kaoshi.tyg.util.Type;
import com.kaoshi.tyg.util.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    @Autowired
    private ExerciseTitleMapper exerciseTitleMapper;

    @Override
    public List<ExerciseDTO> findAll() {

        List<ExerciseDTO> exercises = new ArrayList<ExerciseDTO>();


        List<ExerciseTitle> titles = exerciseTitleMapper.findAll();
        titles.forEach(title -> {
            if (Objects.equals(title.getExerciseType(), Type.MUTI)) {
                ExerciseDTO exerciseDTO = new ExerciseDTO();
                exerciseDTO.setType(Type.MUTISTR);


                List<ExerciseTitle> zitis = exerciseTitleMapper.findByParentId(title.getId());

                List<SubExerciseDTO> subExercises = new ArrayList<>();

                zitis.forEach(zitiTitle -> {

                    SubExerciseDTO subZiti = new SubExerciseDTO();
                    //子题选项
                    List<ExerciseOption> options = exerciseTitleMapper.findOptionByTitleId(zitiTitle.getId());
                    subZiti.setTitle(zitiTitle.getContent());
                    subZiti.setJiexi(zitiTitle.getJiexi());
                    subZiti.setSubType(TypeUtils.getStrTypeByInt(zitiTitle.getExerciseType()));

                    List<Map<String, Integer>> optionMap = new ArrayList<>();
                    options.forEach(op -> {
                        HashMap<String, Integer> hash = new HashMap<String, Integer>();
                        hash.put(op.getContent(), op.getIsTrue());
                        optionMap.add(hash);
                    });

                    subZiti.setOption(optionMap);

                    //添加子题
                    subExercises.add(subZiti);

                });

                //所有子题
                exerciseDTO.setContent(subExercises);

                //添加母子题
                exercises.add(exerciseDTO);

            } else {

                //独立题
                ExerciseDTO exercise = new ExerciseDTO();

                exercise.setTitle(title.getContent());
                exercise.setJiexi(title.getJiexi());
                exercise.setSubType(TypeUtils.getStrTypeByInt(title.getExerciseType()));

                List<ExerciseOption> options = exerciseTitleMapper.findOptionByTitleId(title.getId());

                List<Map<String, Integer>> optionMap = new ArrayList<>();
                options.forEach(op -> {
                    HashMap<String, Integer> hash = new HashMap<String, Integer>();
                    hash.put(op.getContent(), op.getIsTrue());
                    optionMap.add(hash);
                });

                exercise.setOption(optionMap);


                //添加独立题
                exercises.add(exercise);
            }


        });


        return exercises;

    }
}
