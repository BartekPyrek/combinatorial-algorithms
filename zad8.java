import java.util.ArrayList;
import java.util.Collections;

public class zad8 {

    static int nominaly = 4;
    static int miejscaNaKopercie = 4;
    static int dokladnosc = 10;
    static ArrayList<Integer> tablicaNominalow;
    static int sumaZnaczkow = 0;
    static Boolean czyDalej = true;
    static int najwyzszaSuma = 0;
    static ArrayList<Integer> listaPowtorzen = new ArrayList<>();
    static String najlepszeNominaly;
    static boolean czyTakieSame;

    public static void main(String[] args) {
        System.out.println("Ilosc znaczkow: " + nominaly + " \nMiejsc na kopercie:  " + miejscaNaKopercie);
        tablicaNominalow = generujNominaly(nominaly);
        int rozwiazanie;
        System.out.println("Start wyszukiwania od nominałów o wartościach: " + tablicaNominalow.toString() + "\n");
        while (czyDalej) {
            rozwiazanie = rozwiaz(nominaly, miejscaNaKopercie);
            usprawnijSzukanie(dokladnosc, rozwiazanie);
            reorganizujTabliceNominalow(nominaly, miejscaNaKopercie, dokladnosc, rozwiazanie);
        }
        System.out.println("\nNajwyższa możliwa do uzyskania liczba to: " + najwyzszaSuma
                + " dla znaczków o nominałach: " + najlepszeNominaly + "\n\n");
    }

    public static ArrayList<Integer> generujNominaly(int nominaly) {
        ArrayList<Integer> tablica = new ArrayList<Integer>(nominaly);
        for (int i = 1; i <= nominaly; i++) {
            tablica.add(i);
        }
        return tablica;
    }

    public static int rozwiaz(int nominaly, int miejsca) {
        int liczba = 1;
        int poprzedniaSuma = 0;
        int aktualnaSuma;
        while (true) {
            if (miejsca > liczba) {
                generujRozklad(liczba, liczba);
            } else {
                generujRozklad(liczba, miejsca);
            }

            if (sumaZnaczkow - poprzedniaSuma > 1) {
                aktualnaSuma = poprzedniaSuma;
                if (najwyzszaSuma < aktualnaSuma) {
                    najwyzszaSuma = aktualnaSuma;
                    najlepszeNominaly = tablicaNominalow.toString();
                    System.out.println(najwyzszaSuma + " dla tablicy nominalow: " + najlepszeNominaly);
                }
                break;
            }
            if (miejsca * Collections.max(tablicaNominalow) == liczba) {
                aktualnaSuma = sumaZnaczkow;
                if (najwyzszaSuma < aktualnaSuma) {
                    najwyzszaSuma = aktualnaSuma;
                    najlepszeNominaly = tablicaNominalow.toString();
                    System.out.println(najwyzszaSuma + " dla tablicy nominalow: " + najlepszeNominaly);
                }
                break;
            }

            liczba++;
            poprzedniaSuma = sumaZnaczkow;
        }
        return aktualnaSuma;
    }

    public static void usprawnijSzukanie(int licznik, int aktualnaSuma) {
        listaPowtorzen.add(aktualnaSuma);
        if (listaPowtorzen.size() > licznik) {
            listaPowtorzen.remove(0);
        }
    }

    public static void reorganizujTabliceNominalow(int nominaly, int miejsca, int licznik, int sumaNominalow) {
        for (int i = 1; i < listaPowtorzen.size(); i++) {
            if (!listaPowtorzen.get(0).equals(listaPowtorzen.get(i))) {
               czyTakieSame = false;
            }

        }
        czyTakieSame = true;
        
        if ((listaPowtorzen.size() > licznik - 1) && czyTakieSame) {
            if (sumaNominalow == miejsca) {
                tablicaNominalow.remove(tablicaNominalow.size() - 1);
                listaPowtorzen.clear();
            } else {
                tablicaNominalow.set(tablicaNominalow.size() - 1, 116);
                listaPowtorzen.clear();
            }
            return;
        }

        int wartoscElementu = tablicaNominalow.get(tablicaNominalow.size() - 1);
        if (wartoscElementu == 1) {
            czyDalej = false;
        } else if (wartoscElementu <= 115) {
            tablicaNominalow.set(tablicaNominalow.size() - 1, wartoscElementu + 1);
        } else {
            tablicaNominalow.remove(tablicaNominalow.size() - 1);
            reorganizujTabliceNominalow(nominaly, miejsca, licznik, sumaNominalow);
            wartoscElementu = tablicaNominalow.get(tablicaNominalow.size() - 1);
            tablicaNominalow.add(wartoscElementu + 1);
        }
    }
    
    static void generujRozklad(int liczba, int dlugoscLiczbyZapisanejJedynkami) {
        int dlugosc = 1;
        int indeks;
        ArrayList<Integer> rozklad = new ArrayList<Integer>();
        rozklad.add(-1);
        rozklad.add(liczba);
        while (dlugosc <= dlugoscLiczbyZapisanejJedynkami) {
            if (waliduj(rozklad)) {
                sumaZnaczkow = sum(rozklad);
            }
            indeks = dlugosc - 1;
            while (rozklad.get(dlugosc) - rozklad.get(indeks) < 2) {
                indeks--;
            }
            if (indeks != 0) {
                for (int i = dlugosc - 1; i >= indeks; i--) {
                    rozklad.set(i, rozklad.get(indeks) + 1);
                }
                rozklad.set(dlugosc, 0);
            } else {
                for (int i = 1; i <= dlugosc; i++) {
                    rozklad.set(i, 1);
                }
                dlugosc++;
                rozklad.add(0);

            }
            rozklad.set(dlugosc, liczba - sum(rozklad));
        }
    }

    static int sum(ArrayList<Integer> tablica) {
        int suma = 0;
        for (int i = 1; i < tablica.size(); i++) {
            suma += tablica.get(i);
        }
        return suma;
    }

    public static Boolean waliduj(ArrayList tablica) {
        ArrayList<Integer> temp = new ArrayList<Integer>(tablica);
        temp.remove(0);
        for (Integer liczba : temp) {
            if (!tablicaNominalow.contains(liczba)) {
                return false;
            }
        }
        return true;
    }
}