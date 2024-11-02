package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository, TrainingRepository trainingRepository){this.userRepository=userRepository;
    }


    @Override
    public User createUser(final User user) {
        System.out.printf("Creating User %s", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            System.out.println("User with ID " + userId + " deleted successfully.");
        } else {
            throw new IllegalArgumentException("User not found with ID " + userId);
        }

    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {

        return userRepository.findByEmail(email);
    }
    public List<UserEmailDto> searchUsersByEmailFragment(String emailFragment) {
        return userRepository.findByEmailFragment(emailFragment);
    }
    public List<User> findUsersOlderThan(LocalDate birthdate) {
        return userRepository.findOlderThan(birthdate);
    }
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
  /*  public Optional<User>*/
/*    updateUserFirstName(Long userId, UserFirstNameUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update user's first name using the DTO provided
            user.setFirstName(userUpdateDto.getFirstName()); // Correctly setting the first name

            // Save the updated user
            return Optional.of(userRepository.save(user));
        }

        return Optional.empty(); // User not found
    }*/
    public Optional<User> updateUserFirstName(Long userId, UserFirstNameUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(userUpdateDto.getFirstName());  // Update first name
            userRepository.save(user);  // Save changes
            return Optional.of(user);
        }

        return Optional.empty();  // Return empty if user not found
    }



}