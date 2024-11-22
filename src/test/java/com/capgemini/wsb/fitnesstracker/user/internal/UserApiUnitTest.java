package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserApiUnitTest {

    @Test
    public void testUserConstructorAndGetters() {
        String imie = "Jan";
        String nazwisko = "Kowalski";
        LocalDate dataUrodzenia = LocalDate.of(1990, 5, 15);
        String email = "jan.kowalski@example.com";

        User user = new User(imie, nazwisko, dataUrodzenia, email);

        assertEquals(imie, user.getFirstName());
        assertEquals(nazwisko, user.getLastName());
        assertEquals(dataUrodzenia, user.getBirthdate());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testUserSetters() {
        // Testowanie setterów
        String imie = "Anna";
        String nazwisko = "Nowak";
        LocalDate dataUrodzenia = LocalDate.of(1985, 3, 10);
        String email = "anna.nowak@example.com";

        User user = new User(imie, nazwisko, dataUrodzenia, email);

        // Zmiana wartości przez setter
        user.setFirstName("Ewa");
        assertEquals("Ewa", user.getFirstName());
    }

    @Test
    public void testUserToString() {
        // Testowanie metody toString
        String imie = "Basia";
        String nazwisko = "Nowak";
        LocalDate dataUrodzenia = LocalDate.of(1987, 11, 19);
        String email = "basia.nowak@example.com";

        User user = new User(imie, nazwisko, dataUrodzenia, email);

        // Sprawdzanie zawartości toString
        String userString = user.toString();
        assertTrue(userString.contains("Basia"));
        assertTrue(userString.contains("Nowak"));
        assertTrue(userString.contains("1987"));
    }


    @Test
    public void testUserTrainingsManagement() {
        User user = new User("Adam", "Kowalski", LocalDate.of(1995, 6, 20), "adam.Kowalski@example.com");
        Training treningMock = mock(Training.class);

        List<Training> trainings = user.getTrainings();
        assertNotNull(trainings);
        assertTrue(trainings.isEmpty());

        // Symulujemy dodanie treningu
        trainings.add(treningMock);
        assertEquals(1, trainings.size());
    }

    @Test
    public void testUserNotFoundException() {
        Long userId = 111L;
        UserNotFoundException exception = new UserNotFoundException(userId);

        assertEquals("User with ID=111 was not found", exception.getMessage());
    }

    @Test
    public void testUserNotFoundInheritance() {
        UserNotFoundException exception = new UserNotFoundException(1L);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    public void testNullIdInUser() {
        User user = new User("Ryan", "Gosling", LocalDate.of(1980, 11, 12), "ryan.gosling@example.com");

        assertNull(user.getId()); // Domyślne ID jest null
    }

    @Test
    public void testUserEmailUniqueness() {
        User user1 = new User("John", "Doe", LocalDate.of(1992, 4, 1), "jane.doe@example.com");
        User user2 = new User("Jane", "Doe", LocalDate.of(1990, 2, 2), "jane.doe@example.com");

        assertEquals(user1.getEmail(), user2.getEmail()); // Dwa emaile są takie same
    }
}
