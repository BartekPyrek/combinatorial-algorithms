import java.util.Arrays;

// Klasa odpowiadająca za generowanie permutacji, wykorzystywany jest wzorzec projektowy strategia,
// dzięki czemu uruchomienie generatora będzie proste i każdy algorytm będzie uruchomiony w osobnej klasie.
public class PermutationGenerator {
    private final Object[] initData;
    private final DataListener dataListener;

    public <T> PermutationGenerator(T[] data, DataListener dataListener) {
        this.initData = data;
        this.dataListener = dataListener;
    }

    public void generate(GeneratingPermutation permutationStrategy) {
        Object[] data = Arrays.copyOf(initData, initData.length);
        permutationStrategy.generate(data, dataListener);
    }
}
