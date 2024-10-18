package visual.binarytreevisual;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SwapNodes3_HomeInputController {
    private Stage stage;
    private long ManualstartTime;
    private long FilestartTime;
    private boolean isManualInput = true;

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

    @FXML
    private Label fileNameLB;

    public int size;

    public List<List<Integer>> OutputForDisplay = new ArrayList<>();

    @FXML
    void HandlesButtonClicked(MouseEvent event) {
        AppData appData = AppData.getInstance();
        if (event.getSource() == ManualGenerateTreeButton) {
            // Manual input selected
            appData.ManualtimerNanoseconds = 0;
            isManualInput = true;
            handleManualInput();
        } else if (event.getSource() == OpenFileButton) {
            // File input selected (just selecting file)
            appData.FiletimerNanoseconds = 0;
            isManualInput = false;
            handleFileInput();
        } else if (event.getSource() == OpenFileGenerateTreeButton) {
            // File-based tree generation happens here
            handleFileGenerateTree();
        }
    }

    private void handleManualInput() {
        try {
            size = Integer.parseInt(SizeTextField.getText());

            String inputText = ManualInputTextArea.getText();
            AppData appData = AppData.getInstance();
            appData.sizeManual = size;
            appData.Manualindexes = IndexProcessor.parseIndexes(inputText, size);
            appData.Manualqueries = QueryProcessor.parseQueries(SwapDepthsTextField.getText());
            appData.ManualpairCount = String.valueOf(appData.Manualindexes.size());

            System.out.println("[Debug]: Parsed Manualindexes = " + appData.Manualindexes);
            System.out.println("[Debug]: Parsed Manualqueries = " + appData.Manualqueries);

            List<List<Integer>> result = AlgorithmSwapNodes.swapNodes(appData.Manualindexes, appData.Manualqueries);

            displayResult(result, true);

        } catch (NumberFormatException e) {
            System.out.println("[Error]: Invalid input in size or query fields.");
        } catch (Exception e) {
            System.out.println("[Error]: Something went wrong while processing the input.");
            e.printStackTrace();
        }
    }

    private void handleFileInput() {
        AppData appData = AppData.getInstance();

        ManualstartTime = 0;
        FilestartTime = System.nanoTime();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                String fileContent = new String(Files.readAllBytes(selectedFile.toPath()));

                size = Integer.parseInt(SizeTextField.getText());
                appData.sizeFile = size;

                String fileName = selectedFile.getName();
                fileNameLB.setText(fileName);

                appData.Fileindexes = IndexProcessor.parseIndexes(fileContent, size);
                appData.Filequeries = QueryProcessor.parseQueries(SwapDepthsTextField.getText());
                appData.FilepairCount = String.valueOf(appData.Fileindexes.size());

                System.out.println("[Debug]: Parsed Fileindexes from file = " + appData.Fileindexes);

            } catch (IOException e) {
                System.out.println("[Error]: Failed to read the file.");
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("[Error]: Invalid input in size field.");
            } catch (Exception e) {
                System.out.println("[Error]: Something went wrong while processing the file.");
                e.printStackTrace();
            }
        }
    }

    private void handleFileGenerateTree() {
        try {
            // Here, we use the content already parsed in handleFileInput
            AppData appData = AppData.getInstance();
            size = Integer.parseInt(SizeTextField.getText());  // Get the size of the tree
            appData.sizeFile = size;

            // Ensure Fileindexes and Filequeries are prepared for processing
            if (appData.Fileindexes.isEmpty() || appData.Filequeries.isEmpty()) {
                System.out.println("[Error]: Fileindexes or Filequeries are empty. Please check your file input.");
                return; // Exit if the data is not valid
            }

            List<List<Integer>> result = AlgorithmSwapNodes.swapNodes(appData.Fileindexes, appData.Filequeries); // Perform tree generation

            // Display the result of the swap operation
            displayResult(result, false);  // `false` signifies that it was file input

        } catch (NumberFormatException e) {
            System.out.println("[Error]: Invalid input in size or query fields.");
        } catch (Exception e) {
            System.out.println("[Error]: Something went wrong while processing the file.");
            e.printStackTrace();
        }
    }

    private void displayResult(List<List<Integer>> result, boolean isManual) {
        AppData appData = AppData.getInstance();
        List<Integer> finalIteration = new ArrayList<>();
        StringBuilder output = new StringBuilder();

        for (List<Integer> traversal : result) {
            if (isManual) {
                appData.ManualoutputForDisplay.add(new ArrayList<>(traversal));
            } else {
                appData.FileoutputForDisplay.add(new ArrayList<>(traversal));
            }

            // Capture the final iteration
            if (result.indexOf(traversal) == result.size() - 1) {
                finalIteration.addAll(traversal);
            }

            for (int id : traversal) {
                output.append(id).append(" ");
            }
            output.append("\n");
        }

        if (isManual) {
            long ManualendTime = System.nanoTime();
            long ManualelapsedTime = ManualendTime - ManualstartTime;
            appData.ManualIterrationTextAreaResult = "[Result]: \n" + output.toString();
            appData.ManualfinalIteration1DArray = finalIteration;
            appData.ManualtimerNanoseconds = ManualelapsedTime;

        } else {
            long FileendTime = System.nanoTime();
            long FileelapsedTime = FileendTime - FilestartTime;
            appData.FileIterrationTextAreaResult = "[Result]: \n" + output.toString();
            appData.FilefinalIteration1DArray = finalIteration;
            appData.FiletimerNanoseconds = FileelapsedTime;

        }

        // Print the final iteration array for debugging
        System.out.println("[Final Iteration (1D Array)]: " + finalIteration);
        System.out.println(isManual ? appData.ManualIterrationTextAreaResult : appData.FileIterrationTextAreaResult);
    }

    @FXML
    void HandlesKeyboardTyped(MouseEvent event) {
        if (event.getSource() == ManualInputTextArea) {
            AppData appData = AppData.getInstance();
            System.out.println("[Debug]: Key Typed has been observed.");
            FilestartTime = 0;
            ManualstartTime = System.nanoTime();  // Start the timer when typing happens
        }
    }
}
