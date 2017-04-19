import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecisionTreeTest {
    
    public List a0 = new ArrayList(Arrays.asList(40, 1, 1, 9));
    public List a1 = new ArrayList(Arrays.asList(30, 1, 1, 5));
    public List a2 = new ArrayList(Arrays.asList(35, 1, 1, 9));
    public List a3 = new ArrayList(Arrays.asList(40, 1, 1, 5));
    public List a4 = new ArrayList(Arrays.asList(30, 2, 0, 5));
    public List a5 = new ArrayList(Arrays.asList(40, 2, 0, 9));
    public List a6 = new ArrayList(Arrays.asList(30, 2, 1, 9));
    public List a7 = new ArrayList(Arrays.asList(35, 2, 0, 9));
    public List a8 = new ArrayList(Arrays.asList(40, 2, 0, 5));
    public List a9 = new ArrayList(Arrays.asList(40, 2, 1, 5));
    public List aA = new ArrayList(Arrays.asList(30, 3, 0, 5));
    public List aB = new ArrayList(Arrays.asList(30, 3, 0, 9));
    public List aC = new ArrayList(Arrays.asList(35, 3, 0, 5));
    public List aD = new ArrayList(Arrays.asList(35, 3, 1, 5));
    public List<List> records = Arrays.asList(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, aA, aB, aC, aD);
    
    public List age = Arrays.asList(30, 35, 40);
    public List income = Arrays.asList(1, 2, 3);
    public List student = Arrays.asList(0, 1);
    public List credit = Arrays.asList(5, 9);
    public List<List> recordDomain = new ArrayList(Arrays.asList(age, income, student, credit));

    public List grouping = new ArrayList(Arrays.asList(0,1,1,1,0,0,1,1,1,1,0,0,1,1));
    public List groupDomain =  Arrays.asList(0,1);
    public List attributes = new ArrayList(Arrays.asList("age","income","student","credit"));

    public JCC0032DTRecordSet r;
    
    public DecisionTreeTest() {
        r = new JCC0032DTRecordSet();
        r.setAttributes(attributes);
        r.setRecords(records, grouping);
        r.setDomains(recordDomain, groupDomain);
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of setRoot method, of class JCC0032DTDecisionTree.
     */
    @Test
    public void testSetRoot() {
    }

    @Test
    public void testDecisionTree(){
        JCC0032DTDecisionTree instance = new JCC0032DTDecisionTree();
        JCC0032DTNode n = new JCC0032DTNode();
        n.setContents(1234);
        instance.setRoot(n);
    }
    /**
     * Test of growTree method, of class JCC0032DTDecisionTree.
     */
    @Test
    public void testGrowTree_0args() throws CloneNotSupportedException {
        JCC0032DTDecisionTree instance = new JCC0032DTDecisionTree();
        JCC0032DTRecordSet training = r.returnTraining(50);
        
        JCC0032DTNode n = new JCC0032DTNode();
        n.setContents(training);
        instance.setRoot(n);
        instance.growTree();
        
        JCC0032DTConfusionMatrix cm = new JCC0032DTConfusionMatrix();
        cm.setTree(instance);
        cm.setDomain(groupDomain);
        List results = cm.returnPredicts(records, attributes, grouping);
        int[][] matrix = cm.getMatrix();
        double  percent= cm.getPercentage();
        String s = cm.toString();
        Integer adsfajsdlkajsdlk;
    }

    /**
     * Test of growTree method, of class JCC0032DTDecisionTree.
     */
    @Test
    public void testGrowTree_Node() {
    }
    
}
