import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dados {
    /*
     * Nesta classe eu irei definir as varíaveis que serão utilizidas
     * no código. Será a classe mais simples por ter pouco conteúdo nela.
     */
    
     //Aqui é a varíavel que será utilizada para guardar o nome
     //do usuário. Podendo ser alterada sempre que um novo nome
     //for digitado. Todas as classe são privadas para que não 
     //aconteça nenhum provblema de utilizar elas em classes que
     //não deveriam utilizar elas
    private String nome;

    //Aqui é a varíavel que irei utilizar para guardar a data
    //digitada pelo usuário. Ela ainda não será utilizada pois 
    //preciso converter para um tipo String.
    private Date data;
    //Para conseguir converter pra um tipo string eu preciso 
    //usar a calasse "SimpleDataFormat"
    SimpleDateFormat formataData= new SimpleDateFormat("dd/MM/yyyy");
    
    


    //Aqui irei gerar os getter e setter de cada varíavel. 
    //Eles são metódos que permitem o acesso indireto 
    //para as varíaveis privadas dessas classe.
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }    

    //Será necessário criar um método para definir a data a partir 
    //de uma String. Aassim será feita a conversão. O motivo dessa
    //conversão ser necessário é porque o tipo de váriavel "Date"
    //não consegue ser alterada ou interagir com JTextFiedls e parecidos.
    public boolean setData(String dataStr){
        //Esse "try" é o que tenta converter o tipoi "Date" para "String"
        //Se caso a data seja válida, será retornado como verdadeiro.
        try {
            this.data = formataData.parse(dataStr);
            return true;
            //No "catch" será pego qualquer excessão, ou seja, se a data for 
            //inválida, será retornado como falsa.
        } catch (ParseException e) {
            return false;
        }
    }

    //E esse método será responsável por obter a data formata
    //como String, sendo um tipo de tradução do tipo "String"
    //para o tipo "Date".
    public String getData(){
        if(data != null){
            return formataData.format(data);
        }
        return "";
    }
}
