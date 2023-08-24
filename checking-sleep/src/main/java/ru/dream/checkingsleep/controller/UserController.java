package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "dreams-users")
public class UserController {
    private final UserService userService;
    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") UUID id) {

        return userService.getUserById(id);
    }
    @PostMapping("/user/create")
    UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/user/update")
    UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/user/delete/{id}")
    void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

}
