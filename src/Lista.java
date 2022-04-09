public class Lista {
    
    int tam;
    int quant;
    ContaBancaria[] contas;

    public Lista(int tamanho){
        this.tam = tamanho;
        this.quant = 0;
        this.contas = new ContaBancaria [tamanho];
    }

    public void inserir(ContaBancaria nova){
        if(quant < tam){
            contas[quant] = nova;
            quant++;
        }
    }

    public ContaBancaria retirar(int numero){
        if(quant > 0)
            for(int i = 0; i < quant; i++)
                if(numero == contas[i].numero){
                    ContaBancaria aux = contas[i];
                    for(int j = i + 1; j < quant; j++)
                        contas[j - 1] = contas[j];
                    quant--;
                    return aux;
                }
        return null;
    }
}
