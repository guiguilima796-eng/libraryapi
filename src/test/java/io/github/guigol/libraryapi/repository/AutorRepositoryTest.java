package io.github.guigol.libraryapi.repository;

import io.github.guigol.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvar(){
        Autor autor = new Autor();
        autor.setNome("Guilherme Pereira Lima");
        autor.setNacionalidade("Brasileiro");
        autor.setData_nascimento(LocalDate.of(2007, 9, 11));

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor salvo: " + autorSalvo.getId() + " - " + autorSalvo.getNome());
    }

    @Test
    public void atualizarTeste(){
        Autor autor = new Autor();
        var id = UUID.fromString("7a56c9d6-d957-483e-889a-812ef540548");
        Optional<Autor> autorExistente = autorRepository.findById(id);

        if (autorExistente.isPresent()) {

            autor = autorExistente.get();

            autor.setNome("Luiz Suárez");
            autor.setNacionalidade("Uruguaio");
            autor.setData_nascimento(LocalDate.of(1985, 9, 20));

            var autorAtualizado = autorRepository.save(autor);

            System.out.println("Autor atualizado: " + autorAtualizado.getId() + " - " + autorAtualizado.getNome());

            return;
        }

        System.out.println("Autor não encontrado para atualização.");

    }
    @Test
    public void listarTest(){
        var autores = autorRepository.findAll();

        autores.forEach(autor -> {
            System.out.println("Autor: " + autor.getId() + " - " + autor.getNome());
        });
    }

    @Test
    public void cointTest(){
        var count = autorRepository.count();

        System.out.println("Total de autores: " + count);
    }

    @Test
    public void deletByIdTest(){
        var id = UUID.fromString("7a56c9d6-d957-483e-889a-812ef540548");
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            System.out.println("Autor deletado com sucesso.");
            return;
            }


        System.out.println("Autor não encontrado para deleção.");
    }

    @Test
    public void deletByObjectTest(){
        var id = UUID.fromString("15a584aa-c788-40dc-aa35-b34e88054598");
        Optional<Autor> autorExistente = autorRepository.findById(id);

        if (autorExistente.isPresent()) {
            autorRepository.delete(autorExistente.get());
            System.out.println("Autor deletado com sucesso.");
            return;
        }

        System.out.println("Autor não encontrado para deleção.");
    }
}
