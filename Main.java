import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        Integer[] data = {1, 2, 3, 4};
        DataListener<Integer> dataListener = dataResults -> System.out.println(Arrays.toString(dataResults));

        PermutationGenerator permutationGenerator = new PermutationGenerator(data, dataListener);

        System.out.println("Antylex algorytm:");
        permutationGenerator.generate(new Antylex());

        System.out.println("\n");
        System.out.println("BMI algorytm: ");
        permutationGenerator.generate(new BMI());

        System.out.println("\n");
        System.out.println("Johnson Trotter algorytm: ");
        permutationGenerator.generate(new JohnsonTrotter());
    }
}
