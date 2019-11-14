// Interfejs wykorzystywany do rozpoczęcia generowania permutacji
public interface GeneratingPermutation {
    <T> void generate(T[] data, DataListener<T> dataListener);
}
