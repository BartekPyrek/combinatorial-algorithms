// Interfejs stworzony w celu ominięcia redundancji kodu oraz przekazywanie wartości do
// obserwatora, aby wyeliminować nieuzasadnione wykorzystywanie pamięci.
public interface DataListener<T> {

    void action(T[] data);
}
