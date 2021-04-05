import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AtomTest {

	@Test
	void test() {
		Cond c = new Cond();
		boolean result = c.Atom("5");
		assertEquals(true, result);
	}

}
