package zup_teste.zup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import zup_teste.zup.model.User;
import zup_teste.zup.model.Comic;
import zup_teste.zup.model.Response;
import zup_teste.zup.repository.UserRepository;
import zup_teste.zup.service.UserService;

@RestController //indica que será uma classe controller RESTful
public class UserController {
    
    @Autowired //injeção de depedência: delegar ao Spring a instanciação do objeto
    UserRepository userRepository;

    @Autowired
    UserService userService;
    
    @PostMapping("/user/add") //endpoint indicando o tipo da requisição (POST) e o caminho a ser inserido na url
    public ResponseEntity<Response> addUser(@Valid @RequestBody User user){ //os dados do User serão inseridos no body da requisição POST
        User sameCpf = userRepository.findByCpf(user.getCpf()); //guarda o teste da verificação se acha algum usuário com mesmo cpf
        User sameEmail = userRepository.findByEmail(user.getEmail()); //mesma coisa com o email
        Response response = new Response(); //cria um objeto do tipo UserResponse
        if(sameCpf == null && sameEmail == null){ //verifica se não há outro user com mesmo cpf e email
            response.setResultado(userRepository.save(user)); //salva o usuário no repositório
            response.setMessage("Inserido com sucesso!"); //define a mensagem a ser apresentada
            return new ResponseEntity<>(response, HttpStatus.CREATED); //retorna o UserResponse com o codigo http 201
        }else{ //caso haja algum user com mesmo cpf ou email
            response.setMessage("Usuário já cadastrado"); //define a mensagem
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //retorna null mas com a mensagem e o codigo http 400
        }
    }
    
    @GetMapping("/users") //método GET
    public ResponseEntity<Object> getAllUsers(){
        List<User> l = userRepository.findAll(); //seleciona todos os usuários e coloca numa lista
        Response response = new Response();
        if(l.isEmpty()){ // se a lista está vazia então é porque não há usuários cadastrados
             response.setMessage("Nenhum usuário cadastrado");
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{ //a lista não está vazia, então temos pelos menos 1 usuário cadastrado
            response.setResultado(l);
            response.setMessage("Estes são os usuários cadastrados");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    
    @GetMapping("/user/{cpf}/comics") //define que será um GET que recebe dentro da url o valor do cpf do user
    public ResponseEntity<Response> getComicsFromUserByCPF(@Valid @PathVariable String cpf){
        Response response = new Response();
        if(userRepository.findById(cpf).isEmpty()){ //se o cpf não é encontrado no banco como cadastrado em algum user
            response.setMessage("Usuário não cadastrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else{ //se encontrou um user com aquele cpf
            List<Comic> l= userService.getComicsFromUserByCPF(cpf); //chama o método do UserService que percorre a lista de comics e aplica o desconto
            response.setResultado(l);
            response.setMessage("Lista atualizada com sucesso");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}
