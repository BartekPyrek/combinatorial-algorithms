import java.util.Scanner;

public class zad4 {

    static int silnia(int n) {
        return (n == 1 || n == 0) ? 1 : silnia(n - 1) * n;
    }

    static int B(int m, int i) {
        if (m % 2 == 0 && m > 2) {
            if (i < m - 1) {
                return i;
            }
            return m - 2;
        }
        return m - 1;
    }

    static void perm(int m, int[] p) {
        int i;
        int count = 0;
        int[] tab = new int[m + 1];
        int temp;
        for (i = 1; i <= m; i++) {
            tab[i] = 1;
        }
        int mi = 1;
        while (count < silnia(m)) {
            if (tab[mi] == mi) {
                if (tab[mi] == 1 && mi == 1) {
                    count++;
                    System.out.print(count + ": ");
                    for (i = 1; i <= m; i++) {
                        System.out.print(p[i]);
                    }
                    System.out.println();
                }
                for (i = 1; i <= mi; i++) {
                    tab[i] = 1;
                }
                mi++;
            }
            if (tab[mi] < mi) {
                i = tab[mi];
                temp = p[B(mi, i)];
                p[B(mi, i)] = p[mi];
                p[mi] = temp;
                tab[mi]++;
                mi = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj liczbę całkowitą: ");
        int a = in.nextInt();
        if (a == 1) {
            System.out.println("1");
        } else {
            int[] p = new int[a + 1];
            for (int i = 1; i <= a; i++) {
                p[i] = i;
            }
            perm(a, p);
            in.close();
        }
    }
}