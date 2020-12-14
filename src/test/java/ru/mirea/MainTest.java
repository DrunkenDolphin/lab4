package ru.mirea;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neuroph.core.NeuralNetwork;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MainTest {

    private NeuralNetwork ann = null;

    private void print(String input, double output, double actual) {
        log.info("Testing: {} Expected: {} Result: {}", input, actual, output);
    }

    @Before
    public void annInit() {
        ann = Main.trainNeuralNetwork(Main.assembleNeuralNetwork());
    }

    @Test
    public void leftDisjunctTest() {
        ann.setInput(0, 1);
        ann.calculate();
        print("0, 1", ann.getOutput()[0], 1.0);
        assertEquals(ann.getOutput()[0], 1.0, 0.0);
    }

    @Test
    public void rightDisjunctTest() {
        ann.setInput(1, 0);
        ann.calculate();
        print("1, 0", ann.getOutput()[0], 1.0);
        assertEquals(ann.getOutput()[0], 1.0, 0.0);
    }

    @Test
    public void bothFalseConjunctTest() {
        ann.setInput(0, 0);
        ann.calculate();
        print("0, 0", ann.getOutput()[0], 0.0);
        assertEquals(ann.getOutput()[0], 0.0, 0.0);
    }

    @Test
    public void bothTrueConjunctTest() {
        ann.setInput(1, 1);
        ann.calculate();
        print("1, 1", ann.getOutput()[0], 0.0);
        assertEquals(ann.getOutput()[0], 0.0, 0.0);
    }

    @After
    public void annClose() {
        ann = null;
    }
}
