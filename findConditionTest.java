import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class findConditionTest {

	@Test
	void test() {
		HashMap<String, Integer> variables = new HashMap<>();
        variables.put("n", 1);
        String s = " cond((= n 1)1)(t 0))";
        Cond c = new Cond();
        String result = c.findcondition(s, variables);
        assertEquals("1", result);
	}

}
