package org.okten.javaadvanced.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.service.IDirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/directors")
@Slf4j

public class DirectorController {
    private IDirectorService directorService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Director saveDirector(@RequestBody Director director){
        directorService.insertDirector(director);
        log.info("Handling Director /Post with requestBody" + director);
        return director;
    }
    @GetMapping
    public List<Director> getAllDirectors(){
        return directorService.getDirectors();
    }
}
