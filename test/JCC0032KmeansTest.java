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
public class JCC0032KmeansTest {

    public JCC0032Kmeans test;
    public double[][] Table = {{1, 2, 3}, {4, 5, 6}, {9, 8, 7}};
    public double[] RowA = {1, 2, 3};
    public double[] RowB = {4, 5, 6};

    public JCC0032KmeansTest() {
//        test = new JCC0032Kmeans(Table, 1);
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of returnIndexOfSmallest method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnIndexOfSmallest() {
        System.out.println("returnIndexOfSmallest");
        double[] vector = {6.6, 7.7, 9.2, 5.3, 7.55, 10.2};
        int expResult = 3;
        int result = JCC0032Kmeans.returnIndexOfSmallest(vector);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnAverge method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnAverge() {
        System.out.println("returnAverge");
        double[] vector = {1.4, 2.6, 4.9, 132.56};
        double expResult = 35.365;
        double result = JCC0032Kmeans.returnAverge(vector);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of returnEuclideanDistance method, of class JCC0032Kmeans.
     */
    @Test
    public void testEuclideanDistance_doubleArr_doubleArr() {
        double known = Math.sqrt(27);
        double expct = JCC0032Kmeans.returnEuclideanDistance(RowA, RowB);
        StringBuilder msg = new StringBuilder();
        msg.append("Number expected: ")
                .append(known)
                .append(", Number returned: ")
                .append(expct);
        assertEquals(msg.toString(), expct, known, 0.001);

    }

    /**
     * Test of returnEuclideanDistance method, of class JCC0032Kmeans.
     */
    @Test
    public void testEuclideanDistance_doubleArr_doubleArrArr() {
        double[] known = new double[3];
        known[0] = 0.0;
        known[1] = Math.sqrt(27);
        known[2] = Math.sqrt(116);
        double[] expct = JCC0032Kmeans.returnEuclideanDistance(RowA, Table);
        StringBuilder msg = new StringBuilder();
        msg.append("Number expected: ")
                .append(known.toString())
                .append(", Number returned: ")
                .append(expct.toString());
        assertArrayEquals(msg.toString(), expct, known, 0);
    }

    /**
     * Test of returnClosestDistance method, of class JCC0032Kmeans.
     */
    @Test
    public void testClosestDistance() {
        System.out.println("closestDistance");
        double[][] tableA = {{1, 7, 9}, {3, 2, 6}, {4, 3, 3}, {9, 6, 3}, {2, 7, 4}};
        double[][] tableB = {{1, 7, 9}, {3, 5, 9}, {5, 5, 9}};
        int[] expResult = {0, 1, 1, 2, 0};
        int[] result = JCC0032Kmeans.returnClosestDistance(tableA, tableB);
        assertArrayEquals(expResult, result);
//        assertA
    }

    /**
     * Test of returnNewCentroids method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnNewCentroids() {
        System.out.println("returnNewCentroids");
        double[][] table = {{181, 103, 38}, {9, 147, 88}, {63, 106, 9}, {31, 79, 135}, {119, 112, 117}, {27, 58, 132}, {7, 7, 144}, {4, 106, 129}, {2, 49, 92}, {12, 192, 126}, {140, 23, 191}, {86, 30, 73}, {6, 25, 23}, {190, 88, 188}, {11, 4, 75}, {179, 161, 107}};
        int[] groups = {0, 0, 0, 1, 1, 2, 1, 0, 0, 2, 1, 1, 1, 0, 1, 1};
        int k = 3;
        double[][] expResult
                = {{74.83333333, 99.83333333, 90.66666667}, {72.375, 55.125, 108.125}, {19.5, 125, 129}};
        double[][] result = JCC0032Kmeans.returnNewCentroids(table, groups, k);
        for (int i = 0; i < k; i++) {
            assertArrayEquals(expResult[i], result[i], 0.001);
        }

    }

    /**
     * Test of returnArrayAdd method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnArrayAdd() {
        System.out.println("returnArrayAdd");
        double[] vectorA = {1, 2, 3, 4, 5, 6};
        double[] vectorB = {20, 12, 99, -5, 100, 0};
        double[] expResult = {21, 14, 102, -1, 105, 6};
        double[] result = JCC0032Kmeans.returnArrayAdd(vectorA, vectorB);
        assertArrayEquals(expResult, result, 0.001);
    }

    /**
     * Test of returnDiviedBy method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnDiviedBy() {
        System.out.println("returnDiviedBy");
        double[] vectorA = {20, 12, 99, -5, 100, 0};
        int denominator = 7;
        double[] expResult = {2.857142857, 1.714285714, 14.14285714, -0.714285714, 14.28571429, 0};
        double[] result = JCC0032Kmeans.returnDiviedBy(vectorA, denominator);
        assertArrayEquals(expResult, result, 0.001);
    }

    /**
     * Test of returnEuclideanDistance method, of class JCC0032Kmeans.
     */
    @Ignore
    public void testReturnEuclideanDistance_doubleArr_doubleArr() {
        System.out.println("returnEuclideanDistance");
        double[] rowA = null;
        double[] rowB = null;
        double expResult = 0.0;
        double result = JCC0032Kmeans.returnEuclideanDistance(rowA, rowB);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnEuclideanDistance method, of class JCC0032Kmeans.
     */
    @Ignore
    public void testReturnEuclideanDistance_doubleArr_doubleArrArr() {
        System.out.println("returnEuclideanDistance");
        double[] rowA = null;
        double[][] table = null;
        double[] expResult = null;
        double[] result = JCC0032Kmeans.returnEuclideanDistance(rowA, table);
        assertArrayEquals(expResult, result, 0.01);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnClosestDistance method, of class JCC0032Kmeans.
     */
    @Ignore
    public void testReturnClosestDistance() {
        System.out.println("returnClosestDistance");
        double[][] tableA = null;
        double[][] tableB = null;
        int[] expResult = null;
        int[] result = JCC0032Kmeans.returnClosestDistance(tableA, tableB);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnArraySame method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnArraySame() {
        System.out.println("returnArraySame");
        int[] vectorA = {1, 2, 3, 4, 5, 6};
        int[] vectorB = {1, 2, 3, 4, 5, 6};
        boolean expResult = true;
        boolean result = JCC0032Kmeans.returnArraySame(vectorA, vectorB);
        assertEquals(expResult, result);
    }



    /**
     * Test of returnIntial method, of class JCC0032Kmeans.
     */
    @Ignore
    public void testReturnIntial() {
        System.out.println("returnIntial");
        double[][] table = null;
        int k = 0;
        double[][] expResult = null;
        double[][] result = JCC0032Kmeans.returnIntial(table, k);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");

    }

    @Test
    public void testConstructor() {
        double[][] table = 
            {{ 2.000000 ,  4.000000},
            { 3.000000 ,  3.000000},
            { 3.000000 ,  4.000000},
            { 3.000000 ,  5.000000},
            { 4.000000 ,  3.000000},
            { 4.000000 ,  5.000000},
            { 9.000000 ,  4.000000},
            { 9.000000 ,  5.000000},
            { 9.000000 ,  9.000000},
            { 9.000000 , 10.000000},
            {10.000000 ,  4.000000},
            {10.000000 ,  5.000000},
            {10.000000 ,  9.000000},
            {10.000000 , 10.000000},
            {11.000000 , 10.000000},
            {15.000000 ,  4.000000},
            {15.000000 ,  5.000000},
            {15.000000 ,  6.000000},
            {16.000000 ,  4.000000},
            {16.000000 ,  5.000000},
            {16.000000 ,  6.000000}};
        JCC0032Kmeans testing = new JCC0032Kmeans(table, 4, true);
        int j = 5;
    }

    /**
     * Test of returnRandom2 method, of class JCC0032Kmeans.
     */
    @Test
    public void testReturnRandom2() {
        System.out.println("returnRandom2");
        double[][] table = 
                            {{ 2.000000 ,  4.000000},
            { 3.000000 ,  3.000000},
            { 3.000000 ,  4.000000},
            { 3.000000 ,  5.000000},
            { 4.000000 ,  3.000000},
            { 4.000000 ,  5.000000},
            { 9.000000 ,  4.000000},
            { 9.000000 ,  5.000000},
            { 9.000000 ,  9.000000},
            { 9.000000 , 10.000000},
            {10.000000 ,  4.000000},
            {10.000000 ,  5.000000},
            {10.000000 ,  9.000000},
            {10.000000 , 10.000000},
            {11.000000 , 10.000000},
            {15.000000 ,  4.000000},
            {15.000000 ,  5.000000},
            {15.000000 ,  6.000000},
            {16.000000 ,  4.000000},
            {16.000000 ,  5.000000},
            {16.000000 ,  6.000000}};
        int k = 5;
        double[][] expResult = null;
        double[][] result = JCC0032Kmeans.returnRandom2(table, k);
    }
}
