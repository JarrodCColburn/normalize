/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTinARFFTest {
        public JCC0032DTinARFF instance = null;
    
    public JCC0032DTinARFFTest() {
    }
    
    @Before
    public void setUp() {
        try {
            instance = new JCC0032DTinARFF("bcwdisc.arff","class");
        } catch (FileNotFoundException ex) {
            fail("Threw Error in Constructor");
        }
    }

    /**
     * Test of toString method, of class JCC0032DTinARFF.
     */
    @Test
    public void testToString() {
        String ans = instance.toString();
        String an2 = instance.toString();
        
    }
    
}
