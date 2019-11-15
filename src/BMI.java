public class BMI implements GeneratingPermutation {
    private Object[] data;
    private Observer observer;

    @Override
    public <T> void generate(T[] data, Observer<T> observer) {
        this.data = data;
        this.observer = observer;
        generate(data.length);
    }

    private void generate(int m) {
        if (m == 1) {
            observer.printPermutations(data);
        }
        else{
            for(int i = 1; i <= m; i++){
                generate(m-1);
                if (i < m){
                    swap(data, B(m,i)- 1, m-1);
                }
            }
        }
    }

    // metoda B(m,i)
    private int B(int m, int i) {
        if (m % 2 == 0 && m > 2) {
            if (i < m - 1) {
                return i;
            }
            return m - 2;
        }
        return m - 1;
    }

    // Zamiana miejscami elementów. W pseudokodzie operacja P[i]:=: P[j]
    private static void swap(Object[] data, int source, int destination) {
        Object temp = data[source];
        data[source] = data[destination];
        data[destination] = temp;
    }
}
