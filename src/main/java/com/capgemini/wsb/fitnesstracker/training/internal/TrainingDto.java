package com.capgemini.wsb.fitnesstracker.training.internal;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;


/**
 * Data Transfer Object (DTO) used for transferring training session data.
 * The class contains the necessary fields for representing a training session
 * and is used for creating or updating training records.
 * *
 * It includes the following attributes:
 * - userId, startTime, endTime, activityType, distance and averageSpeed.
 */
@Getter
@Setter
public class TrainingDto {
    private Long userId;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;


}
