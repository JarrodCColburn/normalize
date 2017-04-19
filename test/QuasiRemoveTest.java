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
public class QuasiRemoveTest {
    
    
    public QuasiRemoveTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of quasiRemove method, of class QuasiRemove.
     */
    @Test
    public void testQuasiRemove() {
        List instance = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9));
        List tmp;
        tmp = QuasiRemove.quasiRemove(instance, 3);
        tmp = QuasiRemove.quasiRemove(instance, 3);
        tmp = QuasiRemove.quasiRemove(instance, 3);
        tmp = QuasiRemove.quasiRemove(instance, 3);
        tmp = QuasiRemove.quasiRemove(instance, 3);
        tmp = QuasiRemove.quasiRemove(instance, 3);
    }
    
}
