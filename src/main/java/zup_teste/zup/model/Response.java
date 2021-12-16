package zup_teste.zup.model;

public class Response {
    private Object resultado; //objeto genérico que será retornado como resposta
    private String message; //mensagem a ser enviada junto com o resultado


    public Response() {
    }

    public Response(Object resultado, String message) {
        this.resultado = resultado;
        this.message = message;
    }

    public Object getResultado() {
        return this.resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   

}
