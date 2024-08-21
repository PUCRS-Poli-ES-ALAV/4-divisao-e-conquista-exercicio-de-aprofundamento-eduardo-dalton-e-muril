package br.pucrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        long[] array = geraVetor(16, 16);
        List<Long> list = new ArrayList<>();
        for (long num : array) {
            list.add(num);
        }

        long result = maxVal1(array, 32);
        System.out.println(result);

        // System.out.println(list);

        // List<Integer> sortedList = mergesort(list);

        // System.out.println("Sorted list: " + sortedList);
    }
    

    private static long[] geraVetor(int nroPares, int nroImpares) {
        long[] res = null;
        int contPar = 0, contImpar = 0, novoNum;
        Random rnd = new Random();

        if (nroPares >= 0 && nroImpares >= 0 && (nroPares + nroImpares > 0)) {
            res = new long[nroPares + nroImpares];

            while (contPar < nroPares || contImpar < nroImpares) {
                novoNum = rnd.nextInt(Integer.MAX_VALUE) + 1; // Generate numbers between 1 and 100

                if (novoNum % 2 == 0 && contPar < nroPares) {
                    res[contPar + contImpar] = novoNum;
                    contPar++;
                } else if (novoNum % 2 == 1 && contImpar < nroImpares) {
                    res[contPar + contImpar] = novoNum;
                    contImpar++;
                }
            }
        }

        return res;
    }

    public static List<Integer> mergesort(List<Integer> lista) {
        if (lista == null)
            return null;
        if (lista.size() <= 1)
            return lista;

        int mid = lista.size() / 2;
        List<Integer> left = mergesort(lista.subList(0, mid));
        List<Integer> right = mergesort(lista.subList(mid, lista.size()));

        return merge(left, right);
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }

    // Verificar depois
    public static long maxVal1(long A[], int n) {
        long max = A[0];
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];

            }
            count++;
        }
        System.out.println(count);
        return max;
    }
}
