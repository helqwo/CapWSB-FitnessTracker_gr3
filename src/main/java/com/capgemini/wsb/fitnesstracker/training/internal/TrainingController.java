package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {
    private final TrainingProvider trainingProvider;
    private final UserService userService;
    private final TrainingRepository trainingRepository;
    private final TrainingService trainingService;

    TrainingController(TrainingProvider trainingProvider, TrainingRepository trainingRepository, UserService userService, TrainingService trainingService) {
        this.trainingProvider = trainingProvider;
        this.trainingRepository = trainingRepository;
        this.userService = userService;
        this.trainingService = trainingService;
    }

    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings(){
        List<Training> trainings = trainingRepository.findAll();
        return ResponseEntity.ok(trainings);
    }

}

