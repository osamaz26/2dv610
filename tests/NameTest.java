
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameTest {

    private Name sut;

    @BeforeEach
    void beforeEach() {
        sut = new Name();
    }

    @Test
    void testHello() {
        String expected = "Hello";
        sut.setValue(expected);
        var actual = sut.getValue();
        assertEquals(expected, actual);
    }
}
