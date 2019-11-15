// Generowanie permutacji w porządku antyleksykograficznym
public class Antylex implements GeneratingPermutation {
    private Object[] data;
    private Observer observer;

    @Override
    public <T> void generate(T[] data, Observer<T> observer) {
        this.data = data;
        this.observer = observer;
        generate(data.length - 1);
    }

    private void generate(int m) {
        if (m == 0) {
            observer.printPermutations(data);
        } else {
            for (int i = 0; i <= m; i++) {
                generate(m - 1);
                if (i < m) {
                    swap(data, i, m);
                    reverse(data, m - 1);
                }
            }
        }
    }

    // Odwrócenie kolejności wszystkich elementów w przedziale 0 do m-1
    private static void reverse(Object[] data, int tableLength) {
        int i = 0;
        int j = tableLength;

        while (i < j) {
            swap(data, i, j);
            i++;
            j--;
        }
    }

    // Zamiana miejscami elementów. W pseudokodzie operacja P[i]:=: P[j]
    private static void swap(Object[] data, int source, int destination) {
        Object temp = data[source];
        data[source] = data[destination];
        data[destination] = temp;
    }
}
