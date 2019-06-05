package Snake;


public class Config {
    /* For drawer */
    public static final String TITLE = "MegaWonsz9";
    public static final int TEXTURE_WIDTH = 16;
    public static final int TEXTURE_HEIGHT = 16;

    // For stage
    public static final int STAGE_WIDTH_MULTIPLIER = 30;
    public static final int STAGE_HEIGHT_MULTIPLIER = 20;
    public static final int STAGE_WIDTH = STAGE_WIDTH_MULTIPLIER * TEXTURE_WIDTH;
    public static final int STAGE_HEIGHT = STAGE_HEIGHT_MULTIPLIER * TEXTURE_HEIGHT;

    // For drawer - images
    public static final String HEAD_IMAGE = "app/snake_head.png";
    public static final String TAIL_IMAGE = "app/snake_tail.png";
    public static final String FOOD_IMAGE = "app/apple.png";
    public static final String SNAKE_ELEMENT = "app/snake_element.png";
}
