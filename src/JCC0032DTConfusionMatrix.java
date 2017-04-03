// JCC0032DTConfusionMatrix.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTConfusionMatrix {

    private JCC0032DTDecisionTree t;

    private List domain;
    private int[][] confusionMatrix;

    private int correct;
    private int incorrect;

    private StringBuilder str;
    private int elements;
    
    private List incorrects; // Used fo analysis to determine why misclassified
   
    public JCC0032DTConfusionMatrix() {
        correct = 0;
        incorrect = 0;
        incorrects = new ArrayList();
    }
    
    public int[][] getMatrix(){
        return this.confusionMatrix;
    }

    public void setTree(JCC0032DTDecisionTree t){
        this.t = t;
        
    }
    
    private void setElemets() {
        this.elements = domain.size();
    }
    
    @Override
    public String toString() {
        List l = new ArrayList();
        for(int [] v : this.confusionMatrix){
            List r = new ArrayList();
            for(int i :v ){
                r.add(i);
            }
            l.add(r);
        }
        JCC0032DTPrintTable pt = new JCC0032DTPrintTable();
        pt.setData("Predict", domain, "Actual", domain, l);
        return pt.toString() + "\n" + "Percentage:" + getPercentage();
    }

    public List returnPredicts(List<List> records, List attributes, List grouping) {
        List dt_class = new ArrayList<>();
        for (int j = 0; j < records.size(); j++) {
            Object actual = grouping.get(j);
            Object predict = t.returnGroup(records.get(j), attributes);           
            this.insert(predict, actual, records.get(j));
            dt_class.add(predict);
        }
        return dt_class;
    }

    public void setDomain(List domain) {
        this.domain = domain;
        this.sizeMatrix();
    }

    public void addDomain(Object o) {
        this.domain.add(o);
        this.sizeMatrix();
    }

    protected void sizeMatrix() {
        this.confusionMatrix = new int[this.domain.size()][this.domain.size()];
    }

    private void insert(Object predict, Object actual, List record) {
        // Update correct vs incorrect totals
        if (predict.equals(actual)) {
            correct++;
        } else {
            incorrect++;
            incorrects.add(record);
        }

        // Locate coressponding matrix column/row
        int i = 0;
        for (; i < domain.size(); i++) {
            if (this.domain.get(i).equals(actual)) {
                break;
            }
        }
        if (i >= domain.size()) {
            // throw exception because element wasn't found in attribues domain
        }
        int j = 0;
        for (; j < domain.size(); j++) {
            if (this.domain.get(j).equals(predict)) {
                break;
            }
        }

        // Update Matrix if predicted was not in original training set
        if (j >= domain.size()) { // 
            this.addDomain(predict);
            int[][] tmp = new int[this.domain.size()][this.domain.size()];
            for(int i1 = 0 ; i1 < this.confusionMatrix.length ; i1++){
                for(int j1 = 0 ; j1 < this.confusionMatrix.length ; j1++){
                    tmp[i1][j1] = this.confusionMatrix[i1][j1];
                }
            }
            this.confusionMatrix = tmp;
        }

        // Update Inividual row/column
        ++this.confusionMatrix[i][j];
    }

    public double getPercentage() {
        return (double) (1.0 * this.correct) / (1.0*  (this.correct + this.incorrect));
    }
}
