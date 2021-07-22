package org.telepathy.reservation;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.telepathy.reservation.config.AppConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class)
@TestPropertySource("classpath:application.properties")
public class BaseTest extends TestCase {

    @Override
    protected void setUp() {}

    @Override
    protected void tearDown() {}

    @Test
    public void testDummy() {}
}