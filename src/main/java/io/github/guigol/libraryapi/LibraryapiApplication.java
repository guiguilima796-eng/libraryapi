package io.github.guigol.libraryapi;

import io.github.guigol.libraryapi.model.Autor;
import io.github.guigol.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryapiApplication.class, args);
		AutorRepository autorRepository = SpringApplication.run(LibraryapiApplication.class, args).getBean(AutorRepository.class);
		salvar(autorRepository);
	}

	public static void salvar(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Guilherme Silva");
		autor.setNacionalidade("Brasileiro");
		autor.setData_nascimento(LocalDate.of(2007, 9, 11));

		var autorSalvo = autorRepository.save(autor);

		System.out.println("Autor salvo: " + autorSalvo.getId() + " - " + autorSalvo.getNome());
	}

}
