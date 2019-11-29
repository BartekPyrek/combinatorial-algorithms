import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zad5<T> {

    private Set<Set<Set<T>>> czesci;
    private Set<Set<T>> pow;
    private Set<T> baza;

    public static void main(String[] args) {
        Set<Integer> zbior = new HashSet<Integer>();
        zbior.add(1);
        zbior.add(2);
        zbior.add(3);
        zbior.add(4);
        zad5<Integer> kreatorpodzialow = new zad5<Integer>(zbior);
        Set<Set<Set<Integer>>> podzialy = kreatorpodzialow.znajdzWszystkiePodzialy();

        System.out.println("Wyniki:");
        podzialy.stream().forEach(result -> System.out.println(result));
        System.out.println("Zestaw początkowy: " + zbior);
        System.out.println("Ilość wyników: " + podzialy.size());
    }

    public zad5(Set<T> baza) {
        this.baza = baza;
        this.pow = powerSet(baza);
        // Usuwa pusty set gdy nie jest to jedyne wejście do seta
        if (pow.size() > 1) {
            pow.remove(new HashSet<T>());
        }
        this.czesci = new HashSet<Set<Set<T>>>();
    }

    // Znajduje zestawy części dla każdego wpisu oraz zwraca podziały, które zostały
    // znalezione
    public Set<Set<Set<T>>> znajdzWszystkiePodzialy() {
        for (Set<T> set : pow) {
            Set<Set<T>> obecny = new HashSet<Set<T>>();
            obecny.add(set);
            szukajPodzialy(obecny);
        }
        return czesci;
    }

    // Znajduje wszystkie zestawy podziałów dla danych wejściowych i podaje je do
    // zmiennej globalnej czesci
    private void szukajPodzialy(Set<Set<T>> obecny) {
        int maxLen = baza.size() - glebokosc(obecny);
        if (maxLen == 0) {
            czesci.add(obecny);
        } else {
            for (int i = 1; i <= maxLen; i++) {
                for (Set<T> set : pow) {
                    if (set.size() == i) {
                        if (!czyGlebokoOsadzony(set, obecny)) {
                            Set<Set<T>> nastepny = new HashSet<Set<T>>();
                            nastepny.addAll(obecny);
                            nastepny.add(set);
                            szukajPodzialy(nastepny);
                        }
                    }
                }
            }
        }
    }

    // Tworzy power set z podstawowego seta.
    private Set<Set<T>> powerSet(Set<T> baza) {
        Set<Set<T>> pset = new HashSet<Set<T>>();
        if (baza.isEmpty()) {
            pset.add(new HashSet<T>());
            return pset;
        }
        List<T> list = new ArrayList<T>(baza);
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
    private int glebokosc(Set<Set<T>> set) {
        int glebokosc = 0;
        for (Set<T> s : set) {
            glebokosc += s.size();
        }
        return glebokosc;
    }

    // Sprawdza, czy którykolwiek z zestawów należy do któregokolwiek z podzbiorów
    private boolean czyGlebokoOsadzony(Set<T> set, Set<Set<T>> obecny) {
        boolean containing = false;

        for (Set<T> s : obecny) {
            for (T item : set) {
                containing |= s.contains(item);
            }
        }
        return containing;
    }
}