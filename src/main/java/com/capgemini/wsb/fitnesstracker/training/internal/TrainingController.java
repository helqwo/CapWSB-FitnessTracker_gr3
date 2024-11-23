package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller that handles all HTTP requests related to training sessions.
 * Provides endpoints for creating, retrieving, and updating training sessions.
 * All training-related operations are backed by services like {@link TrainingService} and {@link UserService}.
 */
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<Training>> getTrainingsByUser(@PathVariable Long userId) {
        List<Training> trainings = trainingRepository.findByUserId(userId);
        return ResponseEntity.ok(trainings);
    }
    @GetMapping("/finished/{afterDate}")
    public ResponseEntity<List<Training>> getFinishedTrainingsAfter(@PathVariable String afterDate) {
        LocalDate aDate = LocalDate.parse(afterDate);
        LocalDateTime afterDateTime = aDate.atStartOfDay();
        List<Training> trainings = trainingRepository.findFinishedTrainingsAfter(afterDateTime);
        return ResponseEntity.ok(trainings);
    }
    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody TrainingDto trainingDto) {
        User user = userService.getUserById(trainingDto.getUserId());
        Training training = new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());

        Training createdTraining = trainingService.createTraining(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTraining);
    }
    @GetMapping("/activityType")
    public ResponseEntity<List<Training>> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        System.out.println(activityType);
        List<Training> trainings = trainingRepository.findByActivityType(activityType);
        return ResponseEntity.ok(trainings);
    }
    @PutMapping("/{trainingId}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long trainingId, @RequestBody Training updatedTraining) {
        Optional<Training> optionalTraining = trainingRepository.findById(trainingId);

        if (optionalTraining.isPresent()) {
            Training existingTraining = optionalTraining.get();

            updatedTraining.setUser(existingTraining.getUser());

            updatedTraining.setId(trainingId);

            Training savedTraining = trainingRepository.save(updatedTraining);

            return ResponseEntity.ok(savedTraining);
        } else {
            return ResponseEntity.notFound().build();
        }}
}
