package chess;

/**
 * @author pushpanjay.kumar created on 16/3/20
 */
@FunctionalInterface
public interface ValidateMove {
    boolean validate(Coordinate srcPos, Coordinate destPos);
}
