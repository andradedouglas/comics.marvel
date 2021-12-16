package zup_teste.zup.service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zup_teste.zup.model.Comic;
import zup_teste.zup.repository.ComicRepository;
import zup_teste.zup.repository.UserRepository;

@Service //annotation indicando que é um service
public class UserService {
    //coloca para o Spring instanciar os repositórios
    @Autowired
    UserRepository userRepository; 

    @Autowired
    ComicRepository comicRepository;

    //Esse método irá receber o cpf, localizar o user, obter a sua lista de comics, aplicar o desconto e retorná-la
    public List<Comic> getComicsFromUserByCPF(String cpf){ 
        List<Comic> comics = userRepository.getById(cpf).getComics(); //pega a lista de comics do usuário

        for(Comic c : comics){ //percorre a lista de comics e, para cada um deles,
            aplicarDesconto(c); //aplica o método que, pode ou não, gerar o desconto
        }
        return comics; //retorna a lista de comics com o desconto aplicado
    }

    public void aplicarDesconto(Comic c){ //esse método aplica o desconto a um quadrinho, caso seja aplicável
        Calendar cal = Calendar.getInstance(); //pega uma instância do calendário
        cal.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo")); //define o fuso horário brasileiro
        int day = cal.get(Calendar.DAY_OF_WEEK); //capta o dia da semana (segunda = 2, terça = 3, ...)
        
        switch(day){ //irá olhar o valor dentro da variável dia para ver em qual caso se aplica
            case 2: //segunda-feira
                if(c.getIsbn().endsWith("0") || c.getIsbn().endsWith("1") ){ //se o último dígito do isbn for 0 ou 1
                    c.setDesconto(true); //transforma o atributo desconto em true
                    c.setPreco(c.getPreco()*0.9f); //abate os 10% no preço
                }
                break;
                
            case 3: //terça
                if(c.getIsbn().endsWith("2") || c.getIsbn().endsWith("3") ){
                    c.setDesconto(true);
                    c.setPreco(c.getPreco()*0.9f); 
                }
                break;
                
            case 4: //quarta
                if(c.getIsbn().endsWith("4") || c.getIsbn().endsWith("5") ){
                    c.setDesconto(true);
                    c.setPreco(c.getPreco()*0.9f);
                }  
                break;
                
            case 5: //quinta
                if(c.getIsbn().endsWith("6") || c.getIsbn().endsWith("7") ){
                    c.setDesconto(true);
                    c.setPreco(c.getPreco()*0.9f);
                }    
                break;
                
            case 6: //sexta
                if (c.getIsbn().endsWith("8") || c.getIsbn().endsWith("9") ){
                    c.setDesconto(true);
                    c.setPreco(c.getPreco()*0.9f);
                } 
                break;
                
            default: //caso não se aplique em nenhum dos casos anteriores, mantém o atributo desconto como false
                c.setDesconto(false); 
         }
      
    }
    
}
