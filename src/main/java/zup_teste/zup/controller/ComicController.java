package zup_teste.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import zup_teste.zup.model.Author;
import zup_teste.zup.model.Comic;
import zup_teste.zup.model.ComicDTOUser;
import zup_teste.zup.model.Response;
import zup_teste.zup.proxy.MarvelFeign;
import zup_teste.zup.proxy.ResponseBodyComic;
import zup_teste.zup.proxy.ResultsComic;
import zup_teste.zup.repository.AuthorRepository;
import zup_teste.zup.repository.ComicRepository;
import zup_teste.zup.repository.UserRepository;

@RestController
public class ComicController {
    @Value("${marvel.md5}") //captura a informação que guardamos lá em application.properties
    private String md5;

    @Value("${marvel.apikey}") //captura a informação que guardamos lá em application.properties
    private String apikey;

    @Value("${marvel.ts}") //captura a informação que guardamos lá em application.properties
    private Integer timestamp;

    @Autowired
    MarvelFeign feign; //objeto para instanciar a conexão com a API Marvel

    @Autowired
    ComicRepository comicRepository; //objeto da persistência para quadrinhos

    @Autowired
    AuthorRepository authorRepository; //objeto da persistência para autores

    @Autowired
    UserRepository userRepository; ///objeto da persistência para usuários

    @PostMapping("/comic/add") //método POST
    public ResponseEntity<Response> insertComic(@RequestBody ComicDTOUser comicDTOUser) { //recebe nesse objeto o cpf e o comicId que vem no JSON
        Response response = new Response(); //cria um objeto do tipo UserResponse
        if(userRepository.findById(comicDTOUser.getCpf()).isPresent()){ //se o usuário ja existir
            
            ResponseBodyComic c = feign.getComic(comicDTOUser.getComicId(), timestamp, apikey, md5); //captura as info do quadrinho na API externa e joga nesse objeto
            ResultsComic r = c.getData().getResults().get(0);//ResponseBody> Data > Results | get(0) porque só ha 1 elem na lista (conferir na documentação da API Marvel)
            List<Author> l = r.getCreators().getItems(); //Results > Creators > Items> Lista de autores
            for (Author a : l)  //para cada Author na lista
                authorRepository.save(a); //cadastre um novo autor, associado ao quadrinho X
            //Instancia um novo quadrinho
            Comic comic = new Comic(comicDTOUser.getComicId(), r.getTitle(), r.getPrices().get(0).getPrice(), r.getIsbn(), r.getDescription(), l, userRepository.getById(comicDTOUser.getCpf()));
            response.setResultado(comicRepository.save(comic)); //salva o comic no repositório
            response.setMessage("Comic inserido com sucesso no banco!"); //define a mensagem a ser apresentada
            return new ResponseEntity<>(response, HttpStatus.CREATED); //retorna o UserResponse com o codigo http 201
         }else{ //caso o usuário não exista no banco
            response.setMessage("Usuário não cadastrado"); //define a mensagem
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
        } 

    }
    
            

}
