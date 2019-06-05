package Snake;

import static Snake.ObjectType.TNonType;

public class Object {
    protected Object(ObjectType x) { type = x; }
    protected Object() { type = TNonType; }

    public ObjectType type;
}
