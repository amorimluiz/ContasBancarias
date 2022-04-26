import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class App {

    static Random aleatorio = new Random(System.nanoTime());
    static final String nomeArquivo = "contas-bancarias.txt";   //nome do arquivo de dados
    static Lista contas;  //cria uma lista de contas

    public static Lista carregarDados() throws FileNotFoundException{
        File arquivo = new File(nomeArquivo);   //cria o arquivo
        Scanner leitor = new Scanner(arquivo);  //le o arquivo
        Lista novasContas = new Lista();    //cria uma lista de contas

        while(leitor.hasNextLine()){ //enquanto tiver linhas no arquivo

            String linha = leitor.nextLine();   //le a proxima linha do arquivo
            String dados[] = linha.split(";");  //separa os dados no ";"

            int numero = Integer.parseInt(dados[0]); //primeiro dado: numero da conta
            String cpf = (dados[1]);    //segundo dado: cpf
            double saldo = Double.parseDouble(dados[2]);    //terceiro dado: saldo inicial da conta

            ContaBancaria novaConta = new ContaBancaria(numero, cpf, saldo); //cria um novo objeto "ContaBancaria"

            novasContas.enfileirar(novaConta); //joga a conta criada em uma lista de contas.
        }
        leitor.close();
        return novasContas;
    }

    public static void salvarDados(Lista dados) throws IOException{
        FileWriter escritor = new FileWriter(nomeArquivo, false);   //cria o escritor para sobrescrever o arquivo
        Elemento aux = dados.prim.prox; //aux e o primeiro apos o sentinela
        while(aux != null){ //enquanto aux nao for null
            escritor.append(aux.conta.num + ";" + aux.conta.cpf + ";" + aux.conta.saldo + "\n");    //sobrescreve o arquivo com os dados
            aux = aux.prox; //caminha com o aux
        }
        escritor.close();
    }

    public static int mostrarMenu(Scanner teclado){
        System.out.println("CONTAS BANCARIAS XUBANK");
        System.out.println("=======================");
        System.out.println("1 - Consultar conta");
        System.out.println("2 - Adicionar conta");
        System.out.println("3 - Exibir contas ordenadas");
        System.out.println("0 - Sair");

        int opcao = Integer.parseInt(teclado.nextLine());
        return opcao;
    }

    public static void limparTela(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void pausar(Scanner teclado){
        System.out.print("Tecle ENTER para continuar. "); 
        teclado.nextLine();
    }

    public static String lerTeclado(String mensagem, Scanner teclado){
        System.out.print(mensagem + " ");
        return teclado.nextLine();
    }

    public static void main(String[] args) throws Exception{
        Scanner teclado = new Scanner(System.in);
        contas = carregarDados();
        int opcao;

        do{
            limparTela();
            opcao = mostrarMenu(teclado);

            switch(opcao){
                case 1:
                    limparTela();
                    ContaBancaria contaDesejada;
                    int numero = Integer.parseInt(lerTeclado("Número da conta: ", teclado));    //recebe o numero da conta que deseja buscar
                    contaDesejada = contas.buscar(numero);   //busca a conta desejada e retorna um objeto

                    limparTela();
                    if(contaDesejada!=null) //se a conta existir mostra os dados da conta
                        System.out.println(contaDesejada.dadosConta());
                    else    //se nao: conta nao cadastrada
                        System.out.println("Conta não cadastrada");
                    pausar(teclado);
                break;

                case 2: 
                    limparTela();
                    int novoNumero =  aleatorio.nextInt(1000000);
                    String cpf = lerTeclado("CPF: ", teclado);    //recebe o cpf da nova conta
                    double sld = 0;
                    ContaBancaria novaConta = new ContaBancaria(novoNumero, cpf, sld);    //cria uma nova conta, com o cpf recebido e saldo inicial 0
                    contas.enfileirar(novaConta);   //joga a nova conta em uma lista de contas
                    System.out.println("Conta cadastrada.");
                    pausar(teclado);
                break;

                default:
                    limparTela();
                break;
            }
        }while(opcao!=0);

        salvarDados(contas);    //salva os dados no arquivo ao encerrar o programa
        teclado.close();
    }
}