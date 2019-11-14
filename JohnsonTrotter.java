// Generator permutacji wykorzystując algorytm Johnsona Trottera oraz pseudokod z książki Lipskiego
// Kombinatoryka dla Programistów (algorytm 1.12)
public class JohnsonTrotter implements GeneratingPermutation {

    private Object[] data;
    private DataListener dataListener;

    @Override
    public <T> void generate(T[] data, DataListener<T> dataListener) {
        this.data = data;
        this.dataListener = dataListener;
        generate(data.length - 1);
    }

    private void generate(int m) {
        int[] c = new int[m + 1];
        boolean[] pr = new boolean[m + 1];

        for (int i = 0; i < m; i++) {
            c[i] = 0;
            pr[i] = true;
        }
        c[m] = 0;
        dataListener.action(data);
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
            int k;
            if (pr[i]) {
                k = c[i] + x;
            } else {
                k = m - i - 1 - c[i] + x;
            }
            CombinatoricsServices.swap(data, k, k + 1);
            dataListener.action(data);
            c[i]++;
        }
    }
}