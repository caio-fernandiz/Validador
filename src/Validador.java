import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

                //Esse "if" irá fazer a validação do nome, se essa situação 
                //acontecer, então o nome não irá aparecer na label que recebe 
                //o nome e será enviado um aviso em pop-up
                //Isso só é possível pois a condição exige que "nomeDigitado"
                //seja igual a nada, usei o equals justamente para isso.
                //Da pra ver que só "" sem nada dentro entre os parenteses,
                //isso que determina o "nada".
                if(nomeDigitado.equals("")){

                    JOptionPane.showMessageDialog(null, "Está falantando informações");
                }

                //Já esse else if irá ser responsável por enviar o nome.
                //Aqui eu consigo fazer o equals desejar algo diferente 
                //do que está entre os parentenses, eu faço isso colocando
                //o "!" antes de "nomeDigitado".
                else if(!nomeDigitado.equals("")){
                //E aqui a string chamada "nomeDigitado" irá enviar 
                //a informação para a várivael "nome" na classe "Dados".
                dados.setNome(nomeDigitado);

                //E aqui a informação do nome será enviada para a janela 
                //que irá mostrar o nome quando ele for validado.
                janela.getJlRecebeNome().setText(dados.getNome());
                }
            }
        });

    }
}
