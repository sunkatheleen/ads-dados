import java.util.Random;

public class GeradorDeNomesDeLivros {
    private static final int TAMANHO_MAXIMO_DO_NOME = 20;
    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

    public static String[] gerarNomes(int numeroDeLivros) {
        Random random = new Random();
        String[] nomesDeLivros = new String[numeroDeLivros];
        for (int i = 0; i < numeroDeLivros; i++) {
            nomesDeLivros[i] = gerarNomeAleatorio(random);
        }
        return nomesDeLivros;
    }

    private static String gerarNomeAleatorio(Random random) {
        int tamanhoDoNome = random.nextInt(TAMANHO_MAXIMO_DO_NOME) + 1;
        StringBuilder sb = new StringBuilder(tamanhoDoNome);
        for (int i = 0; i < tamanhoDoNome; i++) {
            sb.append(ALFABETO.charAt(random.nextInt(ALFABETO.length())));
        }
        return sb.toString();
    }
}