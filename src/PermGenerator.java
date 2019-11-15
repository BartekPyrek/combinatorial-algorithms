import java.util.Arrays;

// Klasa odpowiadająca za generowanie permutacji, wykorzystywany jest wzorzec projektowy strategia,
// dzięki czemu uruchomienie generatora będzie proste i każdy algorytm będzie uruchomiony w osobnej klasie.
public class PermGenerator {
    private final Object[] initData;
    private final Observer observer;

    public <T> PermGenerator(T[] data, Observer observer) {
        this.initData = data;
        this.observer = observer;
    }

    public void generate(GeneratingPermutation permutation) {
        Object[] data = Arrays.copyOf(initData, initData.length);
        permutation.generate(data, observer);
    }
}
