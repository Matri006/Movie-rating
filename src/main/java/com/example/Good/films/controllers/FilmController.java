package com.example.Good.films.controllers;


import com.example.Good.films.models.Film;
import com.example.Good.films.repositories.FilmRepository;
import com.example.Good.films.services.FilmService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class FilmController {
    private final FilmService filmService;
    private final FilmRepository filmRepository;

    public FilmController(FilmService filmService, FilmRepository filmRepository){
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    @PostMapping("/add-movie")
    public String addMovie(
            @RequestParam("name") String name,
            @RequestParam("genre") String genre,
            @RequestParam("url") MultipartFile file,
            @RequestParam(value = "count_o", defaultValue = "0") int count_o,
            @RequestParam(value = "ball", defaultValue = "0") float ball,
             Model model){

        System.out.println("Получен фильм:");
        System.out.println("Название: " + name);
        System.out.println("Жанр: " + genre);


        try {
            Film savedFilm = filmService.addFilm(name, genre, file, count_o, ball);
            System.out.println("Имя " + name);
            System.out.println("Жанр " + genre);
            System.out.println(name + " " + genre);
            System.out.println("Фильм после сохранения: " + savedFilm);
            model.addAttribute("film", savedFilm);
            return "home";
        } catch (IOException e) {
            model.addAttribute("error", "Ошибка обработки файла");
            return "add-movie";
        }




    }
    @GetMapping("/movies")
    public String movies(Model model){
        List<Film> movies = filmService.getAllFilms();
        System.out.println("Передаём в модель: " + movies.size() + " фильмов");
        model.addAttribute("movies", movies);
        return "movies";
    }

    @PostMapping("/movies/rate")
    public String updateFilm(@RequestParam int id, @RequestParam() float ball) {
        filmService.updateFilm(id, ball);
        return "redirect:/movies";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<Film> movies = filmRepository.getTopFilms();
        System.out.println("Передаём в модель: " + movies.size() + " фильмов");
        model.addAttribute("movies", movies);
        return "home";
    }



}
