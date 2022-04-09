import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    static final String nomeArquivo = "contas-bancarias.txt";   //nome do arquivo de dados
    static Lista contas;  //cria um vetor de objetos
    static int qtde;

    static Lista carregarDados() throws FileNotFoundException{
        File arquivo = new File(nomeArquivo);   //cria o arquivo
        Scanner leitor = new Scanner(arquivo);  //le o arquivo

        int quant = Integer.parseInt(leitor.nextLine());   //primeira linha do arquivo = quantidade de contas

        Lista novasContas = new Lista(quant * 2);

        for(int i = 0; i < quant; i++){ //para cada conta

            String linha = leitor.nextLine();   //le a proxima linha do arquivo
            String dados[] = linha.split(";");  //separa os dados no ";"

            int numero = Integer.parseInt(dados[0]); //primeiro dado: numero da conta
            long cpf = Long.parseLong(dados[1]);    //segundo dado: cpf
            double saldo = Double.parseDouble(dados[2]);    //terceiro dado: saldo inicial da conta

            ContaBancaria novaConta = new ContaBancaria(numero, cpf, saldo); //cria um novo objeto "ContaBancaria"

            novasContas.inserir(novaConta); //joga o objeto "ContaBancaria" criado em um vetor de contas.
        }
        leitor.close();
        qtde = quant;
        return novasContas;
    }

    static void salvarDados(Lista contas) throws IOException{
        File arquivo = new File(nomeArquivo);   //cria o arquivo
        FileWriter escritor = new FileWriter(arquivo, false); //escritor para sobrescrever arquivo

        escritor.append(qtde + "\n");    //grava na primeira linha do arquivo a quantidade de contas

        for(int i = 0; i < qtde; i++)
            escritor.append(contas.contas[i].numero + ";" + contas.contas[i].cpf + ";" + contas.contas[i].saldo + "\n"); //grava no arquivo o numero da conta, o cpf e o saldo respectivamente. Separados por ;

        escritor.close();
    }

    static void ordenarContas(Lista contas){
        int posReferencia, posMenor, posAtual;

        for(posReferencia = 0; posReferencia < qtde; posReferencia++){   //para cada posicao
            posMenor = posReferencia;   //posicao do menor comeca na posicao de referencia
                for(posAtual = posReferencia+1; posAtual < qtde - 1; posAtual++){//para cada numero em uma posicao de referencia
                    if(contas.contas[posAtual].numero < contas.contas[posMenor].numero)    //verifica se o numero da conta atual e menor
                        posMenor = posAtual; //se sim, posicao menor = posicao atual
                }
           trocar(contas, posReferencia, posMenor);
        }
    }

    public static void trocar(Lista contas, int pos1, int pos2){
        ContaBancaria aux = contas.contas[pos1];
        contas.contas[pos1] = contas.contas[pos2];
        contas.contas[pos2] = aux;
    }

    static int mostrarMenu(Scanner teclado){
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

    static String lerTeclado(String mensagem, Scanner teclado){
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
                    contaDesejada = contas.retirar(numero);   //busca a conta desejada e retorna um objeto

                    limparTela();
                    if(contaDesejada!=null) //se a conta existir mostra os dados da conta
                        System.out.println(contaDesejada.dadosConta());
                    else    //se nao: conta nao cadastrada
                        System.out.println("Conta não cadastrada");
                    contas.inserir(contaDesejada);
                    pausar(teclado);
                break;

                case 2: 
                    limparTela();
                    int novoNumero = qtde + 1;
                    long cpf = Long.parseLong(lerTeclado("CPF: ", teclado));    //recebe o cpf da nova conta
                    ContaBancaria novaConta = new ContaBancaria(novoNumero, cpf, 0);    //cria uma nova conta, com o cpf recebido e saldo inicial 0
                    contas.contas[qtde] = novaConta;    //joga a nova conta em um vetor de contas                     
                    qtde++;
                    System.out.println("Conta cadastrada.");
                    pausar(teclado);
                break;

                case 3:
                    limparTela();
                    ordenarContas(contas);  //ordena as contas em ordem crescente
                    for(int i = 0; i < qtde; i++)    //para cada conta
                        System.out.println("Conta número: " + contas.contas[i].numero + " - CPF: " + contas.contas[i].cpf + " - Saldo: " + contas.contas[i].saldo);    //mostra as contas ordenadas
                    pausar(teclado);
                break;

                default:
                    limparTela();
                break;
            }
        }while(opcao!=0);
        teclado.close();
        salvarDados(contas);
    }
}