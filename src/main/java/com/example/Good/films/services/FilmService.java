package com.example.Good.films.services;


import com.example.Good.films.models.Film;
import com.example.Good.films.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmService {
    private FilmRepository filmRepository;
    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";


    @Autowired
    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public Film addFilm(String name, String genre, MultipartFile file, int count_o, float ball) throws IOException {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Сохранение в БД:");
        System.out.println("Название: " + name);
        System.out.println("Жанр: " + genre);
        Film film = new Film();
        film.setName(name);
        film.setGenre(genre);
        film.setCount_o(0);
        film.setBall(0);
        film.setRaiting();
        film.setUrl("/uploads/" + fileName);

        System.out.print(film);
        return filmRepository.save(film);
    }

    @Query("select name, genre, url, raiting from films")
    public List<Film> getAllFilms(){
        System.out.print("Загружено фильмов: " + filmRepository.findAll().size());
        return filmRepository.findAll();
    }

    public Film updateFilm(int id, float ball ){
        Optional<Film> ofilm = filmRepository.findById(id);
        if(ofilm.isPresent()){
            Film film = ofilm.get();
            film.setCount_o(1);
            film.setBall(ball);
            film.setRaiting();
            return filmRepository.save(film);
        }
        throw new RuntimeException("Film not found");
    }



}
