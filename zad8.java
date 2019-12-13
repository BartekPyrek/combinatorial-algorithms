import java.util.ArrayList;
import java.util.Collections;

public class zad8 {

    //nominały
    static int denominations = 4;
    //miejsca na kopercie
    static int stamps = 4;
    //dokladonosc
    static int accuracy = 10;
    //tablica nominałow
    static ArrayList<Integer> tableofDenominations;
    //suma znaczków
    static int sumOfStamps = 0;
    //czy dalej
    static Boolean further = true;
    //najwyższa suma
    static int highestSum = 0;
    //lista powtorzen
    static ArrayList<Integer> listOfRepetition = new ArrayList<>();
    //najlepsze nominały
    static String bestDenominations;
    //czy takie same
    static boolean isTheSame;

    public static void main(String[] args) {
        System.out.println("Ilosc znaczkow: " + denominations + " \nMiejsc na kopercie:  " + stamps);
        tableofDenominations = generateDenominations(denominations);
        int solution;
        System.out.println("Start wyszukiwania od nominałów o wartościach: " + tableofDenominations.toString() + "\n");
        while (further) {
            solution = solve(denominations, stamps);
            improveSearch(accuracy, solution);
            reorganiseTableofDenominations(denominations, stamps, accuracy, solution);
        }
        System.out.println("\nNajwyższa możliwa do uzyskania liczba to: " + highestSum
                + " dla znaczków o nominałach: " + bestDenominations + "\n\n");
    }

    //generuj nominały
    public static ArrayList<Integer> generateDenominations(int denominations) {
        ArrayList<Integer> table = new ArrayList<Integer>(denominations);
        for (int i = 1; i <= denominations; i++) {
            table.add(i);
        }
        return table;
    }

    //rozwiąż
    public static int solve(int denominations, int places) {
        int number = 1;
        int previousSum = 0;
        int actualSum;
        while (true) {
            if (places > number) {
                generateDistribution(number, number);
            } else {
                generateDistribution(number, places);
            }

            if (sumOfStamps - previousSum > 1) {
                actualSum = previousSum;
                if (highestSum < actualSum) {
                    highestSum = actualSum;
                    bestDenominations = tableofDenominations.toString();
                    System.out.println(highestSum + " dla tablicy nominalow: " + bestDenominations);
                }
                break;
            }
            if (places * Collections.max(tableofDenominations) == number) {
                actualSum = sumOfStamps;
                if (highestSum < actualSum) {
                    highestSum = actualSum;
                    bestDenominations = tableofDenominations.toString();
                    System.out.println(highestSum + " dla tablicy nominalow: " + bestDenominations);
                }
                break;
            }

            number++;
            previousSum = sumOfStamps;
        }
        return actualSum;
    }

    //usprawnij szukanie
    public static void improveSearch(int counter, int actualSum) {
        listOfRepetition.add(actualSum);
        if (listOfRepetition.size() > counter) {
            listOfRepetition.remove(0);
        }
    }

    //reorganizuje tablice nominałów
    public static void reorganiseTableofDenominations(int denominations, int places, int counter, int sumOfDenominations) {
        for (int i = 1; i < listOfRepetition.size(); i++) {
            if (!listOfRepetition.get(0).equals(listOfRepetition.get(i))) {
               isTheSame = false;
            }

        }
        isTheSame = true;
        
        if ((listOfRepetition.size() > counter - 1) && isTheSame) {
            if (sumOfDenominations == places) {
                tableofDenominations.remove(tableofDenominations.size() - 1);
                listOfRepetition.clear();
            } else {
                tableofDenominations.set(tableofDenominations.size() - 1, 116);
                listOfRepetition.clear();
            }
            return;
        }

        int valueOfElement = tableofDenominations.get(tableofDenominations.size() - 1);
        if (valueOfElement == 1) {
            further = false;
        } else if (valueOfElement <= 115) {
            tableofDenominations.set(tableofDenominations.size() - 1, valueOfElement + 1);
        } else {
            tableofDenominations.remove(tableofDenominations.size() - 1);
            reorganiseTableofDenominations(denominations, places, counter, sumOfDenominations);
            valueOfElement = tableofDenominations.get(tableofDenominations.size() - 1);
            tableofDenominations.add(valueOfElement + 1);
        }
    }
    
    //generuj rozkład
    static void generateDistribution(int number, int lengthWithOnes) {
        int length = 1;
        int index;
        ArrayList<Integer> distribution = new ArrayList<Integer>();
        distribution.add(-1);
        distribution.add(number);
        while (length <= lengthWithOnes) {
            if (validate(distribution)) {
                sumOfStamps = sum(distribution);
            }
            index = length - 1;
            while (distribution.get(length) - distribution.get(index) < 2) {
                index--;
            }
            if (index != 0) {
                for (int i = length - 1; i >= index; i--) {
                    distribution.set(i, distribution.get(index) + 1);
                }
                distribution.set(length, 0);
            } else {
                for (int i = 1; i <= length; i++) {
                    distribution.set(i, 1);
                }
                length++;
                distribution.add(0);

            }
            distribution.set(length, number - sum(distribution));
        }
    }

    static int sum(ArrayList<Integer> table) {
        int sum = 0;
        for (int i = 1; i < table.size(); i++) {
            sum += table.get(i);
        }
        return sum;
    }

    //waliduj
    public static Boolean validate(ArrayList table) {
        ArrayList<Integer> temp = new ArrayList<Integer>(table);
        temp.remove(0);
        for (Integer number : temp) {
            if (!tableofDenominations.contains(number)) {
                return false;
            }
        }
        return true;
    }
}