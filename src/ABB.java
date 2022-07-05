public class ABB {
    IComparavel raiz;
    ABB esquerda, direita;

    public ABB(){
        raiz = null;
        direita = esquerda = null;
    }

    public ABB(IComparavel primeiro){
        raiz = primeiro;
        direita = esquerda = null;
    }

    public void inserir(IComparavel novo){
        if(this.raiz == null)
            this.raiz = novo;
        else
            this.inserir(novo, this);
    }

    public ABB inserir(IComparavel novo, ABB subarvore){
        if(subarvore == null){
             ABB novoNodo = new ABB(novo);
             return novoNodo;
        }
        else{
        if(novo.menorQue(subarvore.raiz))
            subarvore.esquerda = inserir(novo, subarvore.esquerda);
        else
            subarvore.direita = inserir(novo, subarvore.direita);
        }
        return subarvore;
    }

    public IComparavel buscar(IComparavel desejado){
        ABB subarvore = this;
        while(subarvore != null){
            if(subarvore.raiz.equals(desejado))
                return subarvore.raiz;
            else if(desejado.menorQue(subarvore.raiz))
                subarvore = subarvore.esquerda;
            else
                subarvore = subarvore.direita;
        }
        return null;
    }

    public String emOrdem(ABB subarvore){
        StringBuilder sb = new StringBuilder();
        if(subarvore!=null){
            sb.append(emOrdem(subarvore.esquerda));
            sb.append(subarvore.raiz.toString() + "\n");
            sb.append(emOrdem(subarvore.direita));
            return sb.toString();
        }
        else return "";
    }

    public String preOrdem(ABB subarvore){
        StringBuilder sb = new StringBuilder();
        if(subarvore!=null){
            sb.append(subarvore.raiz.toString() + "\n");
            sb.append(emOrdem(subarvore.esquerda));
            sb.append(emOrdem(subarvore.direita));
            return sb.toString();
        }
        else return "";
    }
}
