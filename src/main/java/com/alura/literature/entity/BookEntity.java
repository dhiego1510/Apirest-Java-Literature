package com.alura.literature.entity;

import com.alura.literature.model.Author;
import com.alura.literature.model.Book;
import com.alura.literature.util.StringUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String language;
  private Integer downloads;
  @OneToOne(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private AuthorEntity author;

  public BookEntity() {

  }

  public BookEntity(Book book) {
    this.title = StringUtil.limitLength(book.title(), 200);
    this.downloads = book.download();
    if (!book.languages().isEmpty())
      this.language = book.languages().get(0);
    if (!book.authors().isEmpty()) {
      for (Author author : book.authors()) {
        this.author = new AuthorEntity(author);
        break;
      }
    }

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public void setDownloads(Integer downloads) {
    this.downloads = downloads;
  }

  @Override
  public String toString() {
    return "LibroEntity [id=" + id + ", titulo=" + title+ ", lenguaje=" + language +
            ", descargas=" + downloads
            + ", autores=" + author + "]";
  }

  public AuthorEntity getAuthor() {
    return author;
  }

  public void setAuthor(AuthorEntity author) {
    this.author = author;
  }

}
