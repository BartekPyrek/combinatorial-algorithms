import java.util.Arrays;

public class Main {
    static Integer[] data = {1, 2, 3, 4};

    public static void main(String[] args) {
        System.out.println("Antylex algorytm:");
        Antylex(data.length - 1);

        System.out.println("\n");
        System.out.println("BMI algorytm: ");
        BMI(data.length);

        System.out.println("\n");
        System.out.println("Johnson Trotter algorytm: ");
        JohnsonTrotter(data.length - 1);
    }

    //zadanie 1
    static void Antylex(int m) {
        if (m == 0) {
            System.out.println(Arrays.toString(data));
        } else {
            for (int i = 0; i <= m; i++) {
                Antylex(m - 1);
                if (i < m) {
                    swap(data, i, m);
                    reverse(data, m - 1);
                }
            }
        }
    }

    //zadanie 2
    static void BMI(int m) {
        if (m == 1) {
            System.out.println(Arrays.toString(data));
        } else {
            for (int i = 1; i <= m; i++) {
                BMI(m - 1);
                if (i < m) {
                    swap(data, B(m, i) - 1, m - 1);
                }
            }
        }
    }

    // metoda B(m,i)
    static int B(int m, int i) {
        if (m % 2 == 0 && m > 2) {
            if (i < m - 1) {
                return i;
            }
            return m - 2;
        }
        return m - 1;
    }

    //zadanie 3
    static void JohnsonTrotter(int m) {
        int[] c = new int[m + 1];
        boolean[] pr = new boolean[m + 1];

        for (int i = 0; i < m; i++) {
            c[i] = 0;
            pr[i] = true;
        }
        c[m] = 0;
        System.out.println(Arrays.toString(data));
        while (true) {
            int i = 0;
            int x = 0;
            while (i < m && c[i] == m - i) {
                pr[i] = !pr[i];
                c[i] = 0;
                if (pr[i]) {
                    x++;
                }
                i++;
            }
            if (i >= m) {
                break;
            }
            //transpozycja
            int k;
            if (pr[i]) {
                k = c[i] + x;
            } else {
                k = m - i - 1 - c[i] + x;
            }
            swap(data, k, k + 1);
            System.out.println(Arrays.toString(data));
            c[i]++;
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
