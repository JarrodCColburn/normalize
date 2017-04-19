/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTcmdLineTest {
    
    JCC0032DTcmdLine c;
    
    public JCC0032DTcmdLineTest() {
        String []  str = {"-i", "kmtest.arff", "-K", "5","-c","class","-normalize"};
        this.c = new JCC0032DTcmdLine(str);
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of isNormalize method, of class JCC0032DTcmdLine.
     */
    @Test
    public void testIsNormalize() {
        System.out.println("isNormalize");
        JCC0032DTcmdLine instance = c;
        boolean expResult = true;
        boolean result = instance.isNormalize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassAttribute method, of class JCC0032DTcmdLine.
     */
    @Test
    public void testGetClassAttribute() {
        System.out.println("getClassAttribute");
        JCC0032DTcmdLine instance = c;
        String expResult = "class";
        String result = instance.getClassAttribute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileName method, of class JCC0032DTcmdLine.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        JCC0032DTcmdLine instance = c;
        String expResult = "kmtest.arff";
        String result = instance.getFileName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getK method, of class JCC0032DTcmdLine.
     */
    @Test
    public void testGetK() {
        System.out.println("getK");
        JCC0032DTcmdLine instance = c;
        int expResult = 5;
        int result = instance.getT();
        assertEquals(expResult, result);
    }    
}
