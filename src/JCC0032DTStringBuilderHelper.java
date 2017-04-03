// JCC0032DTStringBuilderHelper.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTStringBuilderHelper {

    public static StringBuilder toBuilder(String formatting, List... l) {
        return toBuilder(formatting, null, l);
    }

    public static StringBuilder toBuilder(String formatting, String prefix, List... l) {
        int rows = l[0].size();
        int columns = l.length;
        String s = " ";
        String br = "\n";
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < rows; j++) {
            // handle element at 0 index
            if (prefix != null) {  // Contains a prefix
                sb.append(prefix).append(s);
            }
            if (l[0].get(j) instanceof List) { // contains a nested list
                sb.append(row(formatting, (List) l[0].get(j)));
            } else { // not a nested list
                sb.append(String.format(formatting, l[0].get(j)));
            }

            // handle elements > 0 index. Preceded by padding.
            for (int i = 1; i < columns; i++) {
                sb.append(s);
                sb.append(String.format(formatting, l[i].get(j)));
            }
            sb.append(br);
        }
        return sb;
    }

    public static StringBuilder row(String foramtting, List l) {
        return row(foramtting, l, false);
    }

    public static StringBuilder row(String formatting, List l, boolean ln) {
        String s = " ";
        int columns = l.size();
        StringBuilder sb = new StringBuilder();
        if (columns > 0) {
            sb.append(String.format(formatting, l.get(0)));
        }
        for (int j = 1; j < columns; j++) {
            sb.append(s);
            sb.append(String.format(formatting, l.get(j)));
        }
        if (ln == true) {
            sb.append("\n");
        }
        return sb;
    }

    public static StringBuilder table(int min, List l) {
        String br = "\n";
        String p = "+";  // plus
        String h = "-"; //horizontal
        String v = "|";
        String s = " ";
        StringBuilder sb = new StringBuilder();
        sb.append(sb);

        return sb;
    }
}
