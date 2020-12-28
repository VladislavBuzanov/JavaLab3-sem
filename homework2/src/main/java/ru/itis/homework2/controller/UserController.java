package ru.itis.homework2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.homework2.model.User;
import ru.itis.homework2.producer.Producer;

@RestController
public class UserController {
    @Autowired
    private Producer producer;

    @PostMapping("/produce")
    public ModelAndView produce(@RequestParam String queue,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String age,
                          @RequestParam String passportDate,
                          @RequestParam String passportNumber) {
        producer.send(new User(name, surname, passportNumber, age, passportDate), queue);
        return new ModelAndView("page");
    }

    @GetMapping("/index")
    public ModelAndView frontPage() {
        return new ModelAndView("page");
    }

}