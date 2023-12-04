package sg.edu.ntu.movie_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.ntu.movie_api.services.DemoService;

public class DemoServiceTest {
    
    DemoService demoService;

    @BeforeEach
    public void init() {
        System.out.println("Before each test");
        demoService = new DemoService();
    } 

    @Test
    public void testAdd(){
        // 1. SETUP
        // Create the instance of the class that we want to test
        // DemoService demoService = new DemoService();

        // define the expected result
        // int expectedResult = 8;

        // 2.Execute
        // Call the method we want to test
        // int actualResult = demoService.add(3,5);

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(8, demoService.add(3,5), "3 + 5 should be 8");
    }

    @Test
    public void testSubstract() {
        // 1. SETUP
        // Create the instance of the class that we want to test
        // DemoService demoService = new DemoService();

        // definte the expected result
        int expectedResult = 2;

        // 2. EXECUTE
        // Call the method that we want to test
        int actualResult = demoService.substract(5, 3);

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(actualResult, expectedResult, "5 - 3 should be 2");
    }

    @Test
    public void testMultiply() {
        assertEquals(15, demoService.multiply(3, 5), "3 * 5 should be 15");
    }

    @Test
    public void testDivideNormal() {
        assertEquals(3, demoService.divide(15, 5), "15/5 should be 3");
    }

    @Test
    public void testDivideZero() {
        assertThrows(ArithmeticException.class, () -> demoService.divide(15, 0));
    }

    @Test
    public void testIsEven(){
        assertTrue(demoService.isEven(2), "2 should be even");
        assertFalse(demoService.isEven(3), "3 should not be even");
    }
}
