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
public class JCC0032NormalizeTest {
    
    public JCC0032NormalizeTest() {
    }
    
    @Before
    public void setUp() {
    }

    
    /**
     * Test of returnAverage method, of class JCC0032Normalize.
     */
    @Test
    public void testReturnAverage() {
        System.out.println("returnAverage");
        double[][] table ={
            {172,111,91,184},
            {61,33,41,167},
            {122,74,131,196},
            {133,94,199,65},
            {184,198,120,31},
            {140,74,174,31},
            {98,21,33,193},
            {158,43,84,30},
            {180,91,79,48},
            {161,6,3,154},
            {91,144,182,110},
            {115,162,140,81},
            {89,62,196,167},
            {85,190,191,118},};
        double[] expResult = {127.7857143, 93.07142857, 118.8571429, 112.5};
        double[] result = JCC0032Normalize.returnAverage(table);
        assertArrayEquals(expResult, result, 0.01);
    }



    /**
     * Test of returnStdDev method, of class JCC0032Normalize.
     */
    @Test
    public void testReturnStdDev() {
        System.out.println("returnStdDev");
        double[][] table ={
            {172,111,91,184},
            {61,33,41,167},
            {122,74,131,196},
            {133,94,199,65},
            {184,198,120,31},
            {140,74,174,31},
            {98,21,33,193},
            {158,43,84,30},
            {180,91,79,48},
            {161,6,3,154},
            {91,144,182,110},
            {115,162,140,81},
            {89,62,196,167},
            {85,190,191,118},};
        double[] means = {127.7857143, 93.07142857, 118.8571429, 112.5};
        double[] expResult = {38.04729871, 58.95575131, 63.04064125,61.96975069};
        double[] result = JCC0032Normalize.returnStdDev(table, means);
        assertArrayEquals(expResult, result, 0.01);
    }

    /**
     * Test of returnNormalized method, of class JCC0032Normalize.
     */
    @Test
    public void testReturnNormalized() {
        System.out.println("returnNormalized");
        double[][] table ={
            {172,111,91,184},
            {61,33,41,167},
            {122,74,131,196},
            {133,94,199,65},
            {184,198,120,31},
            {140,74,174,31},
            {98,21,33,193},
            {158,43,84,30},
            {180,91,79,48},
            {161,6,3,154},
            {91,144,182,110},
            {115,162,140,81},
            {89,62,196,167},
            {85,190,191,118},};
        double[] means = {127.7857143, 93.07142857, 118.8571429, 112.5};
        double[] stdDevs = {38.04729871, 58.95575131, 63.04064125,61.96975069};
        double[][] expResult = 
            {{1.162087381,0.304102162,-0.441891807,1.153788731}
            ,{-1.755333928,-1.018923977,-1.235030947,0.87946134}
            ,{-0.152066362,-0.32348716,0.192619505,1.347431595}
            ,{0.137047462,0.015750311,1.271288736,-0.766503003}
            ,{1.477484279,1.779785163,0.018128895,-1.315157784}
            ,{0.321028986,-0.32348716,0.874719166,-1.315157784}
            ,{-0.782860158,-1.22246646,-1.361933209,1.299020879}
            ,{0.794124333,-0.849305241,-0.552931286,-1.33129469}
            ,{1.37235198,-0.03513531,-0.6322452,-1.040830394}
            ,{0.872973558,-1.476894563,-1.837816694,0.669681571}
            ,{-0.966841682,0.86384399,1.001621429,-0.040342263}
            ,{-0.336047886,1.169157714,0.335384551,-0.508312518}
            ,{-1.019407832,-0.527029643,1.223700388,0.87946134}
            ,{-1.124540131,1.644090174,1.144386474,0.088752979}};       
        double[][] result = JCC0032Normalize.returnNormalized(table, means, stdDevs);
        for(int i=0; i < table.length; i++){
            assertArrayEquals(expResult[i], result[i], 0.001);
        }
    }
    
}
