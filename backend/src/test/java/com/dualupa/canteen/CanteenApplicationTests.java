package com.dualupa.canteen;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author avbelyaev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CanteenApplicationTests {

    @Ignore("since default profile requires mongo to be lauched")
    @Test
    public void contextLoads() {
        // empty
    }

}
