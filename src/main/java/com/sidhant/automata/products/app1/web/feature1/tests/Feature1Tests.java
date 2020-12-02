package com.sidhant.automata.products.app1.web.feature1.tests;

import com.sidhant.automata.core.annotations.Description;
import com.sidhant.automata.core.listeners.MyListener;
import com.sidhant.automata.core.logger.MyLogger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListener.class)
public class Feature1Tests {

    @Description("This is test one")
    @Test
    public void test_1() {
        MyLogger.log("This is test 1", "test_1");
    }

    @Description("This is test two")
    @Test
    public void test_2() {
        MyLogger.log("This is test 2", "test_2");
    }

    @Description("This will test our logger")
    @Test
    public void test_3() {
        MyLogger.log("This is test 3", "test_3");
        Assert.assertEquals(0,1);
    }
}
