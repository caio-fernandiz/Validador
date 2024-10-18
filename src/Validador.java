import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
                //Irei fazer isso para os diferentes tipos de informações
                //que serão digitadas pelo usuário.
                String nomeDigitado = janela.getJtfDigitaNome().getText();
                String dataDigitada = janela.getJtfDigitaData().getText();
                String cpfDigitado = janela.getJtfDigitaCPF().getText();
                String emailDigitado = janela.getJtfDigitaEmail().getText();

                //Esse "if" irá fazer a validação do nome, se essa situação 
                //acontecer, então o nome não irá aparecer na label que recebe 
                //o nome e será enviado um aviso em pop-up
                //Isso só é possível pois a condição exige que "nomeDigitado"
                //seja igual a nada, usei o equals justamente para isso.
                //Da pra ver que só "" sem nada dentro entre os parenteses,
                //isso que determina o "nada". Isso também serve para as
                //outras variáveis, caso qualquer uma delas tenha uma informação
                //faltando. Isso só funciona por causa do "||", que é o mesmo que
                //"ou". Explicando melhor, se nome ou data não tiver nada, irá receber
                //um aviso, ou seja, se nome tiver informação mas a data não tem nada, 
                //o aviso será enviado da mesma forma. 
                if(nomeDigitado.equals("") || dataDigitada.equals("")){

                    JOptionPane.showMessageDialog(null, "Está falantando informações");
                }

                //Agora eu criei um "else" que irá ter vários "if's" dentro 
                //que irão servir para cada situação de cada dado informado pelo 
                //usário.
                else {
                
                    //Esse "if" será responsável por impedir um nome que tenha qualquer caractere 
                    //que não seja uma letra seja validado pelo programa.
                    if(!nomeDigitado.matches(".*[\\p{L}]+\\s+[\\p{L}]+.*")){
                        JOptionPane.showMessageDialog(null, "Nome inválido");
                    }

                    //Esse "if" será responsável por impedir uma data que tenha qualquer caractere 
                    //que não seja um número ou uma barra seja validado pelo programa.
                    if(!dataDigitada.matches("\\d{2}/\\d{2}/\\d{4}")){
                        JOptionPane.showMessageDialog(null, "Data inválida");
                    }

                    //Esse "else if" é o responsável por validar todas as informações dadas 
                    //pelo usuário caso elas estejam de acordo com o esperado e não sejam 
                    //barradas por nenhum dos "if" anteriores. Isso é, se forem corretamente 
                    //digitados. Ainda irei fazer a validação caso não estejam de acordo com 
                    //outras regras.
                    else if(nomeDigitado.matches(".*[\\p{L}]+\\s+[\\p{L}]+.*") && dataDigitada.matches("\\d{2}/\\d{2}/\\d{4}")){

                        //Esse if é para conferir se a data é válida mesmo que ela
                        //tenha sido digitda da maneira correta.
                        if(!dados.setData(dataDigitada)){
                            JOptionPane.showMessageDialog(null, "Data inválida");
                        }

                        else{
                        
                        //E aqui uma variável "boolean" "dataValida" irá enviar 
                        //a informação para a várivael "data" na classe "Dados".
                        //Ela será a responsável por falar se a data é verdadeira.
                        boolean dataValida = dados.setData(dataDigitada);

                        //Aqui irei adicionar um calcúlo para que só permita a validação 
                        //da data caso o usuário seja maior de idade.

                        //Aqui eu crio a variável "dataNascimento" do tipo "Date",
                        //ela pega a "data" que é informada antes e que vai para a 
                        //classe "dados" utilizando o método get "DataParaDate"
                        //sem se intromoeter na data informada no método get "getData"
                        //que será responsável por informar a digitada caso seja válidada.
                        Date dataNascimento = dados.getDataParaDate();
                        //Aqui eu converto a váriavel dataNascimento para o tipo "LocalDate".
                        //Primeiro eu converto o tipo "Date" para "Instant", para que a varíavel
                        //seja mais precisa, depois eu converto em um objeto do tipo "Zone" ou
                        //"ZoneDateTime", assim ele fica com um fusu horário definido, que nesse caso 
                        //é o do computador, já que o tipo "Instant" não é dependente de fusu horários.
                        //E então o "LocalDate" vai extrair somente a data, sem 
                        //horas, minutos ou segundos. Dessa maneira ele irá pegar o dia, mês e o ano 
                        //de nascimento do usuário e irá calcular todos.
                        LocalDate dataNascimentoLocal = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        //E aqui nós estamos pegando a data atual, que sempre será a data 
                        //em que se iniciar o programa.
                        LocalDate hoje = LocalDate.now();
                        //Aqui eu calculo a diferença entre a data atual e a data de nascimento
                        //utilizando o "Period.between".
                        Period idade = Period.between(dataNascimentoLocal, hoje);

                        //E aqui irei verificar a idade, caso seja menor de 18 anos o 
                        //usuário ira receber esse aviso.
                        if(idade.getYears() < 18){
                            JOptionPane.showMessageDialog(null, "Idade inválida. É necessário ter 18 anos ou mais.");
                        }

                        //E finalmente, se tudo estiver de acordo, o programa 
                        //irá validar.
                        else{
                        //E aqui a string chamada "nomeDigitado" irá enviar 
                        //a informação para a várivael "nome" na classe "Dados".
                        dados.setNome(nomeDigitado);

                        janela.getJlRecebeNome().setText(dados.getNome());
                        janela.getJlRecebeData().setText(dados.getData());
                        }
                        }

                    }
                }
            }
        });

    }
}
