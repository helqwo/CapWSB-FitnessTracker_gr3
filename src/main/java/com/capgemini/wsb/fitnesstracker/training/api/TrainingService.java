package com.capgemini.wsb.fitnesstracker.training.api;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingService {
    List<Training> findAllTrainings();
}
