package ValidityChecks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullCheckTest {
    @Test
    public void notNullCheckTest(){
        NotNullCheck nullCheck = new NotNullCheck();
        int a = 0;
        String b = null;
        String c = "Test";

        assertAll(
                () -> assertTrue(nullCheck.checkValidity(a).ok()),
                () -> assertFalse(nullCheck.checkValidity(b).ok()),
                () -> assertTrue(nullCheck.checkValidity(c).ok())
        );
    }
}