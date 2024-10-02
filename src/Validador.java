import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Validador {

    /*
     * Essa classe será utilizada para fazer tudo acontecer.
     * Todos os comandos serão feitos pelos metódos codados aqui. 
     * A validação de cada dado será feita aqui, aonde irá informar 
     * se foi validado, se não foi ou se está faltando informação.
     */

     //Aqui estou declarando as variáveis que serão responsáveis por transmitir 
     //as informações entre as classes.
    private Janela janela;
    private Dados dados;

    //Aqui está o constructor da classe, que recebe a janela e os dados.
    public Validador(Janela janela, Dados dados){

        this.janela = janela;
        this.dados = dados;
        
        //Aqui irei fazer o método que fará o botão da janela funcionar.
        //O "ActionListener" é o que faz o botão funcionar, executando a
        //ação ordernada quando ele for clicado.
        janela.getJbBotaoValidar().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e){
                //Aqui eu criei uma string chamada "nomeDigitado" para 
                //que a caixa de texto que serve para digitar o nome 
                //envie a informação para essa váriavel.
                String nomeDigitado = janela.getJtfDigitaNome().getText();
                //E aqui a string chamada "nomeDigitado" irá enviar 
                //a informação para a várivael "nome" na classe "Dados".
                dados.setNome(nomeDigitado);

                //E aqui a informação do nome será enviada para a janela 
                //que irá mostrar o nome quando ele for validado
                janela.getJlRecebeNome().setText(dados.getNome());
            }
        });

    }
}
