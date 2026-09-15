/*Projete uma classe que contenha duas pilhas, uma “vermelha” e outra “preta” e suas operações são adaptações “coloridas” das operações habituais sobre pilhas. Por exemplo, esta classe deve prover uma operação de push vermelha e uma operação de push preta. Usando um ÚNICO ARRAY cuja a capacidade é limitada por um tamanho N que é sempre maior do que os tamanhos somados das duas pilhas. A pilha “vermelha” pode começar no início do array e a pilha “preta” pode começar no final do array. 

OBS.: Sempre que o array (que contém as duas pilhas) estiver cheio utilizar a estratégia de duplicação do tamanho do array.
OBS.: Sempre que o array (que contém as duas pilhas) estiver com 1/3 de utilização, usar a estratégia de REDUÇÃO  do tamanho do array pela metade.*/
// LIFO -> Last In First Out -> último a entrar é o primeiro a sair 

class EPilhaVazia extends RuntimeException {
    public EPilhaVazia(String m){
        super(m);
    }
}

interface PilhaFlamengo{
    public int sizeR();
    public int sizeN();
    public int sizeT();

    public boolean isEmptyR();
    public boolean isEmptyN();

    public void tamanho();
    public void duplicar(int c);
    public void reduzir(int c);
    
    public void pushR(Object r);
    public void pushN(Object n);

    public Object topR() throws EPilhaVazia;
    public Object topN() throws EPilhaVazia;

    public Object popR() throws EPilhaVazia;
    public Object popN() throws EPilhaVazia;
}

public class PilhaRubroNegra implements PilhaFlamengo {
    private int capacidade;
    private Object[] fla;
    private int tR;
    private int tN;

    public PilhaRubroNegra(int N) {
        this.capacidade = N;
        fla = new Object[capacidade];
        tR = -1;
        tN = capacidade;
    }

    private int sizeR;
    private int sizeN;

    @Override
    public int sizeR(){
        return this.sizeR;
    }
    @Override
    public int sizeN(){
        return this.sizeN;
    }
    @Override
    public int sizeT(){
        return this.sizeR + this.sizeN;
    }

    @Override
    public boolean isEmptyR(){
        return this.tR == -1;
    }
    @Override
    public boolean isEmptyN(){
        return this.tN == capacidade;
    }

    @Override
    public void tamanho(){
        if (sizeT() == capacidade){
           duplicar(capacidade * 2);
        }
        else if (capacidade > 1 && sizeT() == capacidade/3){
            reduzir(capacidade / 2);
        } 
    }
    @Override
    public void duplicar(int c){
        Object mengo[] = new Object[c];
        for(int iR = 0; iR < sizeR; iR++){
            mengo[iR] = fla[iR];
        }
        for (int iN = c - 1; iN >= c - sizeN; iN--){
            mengo[iN] = fla[iN - (c - capacidade)];
        }
        tR = sizeR - 1;
        tN = c - sizeN;
        capacidade = c;
        fla = mengo;
    }
    @Override
    public void reduzir(int c){
        Object mengo[] = new Object[c];
        for(int iR = 0; iR < sizeR; iR++){
            mengo[iR] = fla[iR];
        }
        for (int iN = c - 1; iN >= c - sizeN; iN--){
            mengo[iN] = fla[iN - (c - capacidade)];
        }
        tR = sizeR - 1;
        tN = c - sizeN;
        capacidade = c;
        fla = mengo;
    }

    @Override
    public Object topR() throws EPilhaVazia{
        if (isEmptyR()){
            throw new EPilhaVazia("A Pilha Vermelha está vazia.");    
        }
        return fla[tR];
    }
    @Override
    public Object topN() throws EPilhaVazia{
        if (isEmptyN()){
            throw new EPilhaVazia("A Pilha Preta está vazia.");    
        }
        return fla[tN];
    }

    @Override
    public void pushR(Object r){//-> -> ->
        tamanho();
        fla[++tR] = r;
        sizeR++;
    }
    @Override
    public void pushN(Object n){//<- <- <-
        tamanho();
        fla[--tN] = n;
        sizeN++;
    }

    @Override
    public Object popR() throws EPilhaVazia{
        if (isEmptyR()){
            throw new EPilhaVazia("A Pilha Vermelha está vazia.");
        }
        Object r = fla[tR--];
        sizeR--;
        tamanho();
        return r;
    }
    @Override
    public Object popN() throws EPilhaVazia{
        if (isEmptyN()){
            throw new EPilhaVazia("A Pilha Preta está vazia.");
        }
        Object n = fla[tN++];
        sizeN--;
        tamanho();
        return n;
    }
}
