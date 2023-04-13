package com.example.jpacontroller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpacontroller.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.firstname='Alberto'")
    public List<Post> findAlberto();

    @Query("SELECT p FROM Post p WHERE p.author.firstname=?1")
    public List<Post> findAuthorFirstname(String firstname);

    @Query("SELECT p FROM Post p WHERE p.author.firstname=:firstname AND p.author.lastname=:lastname")
    public List<Post> findAuthorFirstnameAndLastname(
        @Param("firstname")
        String firstname, 
        @Param("lastname")
        String lastname
        );

}
