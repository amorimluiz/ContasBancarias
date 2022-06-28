public class ContaBancaria implements IComparavel{

    public int num;
    public String cpf;
    public double saldoInicial, saldoAtual;
    public Lista operacoes;

    public ContaBancaria(int numero, String cpf, double saldo){
        this.num = numero;
        this.cpf = cpf;
        this.saldoAtual = this.saldoInicial = saldo;
        this.operacoes = new Lista();
    }

    @Override
    public boolean equals(Object outro){
        ContaBancaria outraConta = (ContaBancaria) outro;
        return this.num == outraConta.num;
    }

    @Override
    public int hashCode(){
        return this.num;
    }

    @Override
    public String toString(){
        return "Conta Número: " + this.num + " | CPF: " + this.cpf + " | Saldo: " + this.saldoAtual;
    }

    /**
     * imprime na tela o extrato de uma conta bancaria
     * @return o extrato
     */
    public String imprimirExtratoDaConta(){
        String extrato = ("EXTRATO BANCARIO\n===================================================================\n");
        extrato += operacoes.toString();
        extrato += "Saldo total da conta: " + this.saldoAtual + "\n";
        return extrato;
    }

    @Override
    public boolean maiorQue(IComparavel outro) {
        ContaBancaria outraConta = (ContaBancaria)outro;
        return this.num > outraConta.num;
    }

    @Override
    public boolean menorQue(IComparavel outro) {
        ContaBancaria outraConta = (ContaBancaria)outro;
        return this.num < outraConta.num;
    }

    /**
     * compara o saldo de duas contas
     * @param outraConta conta que sera comparada com esta
     * @return true se o saldo desta conta for maior
     */
    public boolean maiorSaldo(ContaBancaria outraConta){
        return this.saldoAtual > outraConta.saldoAtual;
    }

    /**
     * realiza uma operacao de saque ou deposito e insere na lista desta conta
     * @param op uma operacao que sera realizada
     */
    public void realizarOperacao(Operacao op){
        double valorOperacao = op.checarOperacao();
        this.saldoAtual += valorOperacao;
        this.operacoes.inserir(op);
    }
}