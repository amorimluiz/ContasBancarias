public class ContaBancaria {

    int numero;
    long cpf;
    double saldo;

    ContaBancaria(int numero, long cpf, double saldo){
        this.numero = numero;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    String dadosConta(){
        String linhaConta = "Conta Número: " + this.numero + "\nCPF: " + this.cpf + "\nSaldo: " + this.saldo;
        return linhaConta;  //retorna uma string com os dados da conta
    }
}