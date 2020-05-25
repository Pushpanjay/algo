package chess;

import java.util.Objects;
import java.util.UUID;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public class Player {
    private long userId;
    private String name;
    private Color color;

    public Player(String name, Color color) {
        userId = UUID.randomUUID().getLeastSignificantBits();
        this.name = name;
        this.color = color;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return userId == player.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
