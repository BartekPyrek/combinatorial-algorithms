import java.util.ArrayList;
import java.util.Scanner;

public class zad6 {
    static int licznik = 1;
    static int licznik2 = 1;
    static int wybrana_liczba;

    public static void main(String[] args) {
        Scanner wczytajLiczbe = new Scanner(System.in);
        System.out.print("Wprowadź liczbę by wypisać jej kompozcje: ");
        wybrana_liczba = wczytajLiczbe.nextInt();
        System.out.println();
        generujKombinacje(wybrana_liczba, "");
        wczytajLiczbe.close();
    }

    static void generujKombinacje(int n, String tekst) {
        if (n == 0) {
            System.out.println("Kompozycja liczby " + wybrana_liczba + " numer " + licznik2 + ") "
                    + tekst.substring(0, tekst.length() - 1));
            licznik2++;
            return;
        }

        for (int i = n; i >= 1; i--) {
            generujKombinacje(n - i, tekst + i + "+");
        }
    }
}