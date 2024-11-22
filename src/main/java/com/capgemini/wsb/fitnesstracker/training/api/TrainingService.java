package com.capgemini.wsb.fitnesstracker.training.api;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface defining the operations for managing training sessions.
 * Implementing classes are responsible for handling actions related to trainings,
 * like creating, retrieving and filtering.
 */
public interface TrainingService {
    Training createTraining(Training training);
    List<Training> findAllTrainings();
    List<Training> findFinishedTrainingsAfter(LocalDateTime afterTime);

}

