package visual.binarytreevisual;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {

    public static List<Integer> parseQueries(String queryInput) {
        List<Integer> queries = new ArrayList<>();
        String[] queryParts = queryInput.trim().split("\\s+");

        try {
            for (String part : queryParts) {
                queries.add(Integer.parseInt(part));
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error]: Invalid query format in input: " + queryInput);
        }
        return queries;
    }
}
