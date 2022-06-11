package bai2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class ManagerPlayer {
    private LinkedList<Player> playersList = new LinkedList<>();

    public void searchPostion(Scanner scanner) {
        System.out.print("Nhập vị trí muốn tìm kiếm: ");
        String position = scanner.nextLine();
        Player player = new Player();
        player.setPosition(position);
        System.out.println("\n====================DACH SÁCH CẦU THỦ Ở VỊ TRÍ " + position.toUpperCase() + "====================");
        System.out.printf("%-3s %-11s %-30s %-4s %-9s %-11s\n", "STT", "Mã cầu thủ", "Họ tên", "Tuổi",
                "Giới tính", "Lương (USD/week)");
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getPosition().type.equals(player.getPosition().type)) {
                System.out.printf("%3d %-11s %-30s %4d %-9s %,11d\n", i + 1, playersList.get(i).getId(),
                        playersList.get(i).getName(), playersList.get(i).getAge(), playersList.get(i).getGender(),
                        playersList.get(i).getWage());
            }
        }
    }

    public void searchID(Scanner scanner) {
        System.out.print("Nhập mã cầu thủ muốn tìm kiếm: ");
        String code = scanner.nextLine();
        System.out.println("\n====================THÔNG TIN CẦU THỦ CÓ MÃ " + code + "====================");
        int index = this.getIndex(code);
        if (index >= 0) {
            System.out.println(playersList.get(index));
        } else {
            System.out.println("Không tìm thấy cầu thủ");
        }
    }

    public void sortPostion() {
        for (Position position :
                Position.values()) {
            System.out.println("\n====================DANH SÁCH CẦU THỦ Ở VỊ TRÍ " + position.type.toUpperCase() + "====================");
            System.out.printf("%-3s %-11s %-30s %-4s %-9s %-11s\n", "STT", "Mã cầu thủ", "Họ tên", "Tuổi",
                    "Giới tính", "Lương (USD/week)");
            for (int i = 0; i < playersList.size(); i++) {
                if (playersList.get(i).getPosition().type.equalsIgnoreCase(position.type)) {
                    System.out.printf("%3d %-11s %-30s %4d %-9s %,11d\n", i + 1, playersList.get(i).getId(),
                            playersList.get(i).getName(), playersList.get(i).getAge(), playersList.get(i).getGender(),
                            playersList.get(i).getWage());
                }
            }
        }
    }

    public void sortName() {
        Collections.sort(playersList, (o1, o2) -> {
            String name1 = o1.getName().substring(o1.getName().lastIndexOf(" "), o1.getName().length());
            String name2 = o2.getName().substring(o2.getName().lastIndexOf(" "), o2.getName().length());
            return name1.compareToIgnoreCase(name2);
        });
        System.out.println("Sắp xếp danh sách cầu thủ theo tên thành công");
    }

    public boolean add(Scanner scanner) throws ValidatePlayer {
        System.out.print("Nhập vị trí cầu thủ muốn thêm: ");
        try {
            String i = scanner.nextLine();

            try {
                Integer.parseInt(i);
            } catch (NumberFormatException ex) {
                throw new ValidatePlayer("Vị trí thêm vào không hợp lệ");
            }

            int index = Integer.parseInt(i);
            if (index <= 0 || index > playersList.size()) {
                throw new ValidatePlayer("Vị trí thêm vào nằm ngoài danh sách");
            }

            Player player = new Player();
            if (player.create(scanner, this)) {
                playersList.add(index, player);
                return true;
            }
        } catch (ValidatePlayer e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        result += String.format("%-3s %-11s %-30s %-4s %-9s %-8s %-11s\n", "STT", "Mã cầu thủ", "Họ tên", "Tuổi",
                "Giới tính", "Ví trí", "Lương (USD/week)");
        for (int i = 0; i < playersList.size(); i++) {
            result += String.format("%3d %-11s %-30s %4d %-9s %-8s %,11d\n", i + 1, playersList.get(i).getId(),
                    playersList.get(i).getName(), playersList.get(i).getAge(), playersList.get(i).getGender(),
                    playersList.get(i).getPosition().type, playersList.get(i).getWage());
        }
        return result;
    }

    public int getIndex(String id) {
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public ManagerPlayer init() {
        playersList.add(new Player("PLAYER0000", "Trần Thái Dương", 26, "Nam", "Thủ môn", 1000L));
        playersList.add(new Player("PLAYER0002", "Trần Văn Mạnh", 20, "Nam", "Tiền đạo", 2000L));
        playersList.add(new Player("PLAYER0003", "Ngô Mạnh Sơn", 19, "Nam", "Tiền vệ", 4000L));
        playersList.add(new Player("PLAYER0004", "Trần Văn Hiểu", 21, "Nam", "Hậu vệ", 5000L));
        playersList.add(new Player("PLAYER0005", "Lê Thái Học", 22, "Nam", "Tiền vệ", 10000L));
        playersList.add(new Player("PLAYER0006", "Nguyễn Trường Chinh", 23, "Nam", "Tiền đạo", 20000L));
        return this;
    }

    public LinkedList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(LinkedList<Player> playersList) {
        this.playersList = playersList;
    }
}
