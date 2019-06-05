package Snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;

import static Snake.ObjectType.TFeed;
import static utilities.Generator.generate_random_int;

public class Feed extends Object {
    public Feed() {
        this.type = TFeed;


    }

    public void randomPosition(final ArrayList<Point2D> snake,
                               final int max_width,
                               final int max_height) {
        Point2D feedPlace;
        do {
            int x = generate_random_int(0, max_width - 1);
            int y = generate_random_int(0, max_height - 1);
            feedPlace = new Point2D(x, y);
        } while (compareVectors(snake, feedPlace));
        this.position_ = feedPlace;
    }

    public final Point2D position() {
        return position_;
    }

    private boolean compareVectors(final ArrayList<Point2D> vec1, final Point2D vec2) {
        for (Point2D point : vec1) {
            if (point.getX() == vec2.getX() && point.getY() == vec2.getY()) {
                return true;
            }
        }
        return false;
    }

    private Point2D position_;
}
