package bai1;

import bai2.ValidatePlayer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ValidatePlayer {
        UserAccount ua = new UserAccount();
        ManagerAccount ma = new ManagerAccount().init();

        Scanner scanner = new Scanner(System.in);
        int optMain;

        do {
            System.out.println("====================HỆ THỐNG====================");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            optMain = Integer.parseInt(scanner.nextLine());
            switch (optMain) {
                case 1:
                    ua = ma.login(scanner);
                    if (ua != null) {
                        int optSub;
                        do {
                            System.out.println("====================TÀI KHOẢN====================");
                            System.out.println("1. Xem tài khoản");
                            System.out.println("2. Chỉnh sửa tài khoản");
                            System.out.println("0. Thoát");
                            System.out.print("Nhập lựa chọn: ");
                            optSub = Integer.parseInt(scanner.nextLine());
                            switch (optSub) {
                                case 1:
                                    ma.display(ua);
                                    break;
                                case 2:
                                    ua.update(scanner);
                                    break;
                            }
                        } while (optSub != 0);
                    } else {
                        System.out.println("Tài khoản hoặc mật khẩu không đúng");
                    }
                    break;
                case 2:
                    if (ua.register(scanner, ma)) {
                        System.out.println("Hoàn tất đăng ký");
                    } else {
                        System.out.println("Đăng ký thất bại");
                    }
                    break;
            }
        } while (optMain !=0);
    }
}
