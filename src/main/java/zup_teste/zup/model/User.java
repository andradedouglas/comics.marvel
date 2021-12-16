package zup_teste.zup.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.br.CPF;


@Entity //indica que será uma tabela no banco
public class User {

    @Id //Indica que será uma chave primária na tabela
    @NotNull(message = "CPF é obrigatório") //não pode ser nulo
    @CPF(message = "CPF inválido") //valida se é um cpf válido
    @Column(unique = true, nullable = false) //coluna na tabela única e não-nula
    private String cpf;

    @NotNull(message = "Nome é obrigatório")
    @Column(nullable = false, length = 50) //tamanho 50
    private String nome;

    @Email(message = "Email inválido")//valida se é um email válido
    @NotNull(message = "Email é obrigatório")
    @Column(nullable = false, length = 50)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy") //valida se o formato da data segue esse padrão
    @NotNull(message = "Data de Nascimento é obrigatório")
    @Column(nullable = false, length = 10)
    private LocalDate dataNascimento;

    @JsonIgnore //Não será mostrado no JSON num caso de GET
    @ManyToMany(mappedBy="users") //Mapeamento de relacionamento muitos para muitos
    private List<Comic> comics; //lista de quadrinhos que o usuário possui

    public User(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public User() {

    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Comic> getComics() {
        return this.comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
}
