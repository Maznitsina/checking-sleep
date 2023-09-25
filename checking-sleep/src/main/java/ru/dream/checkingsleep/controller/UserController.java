package ru.dream.checkingsleep.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dream.checkingsleep.dto.MongoPhotoDto;
import ru.dream.checkingsleep.dto.UserDto;
import ru.dream.checkingsleep.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@SecurityRequirement(name = "dreams-users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    UserDto getUserById(@RequestParam("id") UUID id) {

        return userService.getUserById(id);
    }

    @PostMapping("/create")
    UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/update")
    UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@RequestParam("id") UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/childPhoto/{id}")
    MongoPhotoDto getChildPhoto(@RequestParam("id") UUID id) {
        return userService.getChildPhoto(id);
    }

    @GetMapping("/momPhoto/{id}")
    MongoPhotoDto getMomPhoto(@RequestParam("id") UUID id) {
        return userService.getMomPhoto(id);
    }

    @GetMapping("/dadPhoto/{id}")
    MongoPhotoDto getDadPhoto(@RequestParam("id") UUID id) {
        return userService.getDadPhoto(id);
    }

    @PutMapping("/updateChildPhoto/update/{id}")
    MongoPhotoDto updateChildPhoto(@RequestParam("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateChildPhoto(id, mongoPhotoDto);
    }

    @PutMapping("/updateMomPhoto/update/{id}")
    MongoPhotoDto updateMomPhoto(@RequestParam("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateMomPhoto(id, mongoPhotoDto);
    }

    @PutMapping("/updateDadPhoto/update/{id}")
    MongoPhotoDto updateDadPhoto(@PathVariable("id") UUID id, @RequestBody MongoPhotoDto mongoPhotoDto) {
        return userService.updateDadPhoto(id, mongoPhotoDto);
    }

    @DeleteMapping("/deleteChildPhoto/{id}")
    void deleteChildPhoto(@RequestParam("id") UUID id) {
        userService.deleteChildPhoto(id);
    }

    @DeleteMapping("/deleteMomPhoto/{id}")
    void deleteMomPhoto(@RequestParam("id") UUID id) {
        userService.deleteMomPhoto(id);
    }

    @DeleteMapping("/deleteDadPhoto/{id}")
    void deleteDadPhoto(@RequestParam("id") UUID id) {
        userService.deleteDadPhoto(id);
    }


}
