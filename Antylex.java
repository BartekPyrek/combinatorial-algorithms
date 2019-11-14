// Generowanie permutacji w porządku antyleksykograficznym
public class Antylex implements GeneratingPermutation {
    private Object[] data;
    private DataListener dataListener;

    @Override
    public <T> void generate(T[] data, DataListener<T> dataListener) {
        this.data = data;
        this.dataListener = dataListener;
        generate(data.length - 1);
    }

    private void generate(int m) {
        if (m == 0) {
            dataListener.action(data);
        } else {
            for (int i = 0; i <= m; i++) {
                generate(m - 1);

                if (i < m) {
                    CombinatoricsServices.swap(data, i, m);
                    CombinatoricsServices.reverse(data, m - 1);
                }
            }
        }
    }
}
