public class Cliente{

    String cpf;
    Lista cntsCliente;

    public Cliente(String cdgPessoa){
        this.cpf = cdgPessoa;
        this.cntsCliente = new Lista();
    }

    public void carregarContas(Lista contas){
        Elemento aux = contas.prim.prox;
        while(aux != null){
            if(aux.conta.cpf.equals(cpf))
                cntsCliente.enfileirar(aux.conta);
            aux = aux.prox;
        }
    }
}