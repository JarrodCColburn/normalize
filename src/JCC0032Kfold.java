// JCC0032Kfold.java
// Jarrod Colburn
// Due: 3 Apr 17

//import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JCC0032Kfold {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException {

        // Get data from file
        JCC0032DTcmdLine cmd = new JCC0032DTcmdLine(args);
        JCC0032DTinARFF arff = new JCC0032DTinARFF(cmd.getFileName(), cmd.getClassAttribute());

        // initalize a recordset and move data taken in from arff
        JCC0032DTRecordSet mainSet = new JCC0032DTRecordSet();
        mainSet.attributes = arff.getAttributes();
        mainSet.recordDomain = arff.getRecordDomain();
        mainSet.records = arff.getRecords();        
        mainSet.groupDomain = arff.getGroupingDomain();
        mainSet.grouping = arff.getGrouping();

        // Create folds (sub sets) of original recordset
        int foldNum = (int) cmd.getK();
        List<JCC0032DTRecordSet> folds = mainSet.divvy(foldNum);

        // Calculate needed known values for each fold of records
        ArrayList<ArrayList<int[][]>> intersectMatrix = new ArrayList<>();
        for (JCC0032DTRecordSet fold : folds) {
            fold.initIntersect();
            fold.calcIntersects();
            intersectMatrix.add(fold.getIntersections());
        }

        // Make Predictions using knowns
        for (int k = 0; k < foldNum; k++) {

            // Aggregate knowns from other folds
            mainSet.initIntersect();
            ArrayList<int[][]> mx = mainSet.getIntersections();
            mx = JCC0032DTRecordSet.addEmUp(intersectMatrix, mx, k);

            // Use known from all folds except current to make predictions
            folds.get(k).setPredictions(mx);
        }

        // Construct Empty Confusion Matrix
        JCC0032DTConfusionMatrix cm = new JCC0032DTConfusionMatrix();
        cm.setDomain(mainSet.getGroupDomain());
        
        StringBuilder sb = new StringBuilder();
        List predictions = new ArrayList<>();
        int f = 0;
        for (JCC0032DTRecordSet fold : folds) {
            sb.append("Fold ").append(f+1).append(" accuracy: ").append(fold.predictedCorrect()).append("\n");
            f++;
            int rows = fold.getSize();
            for (int j = 0; j < rows; j++) {
                cm.insert(fold.getPrediction(j), fold.getGrouping(j), fold.records.get(j));
            }
            predictions.addAll(fold.getPredictions());
        }

        // Append predictions to original ARFF
        arff.setPredictions("bayesClass", "real", predictions);
     
        String me = "JCC0032";
        String inputFile = cmd.getFileName();
        FileWriter fw;
        PrintWriter pw;
     
//         Print Aarf file with predictions to file
        String dta = me + foldNum + "FoldClassification" + inputFile;
        fw = new FileWriter(dta, true);
        pw = new PrintWriter(fw);
        String arffOut = arff.toString();
        pw.print(arffOut);
        pw.close();

        // Print Confusion Matrix
        String mat = cm.toString();
        String dta2 = me + foldNum + "FoldConfusion" + inputFile + ".txt";
        fw = new FileWriter(dta2, true);
        pw = new PrintWriter(fw);
        pw.print(mat);
        pw.print(sb.toString());
        pw.close();
    }
}
