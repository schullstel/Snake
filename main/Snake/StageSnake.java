package Snake;

import javafx.geometry.Point2D;

import static Snake.Config.STAGE_HEIGHT_MULTIPLIER;
import static Snake.Config.STAGE_WIDTH_MULTIPLIER;


public class StageSnake {

    public StageSnake() {
        this.stageEdge_ = new Point2D(STAGE_WIDTH_MULTIPLIER, STAGE_HEIGHT_MULTIPLIER);
        this.snake_ = new Snake(STAGE_WIDTH_MULTIPLIER, STAGE_HEIGHT_MULTIPLIER);
        this.feed_ = new Feed();
        this.feed_.randomPosition(this.snake_.position(), STAGE_WIDTH_MULTIPLIER, STAGE_HEIGHT_MULTIPLIER);
        this.endGame_ = false;
    }

    public void nextFrame() {
        if (this.snake_.isItselfEat() || this.isSnakeOutOfBounds()) {
            this.endGame_ = true;
            System.out.println("TRUE");
            return;
        }
        if (!this.endGame_) {
            if (this.isSnakeGrown()) {
                this.snake_.growUp();
                this.feed_.randomPosition(this.snake_.position(), STAGE_WIDTH_MULTIPLIER, STAGE_HEIGHT_MULTIPLIER);
                return;
            }
            this.snake_.move();
        }
    }

    private boolean isSnakeOutOfBounds() {
        for (Point2D point : snake_.position()) {
            if (point.getX() < 0 || point.getX() >= STAGE_WIDTH_MULTIPLIER || point.getY() < 0 || point.getY() >= STAGE_HEIGHT_MULTIPLIER) {
                return true;
            }
        }
        return false;
    }

    public boolean isSnakeGrown() {
        return (this.getSnake().nextHeadPosition().equals(getFeed().position()));
    }

    public Feed getFeed() {
        return this.feed_;
    }


    public Snake getSnake() {
        return this.snake_;
    }


    public boolean isEndGame_() {
        return endGame_;
    }

    private boolean isSnakeGrown_;
    private final Point2D stageEdge_;
    private boolean endGame_;
    private Snake snake_;
    private Feed feed_;

}
