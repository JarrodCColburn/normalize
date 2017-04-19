/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarrodcolburn
 */
public class PrintTableTest {
    
    public JCC0032DTPrintTable pt;
    public List r1 = new ArrayList(Arrays.asList(1,2,3));
    public List r2 = new ArrayList(Arrays.asList(4,5,6));
    public List r3 = new ArrayList(Arrays.asList(7,8,9));
    public List rS = new ArrayList(Arrays.asList(r1,r2,r3));
    
    public List rh = new ArrayList(Arrays.asList("A", "B", "C"));
    public List ch = new ArrayList(Arrays.asList("a", "b", "c"));
    
    
    public PrintTableTest() {
    }
    
    @Before
    public void setUp() {
       pt = new JCC0032DTPrintTable();
       pt.setData("Row", rh, "Column", ch, rS);
       String str = pt.toString();
    }

    /**
     * Test of toString method, of class JCC0032DTPrintTable.
     */
    @Test
    public void testToString() {
        
    }

    /**
     * Test of setData method, of class JCC0032DTPrintTable.
     */
    @Test
    public void testSetData() {
    }


}
