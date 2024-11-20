package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST controller for managing user-related operations, including retrieval, creation,
 * deletion, and update of user information.
 */

@RestController
@RequestMapping("/v1/users")
//@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;


    UserController(UserServiceImpl userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(()->new IllegalArgumentException("No user with this Id"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        System.out.println("User with e-mail: " + userDto.email() + " " + "passed to the request");

        userService.createUser(this.userMapper.toEntity(userDto));

        return null;
    }

   @DeleteMapping("/{userId}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteUser(@PathVariable Long userId) {

        userService.deleteUserById(userId);
   }

    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.searchUsersByEmailFragment(email)
                .stream()
                .toList();


    }
    @GetMapping("/older/{age}")
    public List<User> findUsersOlderThan(@PathVariable LocalDate age) {
        return userService.findUsersOlderThan(age);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserFirstName(
            @PathVariable Long userId,
            @RequestBody UserFirstNameUpdateDto userUpdateDto) {

        Optional<User> updatedUser = userService.updateUserFirstName(userId, userUpdateDto);

        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }}

