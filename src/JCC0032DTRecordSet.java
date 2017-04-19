// JCC0032DTRecordSet.java
// Jarrod Colburn
// Due: 3 Apr 17

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JCC0032DTRecordSet implements Cloneable {

    public Object value;
    public Object attribute;
    public Object group; // group that it maps to

    public List<String> attributes;
    public List<List> recordDomain;
    public List<List> records;

    public List groupDomain;
    public List grouping;

    public List predictions;

    private ArrayList<int[][]> intersections;

    public JCC0032DTRecordSet() {
        this.grouping = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public static ArrayList<int[][]> addEmUp(ArrayList<ArrayList<int[][]>> mats, ArrayList<int[][]> emp, int i) {
        int c = 0;
        for (ArrayList<int[][]> l : mats) {
            if (c == i) {
                c++;
                continue;
            }
            addInterset(emp, l);
            c++;
        }
        return emp;
    }

    public void setPredictions(ArrayList<int[][]> sect) {
        this.predictions = new ArrayList<>();
        for (List l : this.records) {
            int idx = this.returnIndexOfPredictedClass(l, sect);
            this.predictions.add(this.getGroupDomain().get(idx));
        }
    }

    public int returnIndexOfPredictedClass(List l, ArrayList<int[][]> sect) {
        int columns = l.size();
        int[] relatives = new int[columns];
        for (int j = 0; j < columns; j++) {
            for (int a = 0;; a++) {
                if (l.get(j).equals(this.recordDomain.get(j).get(a))) {
                    relatives[j] = a;
                    break;
                }
            }
        }
        int groupNo = this.getGroupDomain().size();
        int predict = 0;
        double globalMax = 0.0;
        for (int g = 0; g < groupNo; g++) {
            int [][] first = sect.get(0);
            int count = 0;
            for(int c = 0; c < first.length ; c++){
                count += first[c][g];
            }
            double localMax = 1.0;
            int j = 0;
            for (int[][] i : sect) {
                int top = i[relatives[j]][g];
                localMax *= top;
                localMax /= (double) count;
                j++;
            }
            localMax *= (double) count;
            // Skipping division by total count because is a constant value
            // and will not matter in comparison
            if (localMax > globalMax) {
                predict = g;
                globalMax = localMax;
            }
        }
        return predict;
    }

    public static int[][] combine2D(int[][] a, int[][] b) {
        int rows = a.length;
        int columns = a[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                a[i][j] += b[i][j];
            }
        }
        return a;
    }

    public void initIntersect() {
        this.intersections = retIntersect();
    }

    public static ArrayList<int[][]> addInterset(ArrayList<int[][]> a, ArrayList<int[][]> b) {
        int columns = a.size();
        for (int j = 0; j < columns; j++) {
            a.set(j, combine2D(a.get(j), b.get(j)));
        }
        return a;
    }

    public ArrayList<int[][]> retIntersect() {
        int columns = this.attributes.size();
        int rows = this.records.size();
        int groupNo = this.getGroupDomain().size();
        ArrayList sects = new ArrayList<>();
        for (int j = 0; j < columns; j++) {
            sects.add(new int[this.recordDomain.get(j).size()][groupNo]);
        }
        return sects;
    }

    public void calcIntersect(List l, int g) {
        int columns = this.attributes.size();
        for (int j = 0; j < columns; j++) {
            int a = this.lookup(this.recordDomain.get(j), l.get(j));
            this.intersections.get(j)[a][g]++;
        }
    }

    public void calcIntersects() {
        int rows = this.records.size();
        for (int i = 0; i < rows; i++) {
            int g = this.lookup(this.getGroupDomain(), this.grouping.get(i));
            this.calcIntersect(this.records.get(i), g);
        }
    }

    public int lookup(List l, Object o) {
        int c = 0;
        while (l.get(c) != o) {
            c++;
        }
        return c;
    }

    public List<JCC0032DTRecordSet> divvy(int K) { // Divides Record Set into K sub Recordset
        List l = new ArrayList<>();
        int m = this.getRecords().size();
        int i_start = 0;
        int i_span = m / K;
        int k_mod = m % K;
        for (int k = 0; k < K; k++) {
            int i_end = (k < k_mod) ? (i_start + i_span + 1) : (i_start + i_span);
            JCC0032DTRecordSet r = new JCC0032DTRecordSet();
            r.setAttributes(this.attributes);
            r.setDomains(this.recordDomain, this.getGroupDomain());
            r.setRecords(this.getRecords().subList(i_start, i_end), this.getGrouping().subList(i_start, i_end));
            i_start = i_end;
            l.add(r);
        }
        return l;
    }

    public void setRecords(List<List> records, List grouping) {
        this.records = records;
        this.grouping = grouping;
    }

    public void setDomains(List<List> recordDomain, List groupDomain) {
        this.recordDomain = recordDomain;
        this.groupDomain = groupDomain;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Object getGroup() {
        return this.group;
    }

    public void addRecord(List record, Object group) {
        this.getRecords().add(record);
        this.getGrouping().add(group);
    }

    public void setLabel(Object attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    public Object getAttribute() {
        return this.attribute;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public Object getValue() {
        return this.value;
    }

    public static int decideSplit(List<List> records, List<List> recordDomain, List grouping, List groupDomain) {

        int columns = recordDomain.size();
        int rows = grouping.size();

        // Initialize class to find entropy
        List<JCC0032DTEntropyBox> boxes = new ArrayList<>();
        for (int j = 0; j < columns; j++) {
            boxes.add(new JCC0032DTEntropyBox(recordDomain.get(j), groupDomain));
        }

        // Add data 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boxes.get(j).insert(records.get(i).get(j), grouping.get(i));
            }
        }

        // Return all entropies and compare
        double minimumEntropy = 1.0; // 1 is highest possible value
        int index = 0;
        for (int j = 0; j < columns; j++) {
            double localEntropy = boxes.get(j).getEntropy();
            if (localEntropy < minimumEntropy) {
                index = j;
                minimumEntropy = localEntropy;
            }
        }
        return index;
    }

    public List<JCC0032DTRecordSet> makeSplits() {
        return makeSplits(this.getRecords(), this.recordDomain, this.getGrouping(), this.getGroupDomain(), this.attributes);
    }

    public static List<JCC0032DTRecordSet> makeSplits(List<List> records, List<List> recordDomain, List grouping, List groupDomain, List attributes) {
        int columns = recordDomain.size();
        int rows = grouping.size();
        int split = decideSplit(records, recordDomain, grouping, groupDomain);

        List<JCC0032DTRecordSet> splits = new ArrayList<>();

        Object label = attributes.get(split);
        List subAttributes = quasiRemove(attributes, split);
        List childDomain = recordDomain.get(split);
        List subRecordDomain = quasiRemove(recordDomain, split);

        // Create children
        for (Object o : childDomain) {
            JCC0032DTRecordSet r = new JCC0032DTRecordSet();
            r.setLabel(label, o);
            r.setAttributes(subAttributes);
            r.setDomains(subRecordDomain, groupDomain);
            splits.add(r);
        }

        // Assign all records to corresponding (sub)RecordSet
        for (int j = 0; j < rows; j++) {
            for (JCC0032DTRecordSet r : splits) {
                List record = records.get(j);
                if (r.getValue() == record.get(split)) {
//                    record.remove(split);
                    r.addRecord(quasiRemove(record, split), grouping.get(j));
                    break;
                }
            }
        }
        return splits;
    }

    public int getSize() {
        return this.getGrouping().size();
    }

    public int getWidth() {
        return this.recordDomain.size();
    }

    public List getDomain(int idx) {
        return this.recordDomain.get(idx);
    }

    /**
     * Determines if all records of one group type. Useful as a stopping
     * condition when splitting
     *
     * @return null if more than one group, otherwise grouping Object
     */
    public Object singleGroup() {
        Object initial = this.getGrouping().get(0);
        for (Object g : this.getGrouping()) {
            if (g != initial) {
                return null;
            }
        }
        return initial;
    }

    public Object dG() {
        int globalMax = 0;
        Object dominant = this.getGroupDomain().get(0);
        for (Object d : this.getGroupDomain()) {
            int localMax = 0;
            for (Object g : this.getGrouping()) {
                if (d.equals(g)) {
                    localMax++;
                }
            }
            if (localMax > globalMax) {
                dominant = d;
                globalMax = localMax;
            }
        }

        return dominant;
    }

    @Override
    public String toString() {
        if (this.attribute == null || this.value == null) {
            return "";
        }
        if (this.group == null) {
            return this.attribute.toString() + " =" + this.value.toString();
        } else {
            return this.attribute.toString() + " =" + this.value.toString() + " " + this.group.toString();
        }
    }

    /**
     * Creates a copy of a list and removes index item by utilizing sublists
     *
     * @param list to be copied, sans idx
     * @param idx index of item to be removed
     * @return
     */
    public static List quasiRemove(List list, int idx) {
        int length = list.size();
        if (idx == 0) {
            return list.subList(1, length);
        } else if (idx == length - 1) {
            return list.subList(0, length - 1);
        } else {
            List tmp = new ArrayList<>();
            tmp.addAll(list.subList(0, idx));
            tmp.addAll(list.subList(idx + 1, length));
            return tmp;
        }
    }

    /**
     * Performs shallow copy of RecordSet, returning a percentage of the
     * original records. Records returned are the first by order, not randomly
     * picked.
     *
     * @param percent of records to return
     * @return RecordSet that is subSet of original data
     * @throws CloneNotSupportedException
     */
    public JCC0032DTRecordSet returnTraining(double percent) throws CloneNotSupportedException {

        // TODO this doesn't update domains. Shouldn't be a problem for this project though
        JCC0032DTRecordSet training = (JCC0032DTRecordSet) this.clone();  // Copy original recordSet
        int recordCount = (int) (training.getRecords().size() * percent / 100.0); // Find records to use
        training.records = training.getRecords().subList(0, recordCount); // Update records
        training.grouping = training.getGrouping().subList(0, recordCount); // Update groupings
        return training;
    }

    /**
     * @return the records
     */
    public List<List> getRecords() {
        return this.records;
    }

    /**
     * @return the grouping
     */
    public List getGrouping() {
        return grouping;
    }

    /**
     * @return the intersections
     */
    public ArrayList<int[][]> getIntersections() {
        return intersections;
    }

    public double predictedCorrect() {
        int rows = this.grouping.size();
        int correct = 0;
        for (int i = 0; i < rows; i++) {
            if (this.grouping.get(i).equals(this.predictions.get(i))) {
                correct += 1;
            }
        }
        return ((double) correct / (double) rows);
    }

    /**
     * @return the groupDomain
     */
    public List getGroupDomain() {
        return groupDomain;
    }

    public Object getPrediction(int i) {
        return this.predictions.get(i);
    }

    public Object getGrouping(int i) {
        return this.grouping.get(i);
    }

    public List getPredictions() {
        return this.predictions;
    }
}
