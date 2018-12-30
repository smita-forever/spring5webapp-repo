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
        publisher1.setName("publisher1");
        publisher1.setAddress("address1");
        publisherRepository.save(publisher1);

        Author author1 = new Author("fname1", "lname1");

        Book book1 = new Book("Book Title1", "isbn1",publisher1);

        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);


        authorRepository.save(author1);
        bookRepository.save(book1);

        Author author2 = new Author("fname2", "lname2");
        Book book2 = new Book("Book Title2", "isbn2",publisher1);

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
