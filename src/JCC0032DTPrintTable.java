// JCC0032DTPrintTable.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.List;

public class JCC0032DTPrintTable {

    private String xTitle;
    private List xLabels;
    private String yTitle;
    private List yLabels;
    private List<List> records;

    private int xT;
    private int yT;
    private int yL;
    private int[] xLs;
    private int rows;
    private int columns;

    // String format to be used
    private String sf;

    // Common used items
    private char br = '\n';
    private char p = '+';  // plus
    private char h = '-';  // horizontal
    private char v = '|';  // vertical
    private char s = ' ';  // space
    private char z = '\0';

    private int lPad;
    private int rPad;

    public JCC0032DTPrintTable() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // TODO add lines for xTitle
        if (this.xTitle != null) {
            sb.append(lines());
            sb.append(rows(null,null,this.xTitle));
        }

        if (this.xLabels != null) {
            sb.append(lines());
            sb.append(rows(null, null, this.xLabels));
        }

        // TODO add lines for xLabels
        int i = 0;
        sb.append(lines());
        if (yLabels != null) {
            sb.append(rows(yTitle, yLabels.get(i).toString(), records.get(i)));
            sb.append(lines());
            for (i = 1; i < columns; i++) {
                sb.append(rows(null, yLabels.get(i).toString(), records.get(i)));
                sb.append(lines());
            }
        }
        return sb.toString();
    }

    public void setData(String xTitle, List xLabels, String yTitle, List yLabels, List<List> records) {
        // Setters
        this.xTitle = xTitle;
        this.xLabels = xLabels;
        this.yTitle = yTitle;
        this.yLabels = yLabels;
        this.records = records;

        // Assign lengths
        setXT();
        setYT();
        setXLs();
        setYL();
        this.rows = yLabels.size();
        this.columns = xLabels.size();
        this.lPad =1; //default
    }

    /**
     *
     * @param len length of repeat
     * @param c repeating item
     * @param d divider character
     * @return
     */
    private String repeat(int len, char c, char d) {
        if (len == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append(c);
            }
            sb.append(d);
            return sb.toString();
        }
    }

    private String rows(String one, String two, List list) {
        StringBuilder sb = new StringBuilder();
        sb.append(rows(one, two));
        sb.append(rows(list));
        sb.append(br);
        return sb.toString();
    }

    private String rows(String one, String two) {
        StringBuilder sb = new StringBuilder();
        one = (one != null) ? one : "";
        two = (two != null) ? two : "";
        sb.append(v);
        sb.append(String.format(right(this.yT), one));
        if (this.yT != 0) {
            if (one == "" && two == "") {
                sb.append(s);
            } else {
                sb.append(v);
            }
        }
        sb.append(String.format(right(this.yL), two));
        if (this.yL != 0) {
            sb.append(v);
        }
        return sb.toString();
    }

    private String rows(String one, String two, String three) {
        StringBuilder sb = new StringBuilder();
        sb.append(rows(one, two));
        int width = 1;
        for(int x: xLs){
            width += x + lPad;
        }
        sb.append(String.format(left(Integer.max(width, this.xT)), three));
        sb.append(v);
        sb.append(br);
        return sb.toString();
    }

    private String rows(List list) {
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for (Object l : list) {
            sb.append(String.format(right(this.xLs[j]), l.toString()));
            sb.append(v);
        }
        return sb.toString();
    }

    private String lines() {
        StringBuilder sb = new StringBuilder();
        sb.append(p);
        sb.append(repeat(this.lPad + this.yT, h, p));
        sb.append(repeat(this.lPad + this.yL, h, p));
        for (int x : this.xLs) {
            sb.append(repeat(this.lPad + x, h, p));
        }
        sb.append(br);
        return sb.toString();
    }

    private void setXT() {
        this.xT = (this.xTitle != null) ? this.xTitle.length() : 0;
    }

    private void setYT() {
        this.yT = (yTitle != null) ? yTitle.length() : 0;
    }

    private void setXLs() {
        if (xLabels != null) {
            this.xLs = new int[xLabels.size()];
            int j = 0;
            for (Object s : xLabels) {
                this.xLs[j] = s.toString().length();
                j++;
            }
        } else {
            this.xLs = new int[0];
        }
        if (records != null) {
            for (List r : this.records) {
                int j = 0;
                for (Object e : r) {
                    this.xLs[j] = Integer.max(this.xLs[j], e.toString().length());
                }
            }

        } else {
            // if here, no records were provided. So why table?
        }
    }

    private void setYL() {
        if (this.yLabels != null) {
            for (Object s : this.yLabels) {
                this.yL = Integer.max(this.yL, s.toString().length());
            }
        } else {
            this.yL = 0;
        }
    }

    private String right(int i) {
        return "%" + (this.lPad + i) + "s";
    }
    private String left(int i){
        return "%-" + (this.lPad + i) + "s";
    }
}
