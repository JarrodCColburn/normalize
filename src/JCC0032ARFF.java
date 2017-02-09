// JCC0032ARFF.java
// Jarrod Colburn
// Due: 8 Feb 17
package normalize;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class JCC0032ARFF {
    private Scanner s;
    private ArrayList<ArrayList<Double>> datas;
    private ArrayList<ArrayList<Double>> norms;
    // private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private String relation;
    
    public JCC0032ARFF(){
    }
    public void setInFile(String inFile) throws FileNotFoundException {
        this.s = new Scanner(new File(inFile));    
    }
    public void setAll(){      
        while(s.hasNext()){            
            if(s.hasNext("@relation")){
                setRelation();
            }
            if(s.hasNext("@attribute")){
                setAttributes();
            }
            if(s.hasNext("@data")){
                setData();
            }
            if(s.hasNext()){
            s.next();                
            }
        }
    }
    private void setRelation(){
        s.next();
        this.relation = s.nextLine().trim();
    }  
    public String getRelation(){
        return this.relation;
    }
    private void setAttributes(){
        attributes.clear();
        types.clear();
        while(s.hasNext("@attribute")){
            s.next();
            attributes.add(s.next());
            types.add(s.nextLine().trim()); // Could process real
        }
    }
    public ArrayList<String> getAttributes(){
        return this.attributes;
    }
    private void setData(){
        s.next();
        //TODO create handler for Attributes not set
        ArrayList<ArrayList<Double>> D = new ArrayList<>();
            for(int i = 0; i < this.attributes.size(); i++){
                D.add(new ArrayList<Double>());
            }          
        while(s.hasNext()){
            for(int i = 0; i < this.attributes.size(); i++){
                String ssss = s.next();
                Double d = Double.parseDouble(ssss);
                D.get(i).add(d);   
            }               
        }
        this.datas = D;
    }
    public ArrayList<ArrayList<Double>> getData(){
        return datas;
    }
    public ArrayList<String> getTypes(){
        return this.types;
    }
}
