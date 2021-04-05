import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EqualTest {

	@Test
	void test() {
		Cond c = new Cond();
		boolean result = c.igual("5", "5");
		assertEquals(true, result);
	}

}
