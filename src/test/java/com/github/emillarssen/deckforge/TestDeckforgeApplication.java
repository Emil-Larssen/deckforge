package com.github.emillarssen.deckforge;

import org.springframework.boot.SpringApplication;

public class TestDeckforgeApplication {

	public static void main(String[] args) {
		SpringApplication.from(DeckforgeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
