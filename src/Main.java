import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        Integer[] data = {1, 2, 3, 4};
        Observer<Integer> observer = dataResults -> System.out.println(Arrays.toString(dataResults));

        PermGenerator permGenerator = new PermGenerator(data, observer);

        System.out.println("Antylex algorytm:");
        permGenerator.generate(new Antylex());

        System.out.println("\n");
        System.out.println("BMI algorytm: ");
        permGenerator.generate(new BMI());

        System.out.println("\n");
        System.out.println("Johnson Trotter algorytm: ");
        permGenerator.generate(new JohnsonTrotter());
    }
}
