import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

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
    //Aqui estarei adiconando a parte que irá definir os padrões de um e-mail.
    //Se o usuário não digitar algo que esteja dentro dos padrões será classificado
    //como inválido. Eu usei o seguinte site como ajuda. Abaixo deixarei o link:
    //https://receitasdecodigo.com.br/java/validar-email-em-java
    private String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";
    private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);


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
                if(nomeDigitado.equals("") || dataDigitada.equals("") || emailDigitado.equals("") || cpfDigitado.equals("")){

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

                    //Então irei fazer o if que irá invalidar o e-mail 
                    //caso ele não siga os padrões definidos.
                    if(!isValidEmail(emailDigitado)){
                        JOptionPane.showMessageDialog(null, "E-mail inválido");
                    }

                    if(!isCPF(cpfDigitado)){
                        JOptionPane.showMessageDialog(null, "CPF inválido");
                    }

                    //Esse "else if" é o responsável por validar todas as informações dadas 
                    //pelo usuário caso elas estejam de acordo com o esperado e não sejam 
                    //barradas por nenhum dos "if" anteriores. Isso é, se forem corretamente 
                    //digitados. A data ainda irá precisar de mais uma validação, a de idade.
                    //Será feita dentro desse mesmo "else if".
                    else if(nomeDigitado.matches(".*[\\p{L}]+\\s+[\\p{L}]+.*") && dataDigitada.matches("\\d{2}/\\d{2}/\\d{4}") && isValidEmail(emailDigitado) && isCPF(cpfDigitado)){

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
                        dados.setEmail(emailDigitado);
                        dados.setCPF(cpfDigitado);

                        janela.getJlRecebeNome().setText(dados.getNome());
                        janela.getJlRecebeData().setText(dados.getData());
                        janela.getJlRecebeEmail().setText(dados.getEmail());
                        //o CPF precisa do "imprimeCPF" pois assim irá aparecer formatado
                        //como um CPF comum.
                        janela.getJlRecebeCPF().setText(imprimeCPF(dados.getCPF()));
                        }
                        }

                    }
                }
            }
        });
    }

    //Aqui estou criando um metódo chamado "isValidEmail". 
    //Isso aqui irá servir para validar o e-mail, como o 
    //nome já indica.
    public boolean isValidEmail(String email) {
    String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
    }

    //Agore irei adicionar um metódo que será responsável pela validação do cpf.
    //O link que eu peguei esse código vai estar abaixo, ele explica melhor 
    //essa parte.
    //https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    public static boolean isCPF(String CPF) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere "0" no inteiro 0
        // (48 eh a posicao de "0" na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
}
