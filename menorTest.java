import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class menorTest {

	@Test
	void test() {
		Cond c = new Cond();
		boolean result = c.menor("5", "2");
		assertEquals(false, result);
	}

}
