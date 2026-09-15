// Implementação os TADs Fila utilizando a estrutura de dados concreta lista simplesmente encadeada
// FIFO -> First In First Out -> primeiro a entrar é o primeiro a sair

class EFilaVazia extends RuntimeException {
    public EFilaVazia(String m){
        super(m);
    }
}

interface fila{
    public int size();
    public boolean isEmpty();
    public void enqueue(Object o);
    public Object dequeue() throws EFilaVazia;
    public Object first() throws EFilaVazia;
}

public class FilaListaE implements fila{
    public class No{
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

    private No i;
    private No f;
    private int size;

    public FilaListaE(){
        this.i = null;
        this.f = null;
        this.size = 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean isEmpty(){
        return this.i == null;
    }
    
    @Override
    public void enqueue(Object o){
        No novo = new No(o);
        if (this.f == null){
            this.i = novo;
            this.f = this.i;
            this.size++;
            return;
        }
        this.f.proximo = novo;
        this.f = novo;
        this.size++;
    }

    @Override
    public Object dequeue() throws EFilaVazia{
        if(isEmpty()){
            throw new EFilaVazia("A fila já está vazia");
        }
        Object dq = this.i.elemento;
        this.i = this.i.proximo;
        if (this.size == 1){
            this.f = this.i;
        }
        this.size--;
        return dq;
    }
    @Override
    public Object first() throws EFilaVazia {
        if(isEmpty()){
            throw new EFilaVazia("A fila está vazia");
        }
        return this.i.elemento;
    }
}
