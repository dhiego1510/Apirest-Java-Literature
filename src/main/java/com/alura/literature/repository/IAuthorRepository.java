package com.alura.literature.repository;

import java.util.List;
import com.alura.literature.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAuthorRepository extends JpaRepository<AuthorEntity, Long> {

  @Query("SELECT a FROM AuthorEntity a WHERE :anio between a.birthDate AND a.dateOfDeath")
  List<AuthorEntity> findForYear(int anio);

}
