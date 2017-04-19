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
public class JCC0032DTRecordSetTest {

    public List a0 = new ArrayList(Arrays.asList(40, 1, 1, 9));
    public List a1 = new ArrayList(Arrays.asList(30, 1, 1, 5));
    public List a2 = new ArrayList(Arrays.asList(35, 1, 1, 9));
    public List a3 = new ArrayList(Arrays.asList(40, 1, 1, 5));
    public List a4 = new ArrayList(Arrays.asList(30, 2, 0, 5));
    public List a5 = new ArrayList(Arrays.asList(40, 2, 0, 9));
    public List a6 = new ArrayList(Arrays.asList(30, 2, 1, 9));
    public List a7 = new ArrayList(Arrays.asList(35, 2, 0, 9));
    public List a8 = new ArrayList(Arrays.asList(40, 2, 0, 5));
    public List a9 = new ArrayList(Arrays.asList(40, 2, 1, 5));
    public List aA = new ArrayList(Arrays.asList(30, 3, 0, 5));
    public List aB = new ArrayList(Arrays.asList(30, 3, 0, 9));
    public List aC = new ArrayList(Arrays.asList(35, 3, 0, 5));
    public List aD = new ArrayList(Arrays.asList(35, 3, 1, 5));
    public List<List> records = Arrays.asList(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, aA, aB, aC, aD);
    
    public List age = Arrays.asList(30, 35, 40);
    public List income = Arrays.asList(1, 2, 3);
    public List student = Arrays.asList(0, 1);
    public List credit = Arrays.asList(5, 9);
    public List<List> recordDomain = new ArrayList(Arrays.asList(age, income, student, credit));

    public List grouping = new ArrayList(Arrays.asList(0,1,1,1,0,0,1,1,1,1,0,0,1,1));
    public List groupDomain =  Arrays.asList(0,1);
    public List attributes = new ArrayList(Arrays.asList("age","income","student","credit"));

    public JCC0032DTRecordSetTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of setDomains method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testSetDomains() {
        JCC0032DTRecordSet instance = new JCC0032DTRecordSet();
        instance.setDomains(recordDomain, groupDomain);
        assertEquals(instance.getDomain(0).get(0), 30);
    }

    /**
     * Test of addRecord method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testAddRecord() {
    }

    /**
     * Test of setLabel method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testSetLabel() {
        JCC0032DTRecordSet instance = new JCC0032DTRecordSet();
        instance.setLabel("asdf", 2);
        assertEquals(instance.getValue(),2);
    }

    /**
     * Test of setAttributes method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testSetAttributes() {
    }

    /**
     * Test of getValue method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testGetValue() {
    }

    /**
     * Test of decideSplit method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testDecideSplit() {
        int expected = 0;        
        int result = JCC0032DTRecordSet.decideSplit(records, recordDomain,  grouping,  groupDomain);
        assertEquals(expected, result);
    }

    /**
     * Test of makeSplits method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testMakeSplits() {
        List r = JCC0032DTRecordSet.makeSplits(records, recordDomain, grouping, groupDomain, attributes);
    }

    /**
     * Test of getSize method, of class JCC0032DTRecordSet.
     */
    @Test
    public void testGetSize() {
    }
    @Test
    public void testSinglGroup(){
        JCC0032DTRecordSet instance = new JCC0032DTRecordSet();
        instance.setRecords(records, grouping);
        Object result = instance.singleGroup();
        Object expResult = null;
        assertEquals(expResult, result);
    }
}
