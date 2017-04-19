/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTEntropyBoxTest {
    
    public List ageValues = Arrays.asList(30, 30, 35, 40, 40, 40, 35, 30, 30, 40, 30, 35, 35, 40);
    public List ageDomain = Arrays.asList(30, 35, 40);
    public List groupingValues   = Arrays.asList(0,0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0);
    public List groupingDomain = Arrays.asList(0,1);
    
    public JCC0032DTEntropyBoxTest() {
    }
    
    @Before
    public void setUp() {
    }
    @Test
    public void testGetEntropy() {
        System.out.println("getEntropy");
        JCC0032DTEntropyBox instance = new JCC0032DTEntropyBox(ageDomain, groupingDomain);
        instance.insertAll(ageValues, groupingValues);
        Double expResult = 0.693536139;
        Double result = instance.getEntropy();
        assertEquals(expResult, result, 0.01);
    }
    
}
