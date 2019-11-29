import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zad5<T> {

    private Set<Set<Set<T>>> parts;
    private Set<Set<T>> pow;
    private Set<T> base;

    public static void main(String[] args) {
        Set<Integer> baseSet = new HashSet<Integer>();
        baseSet.add(1);
        baseSet.add(2);
        baseSet.add(3);
        baseSet.add(4);
        zad5<Integer> partSetCreator = new zad5<Integer>(baseSet);
        Set<Set<Set<Integer>>> partitionSets = partSetCreator.findAllPartitions();

        System.out.println("Wyniki:");
        partitionSets.stream().forEach(result -> System.out.println(result));
        System.out.println("Zestaw początkowy: " + baseSet);
        System.out.println("Ilość wyników: " + partitionSets.size());
    }

    public zad5(Set<T> base) {
        this.base = base;
        this.pow = powerSet(base);
        // Usuwa pusty set gdy nie jest to jedyne wejście do seta
        if (pow.size() > 1) {
            pow.remove(new HashSet<T>());
        }
        this.parts = new HashSet<Set<Set<T>>>();
    }

    // Znajduje zestawy części dla każdego wpisu oraz zwraca podziały, które zostały
    // znalezione
    public Set<Set<Set<T>>> findAllPartitions() {
        for (Set<T> set : pow) {
            Set<Set<T>> current = new HashSet<Set<T>>();
            current.add(set);
            findPartSets(current);
        }
        return parts;
    }

    // Znajduje wszystkie zestawy podziałów dla danych wejściowych i podaje je do
    // zmiennej globalnej parts
    private void findPartSets(Set<Set<T>> current) {
        int maxLen = base.size() - deepSize(current);
        if (maxLen == 0) {
            parts.add(current);
        } else {
            for (int i = 1; i <= maxLen; i++) {
                for (Set<T> set : pow) {
                    if (set.size() == i) {
                        if (!anyInDeepSet(set, current)) {
                            Set<Set<T>> next = new HashSet<Set<T>>();
                            next.addAll(current);
                            next.add(set);
                            findPartSets(next);
                        }
                    }
                }
            }
        }
    }

    // Tworzy power set z podstawowego seta.
    private Set<Set<T>> powerSet(Set<T> base) {
        Set<Set<T>> pset = new HashSet<Set<T>>();
        if (base.isEmpty()) {
            pset.add(new HashSet<T>());
            return pset;
        }
        List<T> list = new ArrayList<T>(base);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            pset.add(newSet);
            pset.add(set);
        }
        return pset;
    }

    // Zsumowany rozmiar wszystkich podzbiorów
    private int deepSize(Set<Set<T>> set) {
        int deepSize = 0;
        for (Set<T> s : set) {
            deepSize += s.size();
        }
        return deepSize;
    }

    // Sprawdza, czy którykolwiek z zestawów należy do któregokolwiek z podzbiorów
    private boolean anyInDeepSet(Set<T> set, Set<Set<T>> current) {
        boolean containing = false;

        for (Set<T> s : current) {
            for (T item : set) {
                containing |= s.contains(item);
            }
        }
        return containing;
    }
}