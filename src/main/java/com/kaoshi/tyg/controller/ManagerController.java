package com.kaoshi.tyg.controller;

import com.kaoshi.tyg.common.CommonResponse;
import com.kaoshi.tyg.common.ReturnCode;
import com.kaoshi.tyg.entity.dto.AddExerciseDTO;
import com.kaoshi.tyg.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/manager")
public class ManagerController {

    @Autowired
    ExerciseService exerciseService;

    @PostMapping("/addExercise")
    public CommonResponse<Object> addExercise(AddExerciseDTO addExercise){

//        exerciseService.addExercise(addExercise);



        return new CommonResponse(ReturnCode.SUCCESS);
    }
}
