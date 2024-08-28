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

        // Multiplica duas strings de bits x e y
    // e retorna o resultado como um inteiro "long".
    public static String multiply(String binary1, String binary2) {
        // Reverter as strings binárias para facilitar a multiplicação
        String b1 = new StringBuilder(binary1).reverse().toString();
        String b2 = new StringBuilder(binary2).reverse().toString();
    
        // Criar um array para armazenar o resultado
        int[] m = new int[binary1.length() + binary2.length()];
    
        // Multiplicação bit a bit
        for (int i = 0; i < binary1.length(); i++) {
            for (int j = 0; j < binary2.length(); j++) {
                m[i + j] += (b1.charAt(i) - '0') * (b2.charAt(j) - '0');
            }
        }
    
        // Corrigir o carry (transporte)
        for (int i = 0; i < m.length - 1; i++) {
            m[i + 1] += m[i] / 2; // Adiciona o carry à próxima posição
            m[i] %= 2;           // Mantém apenas o valor binário (0 ou 1) na posição atual
        }
    
        // Converter o array para uma string binária
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        for (int i = m.length - 1; i >= 0; i--) {
            if (m[i] != 0 || !leadingZero) {
                leadingZero = false;
                result.append(m[i]);
            }
        }
    
        // Retornar o resultado ou "0" se estiver vazio
        return result.length() > 0 ? result.toString() : "0";
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
