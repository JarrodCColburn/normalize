// JCC0032DTcmdLine.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Parse cmd line arguments
 * @author jarrodcolburn
 */
public class JCC0032DTcmdLine {

    private String classAttribute ="";
    private String fileName ="";
    private double T;
    private int K;

    public JCC0032DTcmdLine(String[] args) {      
        for (int i = 0; i < args.length; i++) {
            if (args[i].trim().equals("-i".trim())) {             
                i++;
                this.fileName = Paths.get(args[i]).toString();
                continue;
            }
            if (args[i].trim().equals("-T".trim())) {
                i++;
                this.T = Double.parseDouble(args[i]);
                continue;
            }
            if (args[i].trim().equals("-c".trim())) {
                i++;
                this.classAttribute = args[i];
                continue;
            }
            if (args[i].trim().equals("-K".trim())) {
                i++;
                this.K = Integer.parseInt(args[i]);
                continue;
            }            
        }
    }

    /**
     * @return the classAttribute
     */
    public String getClassAttribute() {
        return this.classAttribute;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @return the T
     */
    public double getT() {
        return this.T;
    }

    /**
     * @return the K
     */
    public int getK() {
        return this.K;
    }

}
