package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
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

    @GetMapping("/user/childPhoto/{id}")
    MongoPhotoDto getChildPhoto(@PathVariable("id") UUID id) {
        return userService.getChildPhoto(id);
    }

    @GetMapping("/user/momPhoto/{id}")
    MongoPhotoDto getMomPhoto(@PathVariable("id") UUID id) {
        return userService.getMomPhoto(id);
    }

    @GetMapping("/user/dadPhoto/{id}")
    MongoPhotoDto getDadPhoto(@PathVariable("id") UUID id) {
        return userService.getDadPhoto(id);
    }

    @PutMapping("/user/updateChildPhoto/update/{id}")
    MongoPhotoDto updateChildPhoto(@PathVariable("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateChildPhoto(id, mongoPhotoDto);
    }

    @PutMapping("/user/updateMomPhoto/update/{id}")
    MongoPhotoDto updateMomPhoto(@PathVariable("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateMomPhoto(id, mongoPhotoDto);
    }

    @PutMapping("/user/updateDadPhoto/update/{id}")
    MongoPhotoDto updateDadPhoto(@PathVariable("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateDadPhoto(id, mongoPhotoDto);
    }

    @DeleteMapping("/user/deleteChildPhoto/{id}")
    void deleteChildPhoto(@PathVariable("id") UUID id) {
        userService.deleteChildPhoto(id);
    }

    @DeleteMapping("/user/deleteMomPhoto/{id}")
    void deleteMomPhoto(@PathVariable("id") UUID id) {
        userService.deleteMomPhoto(id);
    }

    @DeleteMapping("/user/deleteDadPhoto/{id}")
    void deleteDadPhoto(@PathVariable("id") UUID id) {
        userService.deleteDadPhoto(id);
    }


}
