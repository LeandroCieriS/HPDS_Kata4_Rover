package refactoring;

import java.util.HashMap;
import java.util.Map;

public class SingleViewPoint implements ViewPoint{
    private Heading heading;
    private Position position;

    public SingleViewPoint (String facing, int x, int y){
        this(Heading.of(facing), x, y);
    }

    public SingleViewPoint(Heading heading, int x, int y){
        this(heading, new Position(x, y));
    }

    public SingleViewPoint(Heading heading, Position position){
        this.heading = heading;
        this.position = position;
    }

    public Heading heading() {
        return heading;
    }

    public Position position() {
        return position;
    }

    @Override
    public ViewPoint turnLeft() {
        heading = heading.turnLeft();
        return this;
    }

    @Override
    public ViewPoint turnRight() {
        heading = heading.turnRight();
        return this;
    }

    @Override
    public ViewPoint forward() {
        position = position.forward(heading);
        return this;
    }

    @Override
    public ViewPoint backward() {
        position = position.backward(heading);
        return this;
    }


    public static class Position {

        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position forward(Heading heading) {
            if (heading.isVertical()) return verticalMovement(heading, 1);
            return horizontalMovement(heading, 1);
        }

        public Position backward(Heading heading) {
            if (heading.isVertical()) return verticalMovement(heading, -1);
            return horizontalMovement(heading, -1);
        }

        //TODO: Refactorizar para que no tenga dos parametros
        private Position verticalMovement(Heading heading, int sign) {
            if (heading == Heading.North) return new Position(x, y + sign);
            return new Position(x, y - sign);
        }

        private Position horizontalMovement(Heading heading, int sign) {
            if (heading == Heading.East) return new Position(x + sign, y);
            return new Position(x - sign, y);
        }

        @Override
        public boolean equals(Object object) {
            return isSameClass(object) && equals((Position) object);
        }

        private boolean equals(Position position) {
            return position == this || (x == position.x && y == position.y);
        }

        private boolean isSameClass(Object object) {
            return object != null && object.getClass() == Position.class;
        }

    }

    public enum Heading {
        North, East, South, West;
        static Map<Character, Heading> headings = new HashMap<>();

        static {
            headings.put('N', North);
            headings.put('S', South);
            headings.put('W', West);
            headings.put('E', East);
        }

        public static Heading of(String label) {
            return of(label.charAt(0));
        }

        public static Heading of(char label) {
            return headings.get(label);
        }

        public boolean isVertical () { return this == North || this == South; }

        public Heading turnRight() {
            return values()[add(+1)];
        }

        public Heading turnLeft() {
            return values()[add(-1)];
        }

        private int add(int offset) {
            return (this.ordinal() + offset + values().length) % values().length;
        }
    }


}
