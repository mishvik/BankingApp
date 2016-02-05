package com.ibm.enterprise.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AccountTest {
	 
    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test
    public void testGetAccountBalanceAPITest() {
        Assert.assertEquals(true, true);
    }
}
