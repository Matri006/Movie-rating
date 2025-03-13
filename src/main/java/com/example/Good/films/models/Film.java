package com.example.Good.films.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLInsert;


@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "name", unique = false, nullable = false)
    private String name;
    @Column(name = "genre", unique = false, nullable = false)
    private String genre;

    @Column(name = "url", unique = true, nullable = false)
    private String url;



    @Column(name = "count_o", unique = false, nullable = false)
    private int count_o = 0;

    @Column(name = "ball", unique = false, nullable = false)
    private float ball = 0;

    @Column(name = "raiting", unique = false, nullable = false)
    private float raiting = 0;



    public int getId(){ return this.id;}
    public String getName(){ return this.name;}
    public String getGenre(){ return this.genre;}
    public String getUrl(){ return this.url;}
    public int getCount_o() {return this.count_o;}
    public float getBall(){return this.ball;}

    public float getRaiting() {
        return this.raiting;
    }

    public void setId(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setGenre(String genre){ this.genre = genre;}
    public void setUrl(String url){ this.url = url;}
    public void setCount_o(int count_o){this.count_o+=count_o;}
    public void setBall(float ball) {this.ball += ball;}
    public void setRaiting(){if (this.count_o == 0) this.raiting = 0; else this.raiting = this.ball/this.count_o;}

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}