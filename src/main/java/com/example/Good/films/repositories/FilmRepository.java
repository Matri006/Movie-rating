package com.example.Good.films.repositories;

import com.example.Good.films.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>{
    @Query("SELECT f FROM Film f WHERE f.raiting >  7.0")
    public List<Film> getTopFilms();
}
