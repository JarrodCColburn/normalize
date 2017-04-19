/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarrodcolburn
 */
public class MappingTest {
    
    public MappingTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of append method, of class Mapping.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        Integer K = 5;
        Mapping instance = new Mapping();
        instance.append(K, 1);
        instance.append(K, 2);
        instance.append(K, 3);
        instance.append(K, 4);
        instance.append(K, 5);
        instance.append(K, 6);
    }
    
}
