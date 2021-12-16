package zup_teste.zup.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "marvelAPI", url = "http://gateway.marvel.com/v1/") //integração com api externa utilizando o Feign e citando o início da url
public interface MarvelFeign {

    @RequestMapping(method = RequestMethod.GET, value = "public/comics/{comicId}?ts={timestamp}&apikey={apikey}&hash={md5}") //GET e passa o resto da url com as variáveis necessárias
    ResponseBodyComic getComic(@PathVariable (name= "comicId") Integer comicId, //método para retornar os dados de um quadrinho
                    @PathVariable (name= "timestamp") Integer timestamp, 
                    @PathVariable (name= "apikey") String apikey,
                    @PathVariable (name= "md5") String md5);
                
}
