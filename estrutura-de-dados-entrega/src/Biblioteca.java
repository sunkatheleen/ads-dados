import java.util.*;

public class Biblioteca {
    private LinkedList<Livro> acervo = new LinkedList<>();
    private Queue<String> listaEspera = new LinkedList<>();
    private Stack<Livro> historicoNavegacao = new Stack<>();
    private Map<Livro, Set<Livro>> grafoRecomendacoes = new HashMap<>();
    private No raizArvore;

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
        grafoRecomendacoes.putIfAbsent(livro, new HashSet<>());
        raizArvore = inserirNaArvore(raizArvore, livro);
    }

    private No inserirNaArvore(No atual, Livro livro) {
        if (atual == null) return new No(livro);
        if (livro.getTitulo().compareToIgnoreCase(atual.livro.getTitulo()) < 0) {
            atual.esquerda = inserirNaArvore(atual.esquerda, livro);
        } else if (livro.getTitulo().compareToIgnoreCase(atual.livro.getTitulo()) > 0) {
            atual.direita = inserirNaArvore(atual.direita, livro);
        }
        return atual;
    }

    public void exibirArvoreOrdenada(No node) {
        if (node != null) {
            exibirArvoreOrdenada(node.esquerda);
            System.out.println(" - " + node.livro.getTitulo());
            exibirArvoreOrdenada(node.direita);
        }
    }

    public No getRaizArvore() { return raizArvore; }

    // --- REQUISITO FORMATIVA 7: Busca em Profundidade (DFS) ---
    public void buscarLivroDFS(String tituloBuscado) {
        System.out.println("\n[DFS - Profundidade] A iniciar rastreio pelo livro: '" + tituloBuscado + "'");
        boolean encontrado = dfsRecursivo(raizArvore, tituloBuscado);
        if (!encontrado) System.out.println(" [ERRO 404] Livro não encontrado na árvore.");
    }

    private boolean dfsRecursivo(No node, String tituloBuscado) {
        if (node == null) return false;
        
        System.out.println(" -> A inspecionar nó: " + node.livro.getTitulo());
        
        if (node.livro.getTitulo().equalsIgnoreCase(tituloBuscado)) {
            System.out.println(" [ALVO LOCALIZADO VIA DFS]");
            return true;
        }
        
        if (dfsRecursivo(node.esquerda, tituloBuscado)) return true;
        return dfsRecursivo(node.direita, tituloBuscado);
    }

    // --- REQUISITO FORMATIVA 7: Busca em Largura (BFS) ---
    public void buscarLivroBFS(String tituloBuscado) {
        System.out.println("\n[BFS - Largura] A iniciar rastreio pelo livro: '" + tituloBuscado + "'");
        if (raizArvore == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raizArvore);

        while (!fila.isEmpty()) {
            No node = fila.poll();
            System.out.println(" -> A inspecionar nó: " + node.livro.getTitulo());
            
            if (node.livro.getTitulo().equalsIgnoreCase(tituloBuscado)) {
                System.out.println(" [ALVO LOCALIZADO VIA BFS]");
                return;
            }
            
            if (node.esquerda != null) fila.add(node.esquerda);
            if (node.direita != null) fila.add(node.direita);
        }
        System.out.println(" [ERRO 404] Livro não encontrado na árvore.");
    }

    public void conectarLivros(Livro l1, Livro l2) {
        if (grafoRecomendacoes.containsKey(l1) && grafoRecomendacoes.containsKey(l2)) {
            grafoRecomendacoes.get(l1).add(l2);
            grafoRecomendacoes.get(l2).add(l1);
        }
    }

    public Map<Livro, Integer> djikstraSimples(Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();
        distancias.put(origem, 0);
        fila.add(origem);
        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);
            for (Livro vizinho : grafoRecomendacoes.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancias;
    }

    public void exibirRecomendacoesPorDistancia(Livro origem) {
        System.out.println("\n[MOTOR DE RECOMENDAÇÃO] Distâncias a partir de: " + origem.getTitulo());
        Map<Livro, Integer> distancias = djikstraSimples(origem);
        for (Map.Entry<Livro, Integer> entrada : distancias.entrySet()) {
            if (!entrada.getKey().equals(origem)) {
                System.out.println(" -> Distância " + entrada.getValue() + ": " + entrada.getKey().getTitulo());
            }
        }
    }
}