// Interfejs stworzony w celu ominięcia redundancji kodu oraz przekazywanie wartości do
// obserwatora, aby wyeliminować nieuzasadnione wykorzystywanie pamięci.
public interface Observer<T> {

    void printPermutations(T[] data);
}
