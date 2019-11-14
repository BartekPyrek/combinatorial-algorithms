// Klasa zawierająca pomocnicze metody, która pomogą w procesie generowania permutacji
public class CombinatoricsServices {


    // Odwrócenie kolejności wszystkich elementów w przedziale 0 do m-1
    public static void reverse(Object[] data, int tableLength) {
        int i = 0;
        int j = tableLength;

        while (i < j) {
            swap(data, i, j);
            i++;
            j--;
        }
    }

    // Zamiana miejscami elementów. W pseudokodzie operacja P[i]:=: P[j]
    public static void swap(Object[] data, int sourceIndex, int destinationIndex) {
        Object temp = data[sourceIndex];
        data[sourceIndex] = data[destinationIndex];
        data[destinationIndex] = temp;
    }
}
