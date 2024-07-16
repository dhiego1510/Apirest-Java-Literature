package com.alura.literature.repository;

import com.alura.literature.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookEntity, Long> {
  @Query("SELECT l FROM BookEntity l WHERE l.language >= :language")
  List<BookEntity> findForLanguaje(String language);
}
