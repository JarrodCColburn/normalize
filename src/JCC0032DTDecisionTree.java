// JCC0032DTDecisionTree.java
// Jarrod Colburn
// Due: 3 Apr 17

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class JCC0032DTDecisionTree {

    JCC0032DTNode root;

    JCC0032DTDecisionTree() {
    }

    /**
     * Sets root node
     *
     * @param n
     */
    public void setRoot(JCC0032DTNode n) {
        this.root = n;
    }

    /**
     * Begins growing of tree beginning at the root
     */
    public void growTree() {
        growTree(this.root);
    }

    /**
     * Recursive growing of tree
     *
     * @param n
     */
    public void growTree(JCC0032DTNode n) {
        JCC0032DTRecordSet recordSets = (JCC0032DTRecordSet) n.getContents();
        List<JCC0032DTRecordSet> subRecords = recordSets.makeSplits();
        for (JCC0032DTRecordSet r : subRecords) {
            if (r.getSize() < 1) {
                continue; // node has no records, don't add to tree
            }
            JCC0032DTNode c = new JCC0032DTNode();
            r.setGroup(r.singleGroup()); // If a group dominates set it 
            if (r.getWidth() == 0) { // All attributes are exhausted...
                r.setGroup(r.dG()); // ...so assign node to majotiry group
            }
            c.setContents(r);
            if (r.getGroup() == null) {
                growTree(c); // Build tree recursively
            }
            n.addChildren(c);
        }
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        printTree(this.root, 0, str);
        return str.toString().trim();
    }

    /**
     * Recursive method used to print nodes by depth
     *
     * @param n
     * @param depth
     * @param str
     */
    private void printTree(JCC0032DTNode n, int depth, StringBuffer str) {
        if (n.toString() == null || n.toString() == "") {
        } else {
            for (int i = 0; i < depth; i++) {
                str.append("| ");
            }
            str.append(n.toString());
            str.append("\n");
            depth++;
        }
        for (JCC0032DTNode c : n.getChildren()) {
            printTree(c, depth, str);
        }
    }

    /**
     * Using the tree, attempts to predict correct grouping
     * 
     * @param record columns of data
     * @param attributes attributes those columns track to
     * @return
     */
    public Object returnGroup(List record, List attributes) {
        return returnGroup(record, attributes, this.root);
    }

    private Object returnGroup(List record, List attributes, JCC0032DTNode n) {
        JCC0032DTRecordSet r = (JCC0032DTRecordSet) n.getContents();
        if (r.getGroup() != null) { // At leaf so return grouping
            return r.getGroup();
        } else {
            int globalMaxRecords = 0;
            JCC0032DTNode largestSubNode = n.getChildren().get(0);
            for (JCC0032DTNode c : n.getChildren()) { // Find matching child
                JCC0032DTRecordSet child = (JCC0032DTRecordSet) c.getContents();
                Object attribute = child.getAttribute(); // Get attribute
                int i = 0;//= Arrays.asList(attributes).indexOf(attribute);
                for (; i < attributes.size(); i++) {
                    if (attributes.get(i).equals(attribute)) {
                        break;
                    }
                }
                if (child.getValue().equals(record.get(i))) { // Compare record attribute to node Attribute
                    return returnGroup(record, attributes, c);  // Recursive traverse children
                } else { // Traverse node with most subrecords
                    int localMaxRecords = child.getSize();
                    if (localMaxRecords > globalMaxRecords) {
                        globalMaxRecords = localMaxRecords;
                        largestSubNode = c;
                    }
                }
            }
            // No branch in tree matching, search largest branch
            return returnGroup(record, attributes, largestSubNode);
        }
    }
}
