package Snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;

import static Snake.ObjectType.TSnake;

public class Snake extends Object {
    public Snake(int stage_width, int stage_height) {
        this.type = TSnake;
        System.out.println();
        this.direction_ = Direction.UP;
        this.position_ = new ArrayList<Point2D>();
        initilize(stage_width, stage_height);
    }

    public void initilize(int stage_width, int stage_height) {
        createBodyOnCenterStage(stage_width, stage_height);
    }

    public void setNewDirection(Direction newDirection) {
        this.direction_ = newDirection;
    }

    public Direction getDirection() {
        return this.direction_;
    }

    public void move() {
        moveHead();
        moveTail();
        setHead();
    }

    public void growUp() {
        moveHead();
        setHead();
    }

    public boolean isItselfEat() {
        for (int i = 1; i < position_.size() - 1; i++) {
            if (head_.getX() == position_.get(i).getX() && head_.getY() == position_.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public final Point2D getHead() {
        return this.head_;
    }

    public final Point2D getTail() {
        return this.position_.get(this.position_.size() - 1);
    }

    public final ArrayList<Point2D> position() {
        return this.position_;
    }


    public Point2D nextHeadPosition() throws RuntimeException {
        switch (this.direction_) {
            case DOWN:
                return new Point2D(this.head_.getX(), this.head_.getY() + 1);
            case UP:
                return new Point2D(this.head_.getX(), this.head_.getY() - 1);
            case LEFT:
                return new Point2D(this.head_.getX() - 1, this.head_.getY());
            case RIGHT:
                return new Point2D(this.head_.getX() + 1, this.head_.getY());
        }
        throw new RuntimeException("Invalid head position");
    }


    private void createBodyOnCenterStage(int stage_width, int stage_height) {
        Point2D headPlace = new Point2D(stage_width / 2, stage_height / 2 - 1);
        Point2D second = new Point2D(stage_width / 2, stage_height / 2);
        Point2D third = new Point2D(stage_width / 2, stage_height / 2 + 1);
        Point2D tailPlace = new Point2D(stage_width / 2, stage_height / 2 + 2);

        position_.add(headPlace);
        position_.add(second);
        position_.add(third);
        position_.add(tailPlace);

        setHead();
    }

    private void setHead() {
        this.head_ = this.position_.get(0);
    }

    private void moveHead() {
        Point2D head = nextHeadPosition();
        this.position_.add(0, head); // adding head to the begin of snake
    }

    private void moveTail() {
        this.position_.remove(this.position_.get(this.position_.size() - 1)); //remove tail from end of the snake
    }

    private Direction direction_;
    private Point2D head_;
    private ArrayList<Point2D> position_;
}
