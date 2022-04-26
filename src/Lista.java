public class Lista {
    
    public Elemento prim, ult;

    public Lista(){
        prim = new Elemento(null);
        ult = prim;
    }

    public void enfileirar(ContaBancaria nova){
        Elemento novo = new Elemento(nova);
        ult.prox = novo;
        ult = novo;
    }

    public ContaBancaria desenfileirar(){
        Elemento aux = prim.prox;
        prim.prox = aux.prox;
        aux.prox = null;
        if(aux == ult)
            ult = prim;
        return aux.conta;
    }

    public ContaBancaria buscar(int num){
        Elemento aux = prim.prox;
        while(aux != null){
            if(num == aux.conta.num)
                return aux.conta;
            aux = aux.prox;
        }
        return null;
    }

    public String imprimir(){
        StringBuilder sb = new StringBuilder();
        Elemento aux = prim.prox;
        while(aux != null){
            sb.append(aux.conta.dadosConta() + "\n");
            aux = aux.prox;
        }
        return sb.toString();
    }

    public boolean vazia(){
        return prim == ult;
    }
}