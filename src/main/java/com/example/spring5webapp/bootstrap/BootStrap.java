package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {


    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Publisher publisher1 = new Publisher();
        publisher1.setName("Oreilly");
        publisher1.setAddress("address1");
        publisherRepository.save(publisher1);

        Author author_1 = new Author("Kathy", "Sierra");
        Author author_2 = new Author("Bert", "Bates");

        Book book1 = new Book("Head First Java", "isbn1",publisher1);

        author_1.getBooks().add(book1);
        book1.getAuthors().add(author_1);
        book1.getAuthors().add(author_2);

        authorRepository.save(author_1);
        authorRepository.save(author_2);
        bookRepository.save(book1);

        Author author2 = new Author("Alan", "Mycroft");
        Book book2 = new Book("Java 8 in Action", "isbn2",publisher1);

        book2.getAuthors().add(author2);
        author2.getBooks().add(book2);

        authorRepository.save(author2);
        bookRepository.save(book2);


    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
