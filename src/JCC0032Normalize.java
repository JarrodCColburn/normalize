// JCC0032Normalize.java
// Jarrod Colburn
// Due: 8 Feb 17

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JCC0032Normalize {
    public static void main(String[] args) throws FileNotFoundException, IOException  { /* TODO Remove Exceptions */
        String me = "JCC0032";
        String inFile = null;
        String inName = null;
        String classA = "";
        Path pIn;
        int classNum = -1;
        for(int i = 0; i < args.length; i++){
            if("-i".equals(args[i])){
                i++;
                pIn = Paths.get(args[i]);
                inFile = pIn.toString();                
                continue;
            }
            if("-c".equals(args[i])){
                i++;
                classA = args[i];
                continue;
            }
        }         
        JCC0032ARFF a = new JCC0032ARFF();
        a.setInFile(inFile);
        a.setAll();
        JCC0032SummaryChart s = new JCC0032SummaryChart();
        
        if(!classA.isEmpty()){
            for(int i = 0 ; i < a.getAttributes().size(); i++){
               if(classA.equals(a.getAttributes().get(i))){
                   s.setData(a.getData(), i);
                   break;
               }
           }    
        }
        else{
            s.setData(a.getData());
        }
        
        /*-------------*/
        pIn = Paths.get(inFile);
        inName = pIn.getFileName().toString();
        String file1 = me + "MeanStd" + inName;
        FileWriter MeanStd = new FileWriter(file1, true);
        PrintWriter printLine = new PrintWriter(MeanStd);
                
        printLine.print("@relation ");
        printLine.println(a.getRelation());
        printLine.println();
        
        for(int i = 0 ; i < a.getAttributes().size() ; i++){
            printLine.print("@relation " + a.getAttributes().get(i));
            printLine.println(" " + a.getTypes().get(i));
        }
        printLine.println();
        
        printLine.println("@data");
        for(int i = 0 ; i < s.getMeans().size(); i++){
            printLine.printf("%10.6f ", s.getMeans().get(i));
        }
        printLine.println();
        for(int i = 0 ; i < s.getStdDevs().size(); i++){
            printLine.printf("%10.6f ", s.getStdDevs().get(i));
        }            
        printLine.close();
        
        
        /*-------------*/
        
        String file2 = me + "Normalize" + inName;
    
        FileWriter Normal = new FileWriter(file2, true);
        PrintWriter printLine2 = new PrintWriter(Normal);
        
        printLine2.print("@relation ");
        printLine2.println(a.getRelation());
        printLine2.println();
        
        for(int i = 0 ; i < a.getAttributes().size() ; i++){
            printLine2.print("@relation " + a.getAttributes().get(i));
            printLine2.println(" " + a.getTypes().get(i));
        }
        printLine2.println();
        for(int i = 0 ; i < s.getNorms().get(0).size(); i++){
            for(int j = 0; j < s.getNorms().size() ; j++){
                printLine2.printf("%10.6f ",s.getNorms().get(j).get(i));
            }            
            printLine2.println();
        }                
        printLine2.close();
    }
}
