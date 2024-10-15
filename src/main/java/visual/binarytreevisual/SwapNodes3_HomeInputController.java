package visual.binarytreevisual;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SwapNodes3_HomeInputController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Pane InputMainPane;

    @FXML
    private Pane ManualGenerateTreeButton;

    @FXML
    private TextArea ManualInputTextArea;

    @FXML
    private Pane OpenFileButton;

    @FXML
    private Pane OpenFileGenerateTreeButton;

    @FXML
    private TextField SizeTextField;

    @FXML
    private TextField SwapDepthsTextField;

    // Parameters
    public List<List<Integer>> indexes;
    public List<Integer> queries;
    public int size;

    // Output Storage
    public List<List<Integer>> OutputForDisplay = new ArrayList<>(); // Initialize the OutputForDisplay

    @FXML
    void HandlesButtonClicked(MouseEvent event) {
        if (event.getSource() == ManualGenerateTreeButton) {
            try {
                // Get the size and parse the indexes and queries
                size = Integer.parseInt(SizeTextField.getText());

                String inputText = ManualInputTextArea.getText();
                indexes = IndexProcessor.parseIndexes(inputText, size);

                String queryInput = SwapDepthsTextField.getText();
                queries = QueryProcessor.parseQueries(queryInput);

                System.out.println("[Debug]: Parsed indexes = " + indexes);
                System.out.println("[Debug]: Parsed queries = " + queries);

                // Call the Algorithm's swapNodes method
                List<List<Integer>> result = AlgorithmSwapNodes.swapNodes(indexes, queries);

                // Display the results and store them in OutputForDisplay
                displayResult(result);

            } catch (NumberFormatException e) {
                System.out.println("[Error]: Invalid input in size or query fields.");
            } catch (Exception e) {
                System.out.println("[Error]: Something went wrong while processing the input.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void HandlesKeyboardTyped(MouseEvent event) {
        if (event.getSource() == ManualInputTextArea) {
            System.out.println("[Debug]: Key Typed has been observed.");
        }
    }

    // Method to display the result, either in the console or in the UI
    private void displayResult(List<List<Integer>> result) {
        OutputForDisplay.clear(); // Clear previous results before adding new ones

        StringBuilder output = new StringBuilder();
        for (List<Integer> traversal : result) {
            // Create a copy of the current traversal and add it to OutputForDisplay
            OutputForDisplay.add(new ArrayList<>(traversal)); // Copy the current traversal

            for (int id : traversal) {
                output.append(id).append(" ");
            }
            output.append("\n");
        }

        // Print to console for debugging purposes
        System.out.println("[Result]: \n" + output.toString());
        System.out.println("[Result For Display]: \n" + OutputForDisplay);
    }
}
