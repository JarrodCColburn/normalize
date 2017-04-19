/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarrodcolburn
 */
public class HelperTest {
    
    public HelperTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of transpose method, of class JCC0032Helper.
     */
    @Test
    public void testTranspose() {
        System.out.println("transpose");
        ArrayList<ArrayList<Double>> matrixIn = new ArrayList<>();
        matrixIn.add(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)));
        matrixIn.add(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)));
        matrixIn.add(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0)));      
        ArrayList<ArrayList<Double>> expResult = new ArrayList<>();
        expResult.add(new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0)));
        expResult.add(new ArrayList<>(Arrays.asList(2.0, 2.0, 2.0)));
        expResult.add(new ArrayList<>(Arrays.asList(3.0, 3.0, 3.0)));
        expResult.add(new ArrayList<>(Arrays.asList(4.0, 4.0, 4.0)));
        expResult.add(new ArrayList<>(Arrays.asList(5.0, 5.0, 5.0)));
        ArrayList<ArrayList<Double>> result = JCC0032Helper.transpose(matrixIn);
        assertEquals(expResult, result);
    }
    
}
