package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingApiUnitTest {

    @Test
    public void testTrainingConstructorAndGetters() {
        User userMockowany = mock(User.class);
        Date dataPoczatkowa = new Date();
        Date koniecData = new Date(dataPoczatkowa.getTime() + 3600000); // dodajemy godzinę do startu
        ActivityType typDzialania = ActivityType.RUNNING;
        double dlugosc = 6.9;
        double sredniaPredkosc = 12.0;

        // tworzymy obiekt treningu
        Training trening = new Training(userMockowany, dataPoczatkowa, koniecData, typDzialania, dlugosc, sredniaPredkosc);

        // sprawdzamy czy dobrze działa get
        assertEquals(userMockowany, trening.getUser());
        assertEquals(dataPoczatkowa, trening.getStartTime());
        assertEquals(koniecData, trening.getEndTime());
        assertEquals(typDzialania, trening.getActivityType());
        assertEquals(dlugosc, trening.getDistance());
        assertEquals(sredniaPredkosc, trening.getAverageSpeed());
    }

    @Test
    public void testTrainingSetters() {
        User user2 = mock(User.class);
        Date dataStart = new Date();
        Date dataKoniec = new Date(dataStart.getTime() + 7200000); // dwie godziny później
        ActivityType typ = ActivityType.CYCLING;
        double dystans = 15.0;
        double predkoscSrednia = 20.0;

        Training training = new Training(user2, dataStart, dataKoniec, typ, dystans, predkoscSrednia);

        // ustawianie wszystkiego ręcznie
        training.setUser(user2);
        training.setStartTime(dataStart);
        training.setEndTime(dataKoniec);
        training.setActivityType(typ);
        training.setDistance(dystans);
        training.setAverageSpeed(predkoscSrednia);

        // sprawdzamy gettery
        assertEquals(user2, training.getUser());
        assertEquals(dataStart, training.getStartTime());
        assertEquals(dataKoniec, training.getEndTime());
        assertEquals(typ, training.getActivityType());
        assertEquals(dystans, training.getDistance());
        assertEquals(predkoscSrednia, training.getAverageSpeed());
    }

    @Test
    public void testTrainingToString() {
        User user = mock(User.class); // kolejny user
        Date start = new Date();
        Date end = new Date(start.getTime() + 3600000);
        ActivityType type = ActivityType.RUNNING;
        double dist = 10.0;
        double avgSpeed = 12.0;

        Training train = new Training(user, start, end, type, dist, avgSpeed);
        String tekst = train.toString();

        // sprawdzamy czy tekst zawiera różne wartości
        assertTrue(tekst.contains("user"));
        assertTrue(tekst.contains("startTime"));
        assertTrue(tekst.contains("endTime"));
        assertTrue(tekst.contains("activityType"));
        assertTrue(tekst.contains("distance"));
        assertTrue(tekst.contains("averageSpeed"));
    }

    @Test
    public void testTrainingNotFoundExceptionWithSpecificId() {
        Long id = 124L; // przypadkowy ID
        TrainingNotFoundException wyjatek = new TrainingNotFoundException(id);

        // porównanie wiadomości
        assertEquals("Training with ID=124 was not found", wyjatek.getMessage());
    }

    @Test
    public void testTrainingNotFoundInheritanceException() {
        TrainingNotFoundException ex = new TrainingNotFoundException(2L); // dowolny ID
        assertInstanceOf(RuntimeException.class, ex); // sprawdzamy czy dziedziczy Runtime
    }
}
