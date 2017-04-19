// JCC0032DTEntropyBox.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Used to calculate entropy.
 * 
 * @author jarrodcolburn
 */
public class JCC0032DTEntropyBox {

    private List attributes;
    private List classifier;
    private int[][] entropyBox;

    public JCC0032DTEntropyBox() {
    }
    
    /** 
     * 
     * @param attributes
     * @param classifier 
     */
    public JCC0032DTEntropyBox(List attributes, List classifier) {
        this.attributes = attributes;
        this.classifier = classifier;
        entropyBox = new int[attributes.size()][classifier.size()];
    }

    public void insert(Object a, Object c) {
        int i = 0;
        for (; i < attributes.size(); i++) {
            if (this.attributes.get(i).equals(a)) {
                break;
            }
        }
        if(i >= attributes.size()){
            // throw exception because element wasn't found in attribues domain
        }
        int j = 0;
        for (; j < classifier.size(); j++) {
            if (this.classifier.get(j).equals(c)) {
                break;
            }
        }
        if(j >= attributes.size()){
            // throw exception because element wasn't found in classifier domain
        }
        ++this.entropyBox[i][j];
    }

    public Double getEntropy() {
        int totalCount = 0;
        double totalEntropy = 0.0;
        for (int i = 0; i < this.attributes.size(); i++) {
            int localCount = 0;
            for (int j = 0; j < this.classifier.size(); j++) {
                localCount += this.entropyBox[i][j];
            }
            totalCount += localCount;
            Double localEntropy = 0.0;
            for (int j = 0; j < this.classifier.size(); j++) {
                double probability = (1.0 * this.entropyBox[i][j]) / (1.0 * localCount);
                if (probability % 1 != 0) {
                    localEntropy -= (probability * (Math.log(probability) / Math.log(2)));
                }
            }
            totalEntropy += (localEntropy * (1.0 * localCount));
        }
        return totalEntropy / (1.0 * totalCount);
    }
    public void insertAll(List attributes, List classifier){
        if(attributes.size() != classifier.size()){
            // TODO throw an exception because lengths don't match.
        }
        int length = attributes.size();
        for(int c = 0; c < length ;c++){
            this.insert(attributes.get(c), classifier.get(c));
        }
    }
}
