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

        Author JRR = new Author("JRR", "Tolkien");
        Book LOR1 = new Book("Lord of the rings : Felloship of the ring", "100101");
        JRR.getBooks().add(LOR1);
        LOR1.getAuthors().add(JRR);

        authorRepository.save(JRR);
        bookRepository.save(LOR1);

        Author PatRoth = new Author("Patrick", "Rothfuss");
        Book NOW = new Book("Name of the Wind", "100102");
        PatRoth.getBooks().add(NOW);
        NOW.getAuthors().add(PatRoth);

        authorRepository.save(PatRoth);
        bookRepository.save(NOW);

        Publisher simon = new Publisher("Simon & Schuster", "100 Main St", "Ney York", "NY", "76890");

//        Publisher scholastic = new Publisher("Scholastic", "555 W Madison St", "Chicago", "IL", "60661");
//        publisherRepository.save(scholastic);

        simon.getBooks().add(NOW);
        simon.getBooks().add(LOR1);

        LOR1.setPublisher(simon);
        NOW.setPublisher(simon);

        publisherRepository.save(simon);



        System.out.println("Started in Bootstrap");
        System.out.println("Publisher count : " +publisherRepository.count());
        System.out.println("No of books : " + bookRepository.count());
        System.out.println("No of books to simon : " + simon.getBooks().size());

    }
}
