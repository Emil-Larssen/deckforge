package com.github.emillarssen.deckforge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DeckforgeApplicationTests {

	@Test
	void contextLoads() {
	}

}
