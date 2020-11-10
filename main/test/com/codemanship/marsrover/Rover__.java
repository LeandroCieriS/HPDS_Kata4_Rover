package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover;
import refactoring.SingleViewPoint;
import refactoring.ViewPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.Rover.Order.*;
import static refactoring.SingleViewPoint.*;
import static refactoring.SingleViewPoint.Heading.*;

public class Rover__ {

    @Test
    public void could_be_initialized_with_legacy_constructor() {
        assertThat(cast(new Rover(new SingleViewPoint("N", 5, 5)).viewPoint()).heading()).isEqualTo(North);
        assertThat(cast(new Rover(new SingleViewPoint("North", 5, 5)).viewPoint()).heading()).isEqualTo(North);
        assertThat(cast(new Rover(new SingleViewPoint("North", 5, 5)).viewPoint()).position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_be_initialized_using_new_constructors() {
        assertThat(cast(new Rover(new SingleViewPoint(North, new Position(4,4))).viewPoint()).position()).isEqualTo(new Position(4,4));
        assertThat(cast(new Rover(new SingleViewPoint(North, 4, 4)).viewPoint()).position()).isEqualTo(new Position(4,4));
    }

    @Test
    public void could_turn_left() {
        Rover rover = new Rover(new SingleViewPoint(North, new Position(3, 3)));
        rover.go(Left);
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(West);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(3,3));
    }

    @Test
    public void could_turn_right() {
        Rover rover = new Rover(new SingleViewPoint(East, new Position(5, 1)));
        rover.go(Right);
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(South);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(5,1));
    }

    @Test
    public void could_go_forward() {
        Rover rover = new Rover(new SingleViewPoint(South, new Position(-1, 1)));
        rover.go(Forward);
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(South);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(-1,0));
    }

    @Test
    public void could_go_backward() {
        Rover rover = new Rover(new SingleViewPoint(West, new Position(-4, 4)));
        rover.go(Backward);
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(West);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(-3,4));
    }

    @Test
    public void could_execute_many_orders() {
        Rover rover = new Rover(new SingleViewPoint(West, new Position(3, 1)));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(West);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_execute_legacy_instructions() {
        Rover rover = new Rover(new SingleViewPoint(West, new Position(3, 1)));
        rover.go("BLFRF");
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(West);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_ignore_legacy_instructions() {
        Rover rover = new Rover(new SingleViewPoint(West, new Position(3, 1)));
        rover.go("BL*FRF");
        assertThat(cast(rover.viewPoint()).heading()).isEqualTo(West);
        assertThat(cast(rover.viewPoint()).position()).isEqualTo(new Position(3,0));
    }

    private SingleViewPoint cast(ViewPoint viewPoint) {
        return (SingleViewPoint) viewPoint;
    }
}
