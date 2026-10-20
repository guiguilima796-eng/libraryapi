package io.github.guigol.libraryapi.model;

import io.github.guigol.libraryapi.Genero;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String isbn;

    private String titulo;

    private LocalDate data_publicacao;


    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

}
