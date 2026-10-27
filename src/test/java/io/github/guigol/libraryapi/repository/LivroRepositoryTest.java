package io.github.guigol.libraryapi.repository;

import io.github.guigol.libraryapi.Genero;
import io.github.guigol.libraryapi.model.Autor;
import io.github.guigol.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository repository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void testSaveLivro() {
        // Implement your test logic here
        Livro livro = new Livro();
        livro.setTitulo("Test Book");
        livro.setIsbn("1234567890");
        livro.setData_publicacao(java.time.LocalDate.now());
        livro.setGenero(Genero.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(29.99));

        Autor autor = autorRepository.findById(UUID.fromString("430f56d4-40cf-4263-8c75-8888ee0315ac")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);

        System.out.println("Livro salvo: " + livro.getId() + " - " + livro.getTitulo());
    }

    @Test
    void testSaveLivroCascade() {
        // Implement your test logic here
        Livro livro = new Livro();
        livro.setTitulo("Test Book");
        livro.setIsbn("1234567890");
        livro.setData_publicacao(java.time.LocalDate.now());
        livro.setGenero(Genero.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(29.99));

        Autor autor = new Autor();
        autor.setNome("New Author");
        autor.setNacionalidade("Unknown");
        autor.setData_nascimento(java.time.LocalDate.of(1990, 1, 1));

        livro.setAutor(autor);

        repository.save(livro);

        System.out.println("Livro salvo: " + livro.getId() + " - " + livro.getTitulo());
    }
}