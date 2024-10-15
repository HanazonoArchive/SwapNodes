package visual.binarytreevisual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexProcessor {

    public static List<List<Integer>> parseIndexes(String inputText, int size) {
        List<List<Integer>> indexes = new ArrayList<>();
        String[] lines = inputText.split("\n");
        int pairsProcessed = 0;

        for (String line : lines) {
            if (pairsProcessed >= size) {
                break;
            }

            String[] values = line.trim().split("\\s+");

            if (values.length == 2) {
                try {
                    int a = Integer.parseInt(values[0]);
                    int b = Integer.parseInt(values[1]);
                    indexes.add(Arrays.asList(a, b));
                    pairsProcessed++;
                } catch (NumberFormatException e) {
                    System.out.println("[Error]: Invalid number format in input: " + line);
                }
            } else {
                System.out.println("[Error]: Incorrect number of values in line: " + line);
            }
        }
        return indexes;
    }
}
