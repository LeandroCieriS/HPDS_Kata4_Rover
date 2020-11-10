package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.SingleViewPoint;

import static org.junit.Assert.assertEquals;
import static refactoring.SingleViewPoint.Heading.*;

public class Heading_ {

	@Test
	public void should_be_created_from_string() {
		assertEquals(North, SingleViewPoint.Heading.of("N"));
		assertEquals(South, SingleViewPoint.Heading.of("S"));
		assertEquals(East, SingleViewPoint.Heading.of("E"));
		assertEquals(West, SingleViewPoint.Heading.of("W"));
	}

	@Test
	public void should_be_created_from_char() {
		assertEquals(North, SingleViewPoint.Heading.of('N'));
		assertEquals(South, SingleViewPoint.Heading.of('S'));
		assertEquals(East, SingleViewPoint.Heading.of('E'));
		assertEquals(West, SingleViewPoint.Heading.of('W'));
	}

	@Test
	public void should_be_able_turn_right() {
		assertEquals(East, North.turnRight());
		assertEquals(South, East.turnRight());
		assertEquals(West, South.turnRight());
		assertEquals(North, West.turnRight());
	}

	@Test
	public void should_be_able_turn_left() {
		assertEquals(West, North.turnLeft());
		assertEquals(North, East.turnLeft());
		assertEquals(East, South.turnLeft());
		assertEquals(South, West.turnLeft());
	}
}
