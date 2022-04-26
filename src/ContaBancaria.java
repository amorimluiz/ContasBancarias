public class ContaBancaria {

    int num;
    String cpf;
    double saldo;

    public ContaBancaria(int numero, String cdgPessoa, double sld){
        this.num = numero;
        this.cpf = cdgPessoa;
        this.saldo = sld;
    }

    public String dadosConta(){
        String linhaConta = "Conta Número: " + this.num + "\nCPF: " + this.cpf + "\nSaldo: " + this.saldo;
        return linhaConta;  //retorna uma string com os dados da conta
    }
}