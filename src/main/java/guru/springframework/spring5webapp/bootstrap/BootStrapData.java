package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher oreily = new Publisher("oreily");
        publisherRepository.save(oreily);


        Author evan = new Author("evan", "adams");
        Book ddd = new Book("DDD", "123123");

        ddd.getAuthors().add(evan);
        evan.getBooks().add(ddd);

        ddd.setPublisher(oreily);
        oreily.getBooks().add(ddd);

        authorRepository.save(evan);
        bookRepository.save(ddd);
        publisherRepository.save(oreily);


        Author skiena = new Author("stiven", "skiena");
        Book algo = new Book("algo", "234234");

        algo.getAuthors().add(skiena);
        skiena.getBooks().add(algo);

        oreily.getBooks().add(algo);
        algo.setPublisher(oreily);

        authorRepository.save(skiena);
        bookRepository.save(algo);
        publisherRepository.save(oreily);

        System.out.println("Bootstrap loaded");
        System.out.println("books:" + bookRepository.count());
        System.out.println("authors:" + authorRepository.count());
        System.out.println("publishers:" + publisherRepository.count());

//        System.out.println(bookRepository.findAll());
//        System.out.println(bookRepository.findById(1L));
    }
}
