package visual.binarytreevisual;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static AppData instance; // Singleton instance

    public List<List<Integer>> Manualindexes = new ArrayList<>();
    public List<Integer> Manualqueries = new ArrayList<>();
    public int sizeManual;
    public List<List<Integer>> ManualoutputForDisplay = new ArrayList<>();
    public String ManualIterrationTextAreaResult; // To hold debug output as a string

    public List<List<Integer>> Fileindexes = new ArrayList<>();
    public List<Integer> Filequeries = new ArrayList<>();
    public int sizeFile;
    public List<List<Integer>> FileoutputForDisplay = new ArrayList<>();
    public String FileIterrationTextAreaResult;

    // New properties for timing and counting
    public long ManualtimerNanoseconds; // To store elapsed time in nanoseconds
    public String ManualpairCount; // To store pair count as a string
    public List<Integer> ManualfinalIteration1DArray;

    public long FiletimerNanoseconds; // To store elapsed time in nanoseconds
    public String FilepairCount; // To store pair count as a string
    public List<Integer> FilefinalIteration1DArray;



    // Private constructor to prevent instantiation
    private AppData() {
        this.ManualtimerNanoseconds = 0;
        this.ManualpairCount = "";
        this.FiletimerNanoseconds = 0;
        this.FilepairCount = "";
    }

    // Method to get the singleton instance
    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }
}
