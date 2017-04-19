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
public class JCC0032StringBuilderHelperTest {

    public List l1 = new ArrayList(Arrays.asList("One", "Two", "Three", "Four","Five"));
    public List l2 = new ArrayList(Arrays.asList("Six", "Seven", "Eight","Nine","Ten"));
    public List l3 = new ArrayList(Arrays.asList("Eleven","Twelve","Thirteen","Fourteen","Fifteen"));
    public List l4 = new ArrayList(Arrays.asList("Sixteen", "Seventeen", "Eighteen","Nineteen","Twenty"));
    
    public List ls = new ArrayList(Arrays.asList(l1,l2));
    
    
    public List a1 = new ArrayList(Arrays.asList("A", "B", "C", "D", "E"));
    public List a1sub = a1.subList(0, 3);
    
    
    public JCC0032StringBuilderHelperTest() {
    }

    @Before
    public void setUp() {
    }

        /**
     * Test of toBuilder method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testToBuilder_nested() {
        String expResult = "One Two Three Four Five\nSix Seven Eight Nine Ten\n";
        String result = JCC0032DTStringBuilderHelper.toBuilder("%s", ls).toString();
        assertEquals(expResult, result);

    }
    
        /**
     * Test of toBuilder method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testToBuilder_nested_appended() {
        String expResult = "One Two Three Four Five A\nSix Seven Eight Nine Ten B\n";
        String result = JCC0032DTStringBuilderHelper.toBuilder("%s", ls, a1sub).toString();
        assertEquals(expResult, result);

    }
        /**
     * Test of toBuilder method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testToBuilder_nested_appended_prefixed() {
        String expResult = "@attribute One Two Three Four Five A\n@attribute Six Seven Eight Nine Ten B\n";
        String result = JCC0032DTStringBuilderHelper.toBuilder("%s", "@attribute", ls, a1sub).toString();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of toBuilder method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testToBuilder() {
        String expResult = "One\nTwo\nThree\nFour\nFive\n";
        String result = JCC0032DTStringBuilderHelper.toBuilder("%s", l1).toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of toBuilder method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testToBuilder_withTwoColumns() {
        String expResult = "One A\nTwo B\nThree C\nFour D\nFive E\n";
        String result = JCC0032DTStringBuilderHelper.toBuilder("%s", l1, a1).toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of row method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testRow() {
        String expResult = "One Two Three Four Five";
        String result = JCC0032DTStringBuilderHelper.row("%s", l1, false).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of row method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testRow_String_List() {
    }

    /**
     * Test of row method, of class JCC0032DTStringBuilderHelper.
     */
    @Test
    public void testRow_3args() {
    }
    

}
