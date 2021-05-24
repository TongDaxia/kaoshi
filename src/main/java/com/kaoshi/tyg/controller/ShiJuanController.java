package com.kaoshi.tyg.controller;

import com.kaoshi.tyg.common.CommonResponse;
import com.kaoshi.tyg.common.ReturnCode;
import com.kaoshi.tyg.entity.dto.ExerciseDTO;
import com.kaoshi.tyg.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/shijuan")
public class ShiJuanController {


    @Autowired
    ExerciseService exerciseService;


    @RequestMapping("/exercise")
    public CommonResponse<List<ExerciseDTO>> findAllExercixe(){

        List<ExerciseDTO> all = exerciseService.findAll();


        return new CommonResponse<>(ReturnCode.SUCCESS,all);
    }



}
