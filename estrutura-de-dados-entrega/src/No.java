public class No {
    Livro livro;
    No esquerda;
    No direita;

    public No(Livro livro) {
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }
}