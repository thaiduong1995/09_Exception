package bai2;

public enum Position {
    Striker("Tiền đạo"),
    Midfielder("Tiền vệ"),
    Defender("Hậu vệ"),
    Goalie("Thủ môn");

    public final String type;

    Position(String type) {
        this.type = type;
    }
}

