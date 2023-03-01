package ru.stazaev.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.stazaev.entity.DTO.UserSecretKey;
import ru.stazaev.entity.User;
import ru.stazaev.service.UserService;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(
            description = "Регистрация нового пользователя"
    )
    public UserSecretKey registration(@RequestBody User user){
        return userService.register(user);
    }


}
