package bai2;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private static int number = 0;

    private String id;
    private String name;
    private int age;
    private String gender;
    private Position position;
    private long wage;

    public boolean create(Scanner scanner, ManagerPlayer data) {
        System.out.println("====================HOÀN TẤT THÔNG TIN ĐỂ ĐĂNG KÝ CẦU THỦ====================");
        try {
            for (Player player :
                    data.getPlayersList()) {
                while (player.getId().equalsIgnoreCase(this.id)) {
                    if (number < 10) {
                        id = "";
                        id += "PLAYER00" + "0" + String.valueOf(number);
                    } else {
                        id += "PLAYER00" + String.valueOf(number);
                    }
                    number++;
                }
            }

            System.out.print("Tên cầu thủ: ");
            String name = scanner.nextLine();

            if (name.isEmpty()) {
                throw new ValidatePlayer("Họ tên không được để trống");
            }

            if (!ValidatePlayer.isValidate(name, "^[\\p{L} .'-]+$")) {
                throw new ValidatePlayer("Họ tên chỉ được gồm các chữ cái");
            }

            System.out.print("Tuổi: ");
            String age = scanner.nextLine();

            try {
                Integer.parseInt(age);
            } catch (NumberFormatException e) {
                throw new ValidatePlayer("Tuổi nhập vào phải là một số");
            }

            this.age = Integer.parseInt(age);

            if (this.age < 16 || this.age > 40) {
                throw new ValidatePlayer("Độ tuổi quá giới hạn");
            }

            System.out.print("Giới tinh: ");
            gender = scanner.nextLine();
            if (!gender.equalsIgnoreCase("nam")) {
                throw new ValidatePlayer("Cầu thủ phải là nam giới");
            }

            System.out.print("Vị trí: ");
            String positon = scanner.nextLine();

            this.setPosition(positon);
            if (this.position == null) {
                throw new ValidatePlayer("Vị trí cầu thủ không hợp lệ");
            }

            System.out.print("Lương: ");
            String wage = scanner.nextLine();

            try {
                Long.parseLong(wage);
            } catch (NumberFormatException e) {
                throw new ValidatePlayer("Lương nhập vào phải là một số");
            }

            this.name = name;
            this.wage = Long.parseLong(wage);

            return true;

        } catch (ValidatePlayer e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public String toString() {
        String result = "";
        result += "Mã cầu thủ        " + id + "\n";
        result += "Họ tên:           " + name + "\n";
        result += "Tuổi:             " + age + "\n";
        result += "Giới tính:        " + gender + "\n";
        result += "Vị trí:           " + position.type + "\n";
        result += String.format("Lương (USD/week): %,d\n", wage);
        result += "======================================================================\n";
        return result;
    }

    public Player() {
        if (number < 10) {
            id = "";
            id += "PLAYER00" + "0" + String.valueOf(number);
        } else {
            id += "PLAYER00" + String.valueOf(number);
        }
        number++;
    }

    public Player(String name, int age, String gender, String position, long wage) {
        if (number < 10) {
            id += "0" + String.valueOf(number);
        } else {
            id += String.valueOf(number);
        }

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.setPosition(position);
        this.wage = wage;

        number++;
    }

    public Player(String id, String name, int age, String gender, String position, long wage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.setPosition(position);
        this.wage = wage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(String p) {
        boolean b = false;
        for (Position position :
                Position.values()) {
            if (p.equalsIgnoreCase(position.type)) {
                this.position = position;
                b = true;
                break;
            }
        }
        if (!b) {
            position = null;
        }
    }

    public long getWage() {
        return wage;
    }

    public void setWage(long wage) {
        this.wage = wage;
    }
}


