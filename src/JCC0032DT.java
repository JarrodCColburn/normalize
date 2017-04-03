// JCC0032DT.java
// Jarrod Colburn
// Due: 3 Apr 17

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JCC0032DT {

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
        
        // Copy a subSet of RecordSet to TrainingSet
        JCC0032DTRecordSet trainSet = mainSet.returnTraining(cmd.getT());
        
        // Grow a tree using training data as root node
        JCC0032DTNode node = new JCC0032DTNode();
        node.setContents(trainSet);
        JCC0032DTDecisionTree tree = new JCC0032DTDecisionTree();
        tree.setRoot(node);
        tree.growTree();
        
        // Produce a tree graph
        String treeGraph = tree.toString(); // TODO print to file
        
        // Use tree to build confusion matrix
        JCC0032DTConfusionMatrix matrix = new JCC0032DTConfusionMatrix();
        matrix.setTree(tree);
        
        // Feed full dataset to confusion matrix
        matrix.setDomain(arff.getGroupingDomain());
        List predictions = matrix.returnPredicts(arff.getRecords(), arff.getAttributes(), arff.getGrouping());
        
        // Produce Outputs from matrix
        String mat = matrix.toString();
         
        // Append predictions to original ARFF
        arff.setPredictions("dt_class", "real", predictions);
        String arffOut = arff.toString();
        
        
        
        String me = "JCC0032";
        String inputFile = cmd.getFileName();
        int m = ((int) cmd.getT());
        FileWriter fw;
        PrintWriter pw;
        
        // Print Tree to file
        String dtt = me + "DTTrain" + m + "InputFile.dt";
        fw = new FileWriter(dtt, true);
        pw = new PrintWriter(fw);
        pw.print(treeGraph);
        pw.close();
        
        // Print Aarf file with predictions to file
        String dta = me + "DTApply" + m + inputFile;
        fw = new FileWriter(dta, true);
        pw = new PrintWriter(fw);
        pw.print(arffOut);
        pw.close();
        
        // YourLoginDTAccuracyMInputFile
        String dta2 = me + "DTAccuracy" + m + inputFile;
        fw = new FileWriter(dta2, true);
        pw = new PrintWriter(fw);
        pw.print(mat);
        pw.close();
    }
}
