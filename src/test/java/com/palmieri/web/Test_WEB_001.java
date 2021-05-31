package com.palmieri.web;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_WEB_001 {

    @BeforeAll
    static void beforeAll(){}

    @BeforeEach
    void beforeEach() {}

    @Test
    @DisplayName("Test 001")
    void test_001(){
    }

    @AfterEach
    void tearDown() {}

    @AfterAll
    static void tearDownAll() {}
}
