// JCC0032DTRecordSet.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class JCC0032RecordSet implements Cloneable{

    public Object value; 
    public Object attribute; 
    public Object group; // group that it maps to

    public List<String> attributes;
    public List<List> recordDomain;
    public List<List> records;

    public List groupDomain;
    public List grouping;

    public JCC0032RecordSet() {
        this.grouping = new ArrayList<>();
        this.records = new ArrayList<>();
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
        this.records.add(record);
        this.grouping.add(group);
    }

    public void setLabel(Object attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }
    public Object getAttribute(){
        return this.attribute;
    }

    public void setNames(List<String> attributes) {
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

    public List<JCC0032RecordSet> makeSplits() {
        return makeSplits(this.records, this.recordDomain, this.grouping, this.groupDomain, this.attributes);
    }

    public static List<JCC0032RecordSet> makeSplits(List<List> records, List<List> recordDomain, List grouping, List groupDomain, List attributes) {
        int columns = recordDomain.size();
        int rows = grouping.size();
        int split = decideSplit(records, recordDomain, grouping, groupDomain);

        List<JCC0032RecordSet> splits = new ArrayList<>();

        Object label = attributes.get(split);
        List subAttributes = quasiRemove(attributes, split);
        List childDomain = recordDomain.get(split);
        List subRecordDomain = quasiRemove(recordDomain, split);
        

        // Create children
        for (Object o : childDomain) {
            JCC0032RecordSet r = new JCC0032RecordSet();
            r.setLabel(label, o);
            r.setNames(subAttributes);
            r.setDomains(subRecordDomain, groupDomain);
            splits.add(r);
        }

        // Assign all records to corresponding (sub)RecordSet
        for (int j = 0; j < rows; j++) {
            for (JCC0032RecordSet r : splits) {
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
        return this.grouping.size();
    }
    public int getWidth(){
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
        Object initial = this.grouping.get(0);
        for (Object g : this.grouping) {
            if (g != initial) {
                return null;
            }
        }
        return initial;
    }
    
    public Object dG(){
        int globalMax = 0;
        Object dominant = this.groupDomain.get(0);
        for(Object d: this.groupDomain){
            int localMax = 0;
            for(Object g : this.grouping){
                if(d.equals(g)){
                    localMax++;
                }
            }
            if(localMax > globalMax){
                dominant = d;
                globalMax = localMax;
            }
        }
        
        return dominant;
    }

    @Override
    public String toString() {
        if(this.attribute == null || this.value == null){
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
     * Performs shallow copy of RecordSet, returning a percentage of the original records.
     * Records returned are the first by order, not randomly picked.
     * @param percent of records to return
     * @return RecordSet that is subSet of original data
     * @throws CloneNotSupportedException 
     */
    public JCC0032RecordSet returnTraining(double percent) throws CloneNotSupportedException{

        // TODO this doesn't update domains. Shouldn't be a problem for this project though
        JCC0032RecordSet training = (JCC0032RecordSet) this.clone();  // Copy original recordSet
        int recordCount = (int) (training.records.size() * percent / 100.0); // Find records to use
        training.records = training.records.subList(0, recordCount); // Update records
        training.grouping = training.grouping.subList(0, recordCount); // Update groupings
        return training;
    }
}
