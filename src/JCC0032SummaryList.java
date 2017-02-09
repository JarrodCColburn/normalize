// JCC0032SummaryList.java
// Jarrod Colburn
// Due: 8 Feb 17
package normalize ;
import java.util.ArrayList;

/**
 *
 * @author jarrodcolburn
 * @param 
 */
public class JCC0032SummaryList{
    private ArrayList<Double> data;
    private int count;
    private Double sum;
    private Double mean;
    private Double stdDev;
    
//    public JCC0032SummaryList(){
//    }

    public JCC0032SummaryList(ArrayList<Double> data){
        this.initialize(data);
    }
    private void initialize(ArrayList<Double> data){
        setData(data);
        setCount();
        setSum();
        setMean();
        setStdDev();
    }
    private ArrayList<Double> returnData(ArrayList<Double> data){
        ArrayList<Double> tmpData = new ArrayList<>();
        for( int i = 0; i < data.size(); i++){
            if(data.get(i) != null){
                tmpData.add(data.get(i)); 
            }
        }
        return tmpData;
    }
    private void setData(ArrayList<Double> data){
        this.data = returnData(data);
    }
    
    
    
    private void setCount(){
        this.count = data.size();
    }
    public int getCount(){
        return this.count; 
    }
    
    
    
    private Double returnSum(ArrayList<Double> data){
        if (data.size() <= 0) {
            return null;
        }
        else {
            Double s = 0.0;
            for(int i = 0; i < data.size(); i++){
                s += data.get(i);
            }
            return 1.0 * s;
        }
    }
    private void setSum() {
        this.sum = returnSum(this.data);
    }
    public Double getSum(){
        return 1.0 * this.sum;
    }
    
    
    
    private Double returnMean(ArrayList<Double> data){
        return returnSum(data) / data.size();
    }
    private void setMean(){
        this.mean = returnMean(this.data);
    }
    public Double getMean(){
        return 1.0 * mean;
    }
    
    
    
    
    private Double returnStdDev(ArrayList<Double> data, Double avg){
        if(data.size() <= 0){
            return null;
        }
        else {
            Double s = 0.0;
            if (avg == null) {
                avg = returnMean(data);
            }            
            for(int i = 0; i < data.size(); i++){
                s += Math.pow((data.get(i) - avg), 2.0);
            }
            return Math.sqrt(s / (1.0 * data.size()));  
        }
    }  
    private Double returnStdDev(ArrayList<Double> data){
        return returnStdDev(data, null);
    }
    private void setStdDev(){
        this.stdDev = returnStdDev(this.data, this.mean);
    }
    public Double getStdDev(){
        return 1.0 * this.stdDev;
    }
    
    
    public Double getNormalized(Double d) {
        return (d - this.mean) / this.stdDev;
    }
    public ArrayList<Double> getNormalized(ArrayList<Double> data){
        ArrayList<Double> tmpData = new ArrayList<>();   
        for( int i = 0; i < data.size(); i++){
                tmpData.add(this.getNormalized(data.get(i)));             
        }
        return tmpData;
    }
    public ArrayList<Double> getNormalized(){
        ArrayList<Double> tmpData = new ArrayList<>();   
        for( int i = 0; i < this.data.size(); i++){
                tmpData.add(this.getNormalized(this.data.get(i)));             
        }
        return tmpData;
    }
}
