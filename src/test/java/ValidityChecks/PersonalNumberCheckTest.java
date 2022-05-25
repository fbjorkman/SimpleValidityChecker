package ValidityChecks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalNumberCheckTest {
    @Test
    public void checkValidPersonalNumbers(){
        PersonalNumberCheck pb = new PersonalNumberCheck();
        String personA = "197802022389";
        String personB = "8204112380";
        long personAlong = 7802022389L;
        long personBlong = 198204112380L;
        int personCint = 4121935;

        assertAll(
                () -> assertTrue(pb.checkValidity(personA).ok()),
                () -> assertTrue(pb.checkValidity(personB).ok()),
                () -> assertTrue(pb.checkValidity(personAlong).ok()),
                () -> assertTrue(pb.checkValidity(personBlong).ok()),
                () -> assertTrue(pb.checkValidity(personCint).ok())
        );
    }

    @Test
    public void checkInvalidCharactersTest(){
        PersonalNumberCheck pb = new PersonalNumberCheck();
        String personA = "19780202-2389";
        String personB = "19820411 2380";
        String personC = "19820411A2380";
        String personD = "19820411.2380";
        String personE = "19820411$2380";

        assertAll(
                () -> assertTrue(pb.checkValidity(personA).ok()),
                () -> assertTrue(pb.checkValidity(personB).ok()),
                () -> assertFalse(pb.checkValidity(personC).ok()),
                () -> assertFalse(pb.checkValidity(personD).ok()),
                () -> assertFalse(pb.checkValidity(personE).ok())
        );
    }

    @Test
    public void controlDigitTest(){
        PersonalNumberCheck pb = new PersonalNumberCheck();
        String personA = "198204112380";
        String personB = "198204112381";
        String personC = "8204112380";
        String personD = "8204112386";
        long personE = 198204112380L;
        long personF = 198204112388L;

        assertAll(
                () -> assertTrue(pb.checkValidity(personA).ok()),
                () -> assertFalse(pb.checkValidity(personB).ok()),
                () -> assertTrue(pb.checkValidity(personC).ok()),
                () -> assertFalse(pb.checkValidity(personD).ok()),
                () -> assertTrue(pb.checkValidity(personE).ok()),
                () -> assertFalse(pb.checkValidity(personF).ok())
        );
    }

    @Test
    public void checkValidDayAndMonth(){
        PersonalNumberCheck pb = new PersonalNumberCheck();
        String personA = "198204112380";
        String personB = "198214112382";
        String personC = "8204512389";
        long personD = 198214112382L;
        long personE = 198204512389L;

        assertAll(
                () -> assertTrue(pb.checkValidity(personA).ok()),
                () -> assertFalse(pb.checkValidity(personB).ok()),
                () -> assertFalse(pb.checkValidity(personC).ok()),
                () -> assertFalse(pb.checkValidity(personD).ok()),
                () -> assertFalse(pb.checkValidity(personE).ok())
        );
    }
}