import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Janela extends JFrame{

    /*
     * Essa classe é responsável pela parte vísivel para o usuário. 
     * Aqui irei definir tudo que será feito na parte de frontend.
     * Usarei siglas e padrões de nomes para identificar cada parte da janela. 
     * As siglas jtf, jl e jb, siginificam, respectivamente, JTextField, JLabel e JButon.
     * Os padrões de nome serão "Digita", "Indica" e "Recebe". Os que possuem "Digita"
     * serão todos JTextField, são responsáveis por pegar tudo que o usuário digitar, 
     * por isso o nome. Aqueles que possuem "Indica" terão a funão de indicar, como o 
     * nome já diz, aonde o usuário irá digitar as informações. E aqueles que tiverem 
     * "Recebe" irão receber as informações dadas pelo usuário e mostrar elas quando 
     * os dados informados forem validados, para que o usuário saiba que foram 
     * aceitas.
     */
        
    //Esse é o botão que será clicado pelo usuário quando ele querer 
    //que as informações dadas por ele sejam validadas.
    private JButton jbBotaoValidar;
   
    //Será responsável por permitir o usuário digitar o nome dele.
    private JTextField jtfDigitaNome;
    //Será responsável por indicar aonde o usuário irá digitar o nome.
    private JLabel jlIndicaNome;
    //será responsável por receber e mostrar o nome digitado.
    private JLabel jlRecebeNome;
 
    //Será responsável por permitir o usuário digitar a da 
    //de nascimento dele.
    private JTextField jtfDigitaData;
    //Será responsável por indicar aonde o usuário irá digitar a 
    //data de nascimento dele;
    private JLabel jlIndicaData;
    //será responsável por receber e mostrar a data 
    //de nascimento digitado
    private JLabel jlRecebeData;

    //Aqui eu começo a criar minha janela, podendo mudar ela sem 
    //precisar ficar digitando o nome da minha janela .
    public Janela(){

        
        //Aqui irei definir o tamanho e as cordernadas da janela.
        //As coordenadas servem para iniciar ela aonde eu desejo.
        //Os dois primeiros números são as coordernadas, os dois 
        //últimos são o tamanho da janela, esse padrão será usado
        //em todos os campos de texto e até no botão.
        setBounds(800, 300, 400, 400);
        //Aqui irei definir o titulo da janela.
        setTitle("Validador");
        //Aqui irei definir que o aplicativo deve encerrar quando clicar no X 
        //que fecha a janela.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Aqui irei definir o layout, irei deixar "null" para que seja um layout
        //totalmente livre
        setLayout(null);

        //Aqui estou inicializando o botão. Está digitado "validar"
        //entre os parenteses para que o usuário saiba a função do botão.
        //Esse texto irá aparecer dentro do botão.
        jbBotaoValidar = new JButton("Validar");

        //Aqui estou inicializando o campo de texto que será digitado o nome.
        jtfDigitaNome = new JTextField(); 
        //Aqui estou inicializando o campo de texto que indica aonde digitar o nome.
        //Perceba que digitei "Nome" entre os paretenses, eu faço isso para 
        //que tenha esse texti escruti dentro do campo, algo diferente do 
        //campo de texto em que precisa digitar.
        jlIndicaNome = new JLabel("Nome");
        //Aqui estou inicializando o campo de texto que irá aparecer o nome 
        //que o usuário digitar após ser validado.
        jlRecebeNome = new JLabel("");

        //Aqui estou inicializando o campo de texto que será digitado a data.
        jtfDigitaData = new JTextField();
        //Aqui estou inicializando o campo de texto que indica aonde digitar a data.
        jlIndicaData = new JLabel("Data de Nascimento");
        //Aqui estou inicializando o campo de texto que irá aparecer a data 
        //que o usuário digitar após ser validado.
        jlRecebeData = new JLabel("");

        //Aqui estou definindo o tamanho e coordenadas do botão.
        jbBotaoValidar.setBounds(140, 300, 100, 40);

        //Aqui irei definir o tamanho e coordernadas dos campos de textos
        //dentro da janela. Isso inclui os para digitar ou apenas visualizar.
        jtfDigitaNome.setBounds(20, 50, 150, 30);
        jlIndicaNome.setBounds(20, 20, 150, 30);
        jlRecebeNome.setBounds(200, 50, 150, 30);

        jtfDigitaData.setBounds(20, 120, 150, 30);
        jlIndicaData.setBounds(20, 90, 150, 30);   
        jlRecebeData.setBounds(200, 120, 150, 30);     


        //aqui estou adicionando o botão na janela.
        add(jbBotaoValidar);

        //Aqui irei adicionar os campos de textos na janela.
        add(jtfDigitaNome);
        add(jlIndicaNome); 
        add(jlRecebeNome);

        add(jtfDigitaData);
        add(jlIndicaData);
        add(jlRecebeData);

        //Aqui irei definiri a visibilidade da janela, irei colocar true para que 
        //ela se torne viísvel.
        setVisible(true); 
    }

    //Aqui irei colocar os getters das partes que formam a janela. Só será usado os getters 
    //pois não precisarei alterar nada dessas partes.
    public JButton getJbBotaoValidar() {
        return jbBotaoValidar;
    }

    public JTextField getJtfDigitaNome() {
        return jtfDigitaNome;
    }

    public JLabel getJlRecebeNome() {
        return jlRecebeNome;
    }

    public JTextField getJtfDigitaData() {
        return jtfDigitaData;
    }

    public JLabel getJlRecebeData() {
        return jlRecebeData;
    }
}
