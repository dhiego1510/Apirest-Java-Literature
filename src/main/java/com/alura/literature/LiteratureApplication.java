package com.alura.literature;

import com.alura.literature.client.LiteratureManager;
import com.alura.literature.repository.IAuthorRepository;
import com.alura.literature.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteratureApplication implements CommandLineRunner {
  @Autowired
  private IBookRepository bookRepository;
  @Autowired
  private IAuthorRepository authorRepository;

  public static void main(String[] args) {

	  SpringApplication.run(LiteratureApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    LiteratureManager client = new LiteratureManager(bookRepository, authorRepository);
    client.menu();

  }
}
