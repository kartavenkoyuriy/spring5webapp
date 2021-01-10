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
        Author evan = new Author("evan", "adams");
        Book ddd = new Book("DDD", "123123");
        Publisher oreily = new Publisher("oreily");
        oreily.getBooks().add(ddd);
        evan.getBooks().add(ddd);
        ddd.getAuthors().add(evan);
        ddd.setPublisher(oreily);
        bookRepository.save(ddd);
        authorRepository.save(evan);
        publisherRepository.save(oreily);

        Author skiena = new Author("stiven", "skiena");
        Book algo = new Book("algo", "234234");
        oreily.getBooks().add(algo);
        publisherRepository.save(oreily);
        skiena.getBooks().add(algo);
        algo.getAuthors().add(skiena);
        algo.setPublisher(oreily);
        bookRepository.save(algo);
        authorRepository.save(skiena);

        System.out.println("Bootstrap loaded");
        System.out.println("books:" + bookRepository.count());
        System.out.println("authors:" + bookRepository.count());
        System.out.println("publishers:" + bookRepository.count());

//        System.out.println(bookRepository.findAll());
//        System.out.println(bookRepository.findById(1L));
    }
}
