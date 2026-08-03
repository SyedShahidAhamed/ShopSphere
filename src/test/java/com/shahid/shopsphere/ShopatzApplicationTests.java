package com.shahid.shopsphere;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ShopatzApplicationTests {

    @BeforeAll
    public static void testStarts() {
        System.out.println("test starting..........");
    }

    @AfterAll
    public static void doneTest() {
        System.out.println("testing done!");
    }

    @BeforeEach
    public void eachTest() {
        System.out.println("before each test..");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("After each test..");
    }

    @Test
    public void addTest() {
        assertEquals(4, 2 + 2);
        assertTrue(4 > 2);
        assertFalse(2 > 4);
    }

    @Test
    public void nullTest() {
        String token = null;
        assertNull(token);
    }
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void isPositve(int a)
    {
        assertTrue(a>0,"failed num:"+a);
            
    }
    @ParameterizedTest
    @CsvSource({
        "2,3,5",
        "4,5,6"
    })
    @RepeatedTest(5)
void testMultipleTimes() {
    assertTrue(true);
}
    public void checkCond(int a,int b,int expected){
        assertEquals(expected, a+b,"failed pair:"+a+","+b+"!="+expected);
    }
    public int divide(int a,int b) { return a/b; } 
    @Disabled @Test public void ThrowTest(){
         assertThrows(ArithmeticException.class, ()-> {divide(10, 5);}); 
        }
}