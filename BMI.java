public class BMI implements GeneratingPermutation {
    private Object[] data;
    private DataListener dataListener;

    @Override
    public <T> void generate(T[] data, DataListener<T> dataListener) {
        this.data = data;
        this.dataListener = dataListener;
        generate(data.length);
    }

    private void generate(int m) {
        if (m == 1) {
            dataListener.action(data);
        }
        else{
            for(int i = 1; i <= m; i++){
                generate(m-1);
                if (i < m){
                    CombinatoricsServices.swap(data, getIndexSwap(m,i)- 1, m-1);
                }
            }
        }
    }

    // metoda B(m,i)
    private int getIndexSwap(int m, int i) {
        if (m % 2 == 0 && m > 2) {
            if (i < m - 1) {
                return i;
            }
            return m - 2;
        }
        return m - 1;
    }
}
