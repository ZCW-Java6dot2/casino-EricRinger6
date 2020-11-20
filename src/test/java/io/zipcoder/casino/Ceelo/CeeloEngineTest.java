package io.zipcoder.casino.Ceelo;

import io.zipcoder.casino.ceelo.CeeloEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStream;

public class CeeloEngineTest {
    CeeloEngine ceelo = new CeeloEngine();
    ArrayList<Integer> playerList = new ArrayList<Integer>();
    ArrayList<Integer> compList = new ArrayList<Integer>();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void determineWinner1() {
        String expected = "You lose!";
        Integer comboType = 0;

        String actual = ceelo.determineWinner(comboType);

        assertEquals(expected,actual);
    }

    @Test
    public void determineWinner2() {
        String expected = "YOU WIN!";
        Integer comboType = 1;

        String actual = ceelo.determineWinner(comboType);

        assertEquals(expected,actual);
    }

    @Test
    public void checkResult() {
        Integer[] playerArr = {3,3,3};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        Integer whoseturn = 0;
        boolean expected = false;

        boolean actual = ceelo.checkResult(playerList,whoseturn);

        assertEquals(expected,actual);
    }

    @Test
    public void testCompareToss1() {
        Integer[] playerArr = {3,3,3};
        Integer[] compArr = {3,3,1};
        Integer[] compare = {2,1};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        compList = new ArrayList<Integer>(Arrays.asList(compArr));
        Integer expected = 0;

        Integer actual = ceelo.compareToss(compare,playerList,compList);

        assertEquals(expected,actual);
    }

    @Test
    public void testCompareToss2() {
        Integer[] playerArr = {3,3,1};
        Integer[] compArr = {4,4,4};
        Integer[] compare = {2,1};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        compList = new ArrayList<Integer>(Arrays.asList(compArr));
        Integer expected = 0;

        Integer actual = ceelo.compareToss(compare,playerList,compList);

        assertEquals(expected,actual);
    }

    @Test
    public void testComparePoints1() {
        Integer[] playerArr = {2,2,3};
        Integer[] compArr = {3,3,1};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        compList = new ArrayList<Integer>(Arrays.asList(compArr));
        Integer expected = 0;

        Integer actual = ceelo.comparePoints(playerList,compList);

        assertEquals(expected,actual);
    }

    @Test
    public void testComparePoints2() {
        Integer[] playerArr = {2,2,3};
        Integer[] compArr = {3,3,5};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        compList = new ArrayList<Integer>(Arrays.asList(compArr));
        Integer expected = 1;

        Integer actual = ceelo.comparePoints(playerList,compList);

        assertEquals(expected,actual);
    }

    @Test
    public void testCompareTriples() {
        Integer[] playerArr = {2,2,2};
        Integer[] compArr = {3,3,3};
        playerList = new ArrayList<Integer>(Arrays.asList(playerArr));
        compList = new ArrayList<Integer>(Arrays.asList(compArr));
        Integer expected = 1;

        Integer actual = ceelo.compareTriples(playerList,compList);

        assertEquals(expected,actual);
    }
}
