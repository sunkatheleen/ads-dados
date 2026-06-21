public class BubbleSort {
    public static long operacoes = 0; // Contador de eficiência

    public static String[] ordenar(String[] oldArr) {
        operacoes = 0;
        String[] arr = oldArr.clone();
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                operacoes++;
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}