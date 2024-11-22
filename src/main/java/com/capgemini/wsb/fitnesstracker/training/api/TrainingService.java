package com.capgemini.wsb.fitnesstracker.training.api;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingService {
    Training createTraining(Training training);
    List<Training> findAllTrainings();
    List<Training> findFinishedTrainingsAfter(LocalDateTime afterTime);

}

