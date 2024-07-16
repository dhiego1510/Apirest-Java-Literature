package com.alura.literature.entity;

import com.alura.literature.model.Author;
import com.alura.literature.util.StringUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Author")
public class AuthorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Integer birthDate;
  private Integer dateOfDeath;


  @OneToOne
  @JoinTable(
          name = "Libro",
          joinColumns = @JoinColumn(name = "autor_id"),
          inverseJoinColumns = @JoinColumn(name = "id"))
  private BookEntity books;


  public AuthorEntity() {

  }

  public AuthorEntity(Author author) {
    this.name = StringUtil.limitLength(author.name(), 200);

    if (author.birthYear() == null)
      this.birthDate = 1980;
    else
      this.birthDate = author.birthYear();

    if (author.deathYear() == null)
      this.dateOfDeath = 3022;
    else
      this.dateOfDeath = author.deathYear();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getbirthDate() {
    return birthDate;
  }

  public void setbirthDate(Integer birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getdateOfDeath() {
    return dateOfDeath;
  }

  public void setdateOfDeath(Integer dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }


  @Override
  public String toString() {
    return "AutorEntity [id=" + id + ", nombre=" + name + ", fecha Nacimiento=" + birthDate
            + ", fecha Fallecimiento=" + dateOfDeath + ", libro="  + books + "]";
  }

  public BookEntity getBooks() {
    return books;
  }

  public void setBooks(BookEntity libros) {
    this.books = books;
  }

}