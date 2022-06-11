package bai2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ValidatePlayer {
        ManagerPlayer mp = new ManagerPlayer().init();

        Scanner scanner = new Scanner(System.in);
        int optMain;

        do {
            System.out.println("====================QUẢN LÝ CẦU THỦ====================");
            System.out.println("1. Danh sách cầu thủ");
            System.out.println("2. Thêm cầu thủ");
            System.out.println("3. Danh sách cầu thủ theo tên");
            System.out.println("4. Danh sách cầu thủ theo vị trí");
            System.out.println("5. Tìm kiếm cầu thủ theo vị trí");
            System.out.println("6. Tìm kiếm cầu thủ theo mã");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            optMain = Integer.parseInt(scanner.nextLine());
            switch (optMain) {
                case 1:
                    System.out.println("====================DANH SÁCH CẦU THỦ====================");
                    System.out.println(mp);
                    break;
                case 2:
                    System.out.println("====================THÊM CẦU THỦ====================");
                    mp.add(scanner);
                    break;
                case 3:
                    System.out.println("====================DANH SÁCH CẦU THỦ THEO TÊN====================");
                    mp.sortName();
                    break;
                case 4:
                    System.out.println("====================DANH SÁCH CẦU THỦ THEO VỊ TRÍ====================");
                    mp.sortPostion();
                    break;
                case 5:
                    System.out.println("====================TÌM KIẾM CẦU THỦ THEO VỊ TRÍ====================");
                    mp.searchPostion(scanner);
                    break;
                case 6:
                    System.out.println("====================TÌM KIẾM CẦU THỦ THEO MÃ====================");
                    mp.searchID(scanner);
                    break;
            }
        } while (optMain !=0);
    }
}
