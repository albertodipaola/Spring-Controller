package com.example.jpacontroller.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.jpacontroller.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("DELETE FROM Comment c WHERE c.email LIKE 'tizio%'")
    public void deleteAlberto();
}
