import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class mayorTest {

	@Test
	void test() {
		Cond c = new Cond();
		boolean result = c.menor("5", "2");
		assertEquals(true, result);
	}

}
