package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    private static final String TEST_NAME = "Ученик";

    @Test
    void positionTest() {
        Position position = new Position();
        position.setName(TEST_NAME);
        assertEquals(TEST_NAME, position.getName());
    }
}