package zup_teste.zup.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //mapeado como tabela no banco
public class Author {

    @Id //chave primária
    @GeneratedValue(strategy=GenerationType.AUTO) //o valor do ID será gerado automaticamente
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name; //nome do autor
    
    @JsonIgnore //deve ser ignorado no caso de ser enviado num JSON
    @ManyToMany(mappedBy="autores") //mapeamento de muitos para muitos com a tabela comics
    private List<Comic> comics; //lista de quadrinhos que esse autor está associado

    public Author(String name) { //construtor só com o nome do autor
        this.name = name;
    }

    public Author() {
    }

    public Author(String name, List<Comic> comics) {
        this.name = name;
        this.comics = comics;
    }

    public List<Comic> getComics() {
        return this.comics;
    }

    public void setComic(List<Comic> comics) {
        this.comics = comics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    
 
}
