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


    //Aqui irei gerar os getter e setter de cada varíavel. 
    //Eles são metódos que permitem o acesso indireto 
    //para as varíaveis privadas dessas classe.
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }    
}
