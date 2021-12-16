package zup_teste.zup.model;

public class ComicDTOUser { //classe de comunicação com atributos específicos para realizar uma tarefa pontual
    private String cpf; //chave primária de User
    private Integer comicId; //chave primária de Comic

    public ComicDTOUser() {
    }

    public ComicDTOUser(String cpf, Integer comicId) {
        this.cpf = cpf;
        this.comicId = comicId;
    }


    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getComicId() {
        return this.comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }
}
