// JCC0032SummaryChart.java
// Jarrod Colburn
// Due: 8 Feb 17
package normalize;
import normalize.JCC0032SummaryList;
import java.util.ArrayList;
public class JCC0032SummaryChart {
    
    private ArrayList<JCC0032SummaryList> data;
    
    public JCC0032SummaryChart(){
        data = new ArrayList<>();
    }
    public void setData(ArrayList<ArrayList<Double>> data){
        setData(data, -1);
    }
    public void setData(ArrayList<ArrayList<Double>> data, int classA){
        ArrayList<JCC0032SummaryList> tmp = new ArrayList<>();
        for(int i = 0; i < data.size() ; i++) {
            if(classA != i){
                tmp.add(new JCC0032SummaryList(data.get(i)));
            }
            else{
                continue;
            }
        }
        this.data = tmp;
    }
    public ArrayList<ArrayList<Double>> getNorms(){
        ArrayList<ArrayList<Double>> tmp = new ArrayList<>();
        for(int i = 0; i < data.size() ; i++) {
            tmp.add(data.get(i).getNormalized());
        }
        return tmp;
    }
    public ArrayList<Double> getMeans(){
        ArrayList<Double> tmp = new ArrayList<>();
        for(int i = 0; i < data.size() ; i++) {
            tmp.add(data.get(i).getMean());
        }
        return tmp;
    }
    public ArrayList<Double> getStdDevs(){
        ArrayList<Double> tmp = new ArrayList<>();
        for(int i = 0; i < data.size() ; i++) {
            tmp.add(data.get(i).getStdDev());
        }
        return tmp;
    }
}
