package br.pucrs;

public class Multiplication {

    private static int iterationCount = 0;

    // Função de multiplicação
    public static long multiply(long x, long y, int n) {
        iterationCount++;

        if (n == 1) {
            return x * y;
        } else {
            int m = (int) Math.ceil(n / 2.0);

            // Dividindo x e y em duas partes
            long a = x >> m; // x dividido por 2^m
            long b = x - (a << m); // x mod 2^m
            long c = y >> m; // y dividido por 2^m
            long d = y - (c << m); // y mod 2^m

            // Chamando recursivamente para as partes
            long e = multiply(a, c, m);
            long f = multiply(b, d, m);
            long g = multiply(b, c, m);
            long h = multiply(a, d, m);

            return (e << (2 * m)) + ((g + h) << m) + f;
        }
    }

    public static void main(String[] args) {
        // Testes com 4 bits
        testMultiply(15, 3, 4);

        // Testes com 16 bits
        testMultiply(65535, 12345, 16);

        // Testes com 64 bits
        testMultiply(9223372036854775807L, 1234567890123456789L, 64);
    }

    // Função para testar a multiplicação e contabilizar iterações e tempo
    public static void testMultiply(long x, long y, int n) {
        iterationCount = 0;
        long startTime = System.nanoTime();
        long result = multiply(x, y, n);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000; // Tempo em microssegundos

        System.out.println("Resultado: " + result);
        System.out.println("Número de iterações: " + iterationCount);
        System.out.println("Tempo gasto: " + duration + " microssegundos\n");
    }
}
