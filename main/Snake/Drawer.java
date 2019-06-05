package Snake;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static Snake.Config.*;
import static Snake.Direction.*;
import static Snake.DirectionType.UP_DOWN;


public class Drawer {

    long then = System.nanoTime();

    public Drawer(Task<Void> sleeper) {
        this.stage_ = new StageSnake();
        this.pane_ = new Pane();
        this.imageOfHead_ = new Image(HEAD_IMAGE);
        this.imageOfTail_ = new Image(TAIL_IMAGE);
        this.imageOfFood_ = new Image(FOOD_IMAGE);
        this.imageOfSnakeElemenet = new Image(SNAKE_ELEMENT);
        this.myBI = new BackgroundImage(new Image("app/background1.png", TEXTURE_WIDTH * STAGE_WIDTH_MULTIPLIER, TEXTURE_HEIGHT * STAGE_HEIGHT_MULTIPLIER, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.pane_.setBackground(new Background(myBI));
        this.sleeper = sleeper;
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - then > 1000000000 / 8) {
                    //checkIfEnd();
                    draw();
                    then = now;
                }

            }
        };
    }

    private Direction oposite(Direction direction) throws Exception {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        throw new Exception("Unknown direction oposite(Direction direction)");
    }

    private void drawSnakeHead(final Snake snake) {
        final double NINETY_DEGREE = 90.0;
        final double HEAD_X_POSITION = snake.getHead().getX() * TEXTURE_WIDTH;
        final double HEAD_Y_POSITION = snake.getHead().getY() * TEXTURE_HEIGHT;
        ImageView head = new ImageView(imageOfHead_);
        final Direction direction = snake.getDirection();
        double angle = 0.0;

        switch (direction) {
            case UP:
                break;
            case DOWN:
                angle = 2 * NINETY_DEGREE;
                break;
            case LEFT:
                angle = 3 * NINETY_DEGREE;
                break;
            case RIGHT:
                angle = NINETY_DEGREE;
                break;
        }
        head.setLayoutX(HEAD_X_POSITION);
        head.setLayoutY(HEAD_Y_POSITION);
        head.setRotate(head.getRotate() + angle);
        pane_.getChildren().add(head);
    }

    private void drawTailElement(final Point2D element) {
        final double TAIL_X_POSITION = element.getX() * TEXTURE_WIDTH;
        final double TAIL_Y_POSITION = element.getY() * TEXTURE_HEIGHT;

        ImageView bodyElement = new ImageView();
        bodyElement.setLayoutX(TAIL_X_POSITION);
        bodyElement.setLayoutY(TAIL_Y_POSITION);
        bodyElement.setImage(imageOfSnakeElemenet);
        pane_.getChildren().add(bodyElement);
    }

    private void drawSnakeTailEnd(Snake snake) {
        final double NINETY_DEGREE = 90.0;
        final double TAIL_X_POSITION = snake.getTail().getX() * TEXTURE_WIDTH;
        final double TAIL_Y_POSITION = snake.getTail().getY() * TEXTURE_HEIGHT;
        try {
            final DirectionType direction = getDirection(snake.position().get(snake.position().size() - 1), snake.position().get(snake.position().size() - 2));
            ImageView tail = new ImageView(imageOfTail_);
            tail.setLayoutX(TAIL_X_POSITION);
            tail.setLayoutY(TAIL_Y_POSITION);
            double angle = 0.0;

            switch (direction) {
                case UP_DOWN:
                    angle = 2 * NINETY_DEGREE;
                    break;
                case DOWN_UP:
                    break;
                case LEFT_RIGHT:
                    angle = NINETY_DEGREE;
                    break;
                case RIGHT_LEFT:
                    angle = 3 * NINETY_DEGREE;
                    break;
            }
            tail.setRotate(tail.getRotate() + angle);
            pane_.getChildren().add(tail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDirection(Direction d) {
        stage_.getSnake().setNewDirection(d);
    }

    public Direction getDirection() {
        return stage_.getSnake().getDirection();
    }

    private DirectionType getDirection(final Point2D first, final Point2D second) throws Exception {
        final double deltaX = second.getX() - first.getX();
        final double deltaY = second.getY() - first.getY();

        if (deltaX == 0 && deltaY > 0) {
            return UP_DOWN;
        } else if (deltaX == 0 && deltaY < 0) {
            return DirectionType.DOWN_UP;
        } else if (deltaX > 0 && deltaY == 0) {
            return DirectionType.LEFT_RIGHT;
        } else if (deltaX < 0 && deltaY == 0) {
            return DirectionType.RIGHT_LEFT;
        }

        throw new Exception("Unknown direction");
    }


    private void drawSnake(final Snake snake) {
        ArrayList<Point2D> snakeBody = snake.position();
        final int FIRST_PART_AFTER_HEAD = 1;
        final int LAST_PART_TAIL = 1;

        drawSnakeHead(snake);
        try {
            Direction lastElementDirection = oposite(snake.getDirection());
            for (int i = FIRST_PART_AFTER_HEAD; i < snakeBody.size() - LAST_PART_TAIL; i++) {
                drawTailElement(snakeBody.get(i));
            }
            drawSnakeTailEnd(snake);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void drawFeed(final Feed feed) {
        final double FEED_X_POSITION = feed.position().getX() * TEXTURE_WIDTH;
        final double FEED_Y_POSITION = feed.position().getY() * TEXTURE_HEIGHT;
        ImageView feedImage = new ImageView(imageOfFood_);
        feedImage.setLayoutX(FEED_X_POSITION);
        feedImage.setLayoutY(FEED_Y_POSITION);
        pane_.getChildren().add(feedImage);
    }

    private void draw() {
        if (stage_.isEndGame_()) {
            new Thread(sleeper).start();
            timer.stop();
        }
        pane_.getChildren().clear();
        drawSnake(stage_.getSnake());
        drawFeed(stage_.getFeed());
        stage_.nextFrame();
    }

    public Pane doSmth() {
        Pane pane = new Pane();
        Image wastedImage = new Image("app/wasted.png");
        ImageView wasted = new ImageView(wastedImage);
        wasted.setLayoutX(33);
        wasted.setLayoutY(101);
        pane.setBackground(new Background(myBI));
        pane.getChildren().add(wasted);
        return pane;
    }

    public void startAnimation() {
        timer.start();
    }

    public Pane getPane_() {
        return pane_;
    }

    private Pane pane_;
    private Image imageOfHead_;
    private Image imageOfTail_;
    private Image imageOfFood_;
    private Image imageOfSnakeElemenet;
    private StageSnake stage_;
    private AnimationTimer timer;
    private BackgroundImage myBI;
    private Task<Void> sleeper;
}
