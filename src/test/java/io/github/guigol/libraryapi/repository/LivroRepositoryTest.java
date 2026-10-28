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
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void testSaveLivro() {
        // Implement your test logic here
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setIsbn("1234567833");
        livro.setData_publicacao(java.time.LocalDate.now());
        livro.setGenero(Genero.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(50.00));

        Optional<Autor> autor = autorRepository.findById(UUID.fromString("430f56d4-40cf-4263-8c75-8888ee0315ac"));

        livro.setAutor(autor.orElse(null));

        livroRepository.save(livro);

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
        autor.setNome("Guilherme Pereira Lima");
        autor.setNacionalidade("Brasileiro");
        autor.setData_nascimento(java.time.LocalDate.of(2007, 1, 1));

        livro.setAutor(autor);

        livroRepository.save(livro);

        System.out.println("Livro salvo: " + livro.getId() + " - " + livro.getTitulo());
    }

    @Test
    void atualizarAutorDoLivro() {
        // Implement your test logic here
        Livro livro = livroRepository.findById(UUID.fromString("f50f277c-44cb-4da0-a9b6-7423f2e1fb8a")).orElse(null);

        if (livro != null) {
            Autor autor = autorRepository.findById(UUID.fromString("430f56d4-40cf-4263-8c75-8888ee0315ac")).orElse(null);
            livro.setAutor(autor);
            livroRepository.save(livro);

            System.out.println("Autor do livro atualizado: " + livro.getId() + " - " + livro.getTitulo());
        } else {
            System.out.println("Livro não encontrado para atualização.");
        }
    }

    @Test
    void deleteApenasLivro() {
        // Implement your test logic here
        UUID livroId = UUID.fromString("f50f277c-44cb-4da0-a9b6-7423f2e1fb8a");
        livroRepository.deleteById(livroId);

        System.out.println("Livro deletado: " + livroId);
    }

    @Test
    void deleteLivroCascade() {
        // Implement your test logic here
        UUID livroId = UUID.fromString("0ff1503d-4809-4f6c-a34b-fc579d10b730");
        livroRepository.deleteById(livroId);

        System.out.println("Livro deletado: " + livroId);
    }
}