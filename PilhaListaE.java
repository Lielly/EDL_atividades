// Implementação de um TADs Pilha utilizando a estrutura de dados concreta lista simplesmente encadeada.
// LIFO -> Last In First Out -> último a entrar é o primeiro a sair 

class EPilhaVazia extends RuntimeException {
    public EPilhaVazia(String m){
        super(m);
    }
}

interface pilha {
    public int size();
    public boolean isEmpty();
    public void push(Object o);
    public Object top() throws EPilhaVazia;
    public Object pop() throws EPilhaVazia;
}

public class PilhaListaE implements pilha {
    private static class No {
        private Object elemento;
        private No proximo;

        public Object getElemento() {return elemento;}
        public void setElemento(Object e){elemento = e;}
        public No getProximo() {return proximo;}
        public void setProximo(No p){this.proximo = p;}

        public No(Object elemento){
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private No t;
    private int size;

    public PilhaListaE(){
        this.t = null;
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean isEmpty(){
        return this.t == null;
    }

    @Override
    public void push(Object o){
        No novo = new No(o);
        novo.proximo = this.t;
        this.t = novo;
        this.size++;
    }

    @Override
    public Object top() throws EPilhaVazia {
        if(isEmpty()){
            throw new EPilhaVazia("A pilha está vazia");
        }
        return this.t.elemento;
    }

    @Override
    public Object pop() throws EPilhaVazia{
        if(isEmpty()){
            throw new EPilhaVazia("A pilha já está vazia");
        }
        Object pp = this.t.elemento;
        this.t = this.t.proximo;
        this.size--;

        return pp;
    }
}
