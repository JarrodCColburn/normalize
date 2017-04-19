// JCC0032DTineARFF.java
// Jarrod Colburn
// Due: 3 Apr 17

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Used to take in ARFF data and transform into usable data.
 * Parse ARFF file, adding data to respective area.
 * @author jarrodcolburn
 */
public class JCC0032DTinARFF {

    private Scanner s;
    
    private String relation;
    
    private List<List> records;

    private String classname;
    private String classtype;
    private int classIdx = -1;
    private ArrayList<Integer> grouping;
    private List<Integer> groupingDomain;

    private ArrayList<String> attributes ;
    private List<List> recordDomain;
    private ArrayList<String> types; 
    
    // Used for exercise C
    private String predictName;
    private List predictions;
    private String predictType;
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        String br = "\n";
        String s = " ";

        int columns = this.attributes.size();
        int rows = this.records.size();
                
        sb.append("@relation").append(s).append(relation).append(br);
        
        sb.append(br).append(JCC0032DTStringBuilderHelper.toBuilder("%s", "@attribute", attributes, types));
        if(this.classname != null && this.classtype != null){
            sb.append("@attribute").append(s).append(classname).append(s).append(classtype).append(br);
        }
        if(this.predictName != null && this.predictType != null){
            sb.append("@attribute").append(s).append(predictName).append(s).append(predictType).append(br);
        }
        
        sb.append(br).append("@data").append(br);
        if(predictions != null){
            sb.append(JCC0032DTStringBuilderHelper.toBuilder("% 3d", records, grouping, predictions));
        }
        else{
            sb.append(JCC0032DTStringBuilderHelper.toBuilder("% 3d", records, grouping));
        }
        
        return sb.toString();
    }
    
    public void setPredictions(String predictionName, String predictionType, List predictions){
        this.predictName = predictionName;
        this.predictType = predictionType;
        this.predictions = predictions;
    }

    public JCC0032DTinARFF(String inFile, String classname) throws FileNotFoundException {
        this.classname = classname;
        this.s = new Scanner(new File(inFile));
        while (s.hasNext()) {
            if (s.hasNext("@relation")) {
                setRelation();
            }
            if (s.hasNext("@attribute")) {
                setAttributes();
            }
            if (s.hasNext("@data")) {
                setData();
            }
            if (s.hasNext()) {
                s.next();
            }
        }
        s.close();
    }

    private void setRelation() {
        s.next();
        this.relation = s.nextLine().trim();
    }

    public String getRelation() {
        return this.relation;
    }

    private void setAttributes() {
        this.types = new ArrayList<>();
        this.attributes = new ArrayList<>();
        int i = 0;
        types.clear();
        while (s.hasNext("@attribute")) {
            s.next();
            if (s.hasNext(this.classname)) {
                this.classIdx = i; // Attribute is class, note index.
                s.next();
                this.classtype = s.nextLine().trim();
            } else {
                attributes.add(s.next());
                types.add(s.nextLine().trim()); // Could process real
                i++;
            }
        }
        this.recordDomain = new ArrayList<>();
        for(String s : this.types){
            List l = parse2Int(s);
            this.getRecordDomain().add(l);
        }
        this.groupingDomain = parse2Int(classtype);
        
    }

    public ArrayList<String> getAttributes() {
        return this.attributes;
    }

    private void setData() {
        this.grouping = new ArrayList<>();
        this.records = new ArrayList<>();
        Object o = s.next();
        int max = this.attributes.size();
        max += (classIdx > -1) ? 1 : 0;
        while (s.hasNext()) {
            List record = new ArrayList<>(this.attributes.size());
            for (int j = 0; j < max; j++) {
                if (j == classIdx) {
                    getGrouping().add(Integer.parseInt(s.next()));
                } else {
                    record.add(Integer.parseInt(s.next()));
                }
            }
            this.records.add(record);
        }
    }

    public List<List> getRecords() {
        return this.records;
    }

    public ArrayList<String> getTypes() {
        return this.types;
    }
    
    public List<Integer> parse2Int(String str){
        List ints = new ArrayList<>();
        String tmp = str.substring(1, str.length()-1);
        String[] tmps = tmp.split("[,\\s]+");
        for(String i: tmps){
            ints.add(Integer.parseInt(i));
        }
        return ints;
    }

    /**
     * @return the groupingDomain
     */
    public List<Integer> getGroupingDomain() {
        return groupingDomain;
    }

    /**
     * @return the recordDomain
     */
    public List<List> getRecordDomain() {
        return recordDomain;
    }

    /**
     * @return the grouping
     */
    public ArrayList<Integer> getGrouping() {
        return grouping;
    }
}
