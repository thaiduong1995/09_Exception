package bai1;

import java.util.LinkedList;
import java.util.Scanner;

public class ManagerAccount {
    private LinkedList<UserAccount> accountsList = new LinkedList<>();

    public void display(UserAccount ua) {
        String result = "====================THÔNG TIN TÀI KHOẢN====================\n";
        for (UserAccount account :
                accountsList) {
            if (account.getAccount().equals(ua.getAccount())) {
                System.out.println(account);
                break;
            }
        }
    }

    public UserAccount login(Scanner scanner) {
        System.out.println("====================ĐĂNG NHẬP====================");
        System.out.print("Tài khoản: ");
        String account = scanner.nextLine();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();
        for (UserAccount ua :
                accountsList) {
            if (account.equals(ua.getAccount()) && password.equals(ua.getPassword())) {
                System.out.println("Đăng nhập thành công");
                return ua;
            }
        }
        return null;
    }

    public boolean isExit(UserAccount ua) {
        for (UserAccount account :
                accountsList) {
            if (account.getAccount().equals(ua.getAccount())) {
                return true;
            }
        }
        return false;
    }

    public ManagerAccount init() {
        accountsList.add(new UserAccount("tranthaiduong@gmail.com", "1A2b3c4d",
                "Trần Thái Dương", "13/08/1995", "0123456789"));
        return this;
    }

    public LinkedList<UserAccount> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(LinkedList<UserAccount> accountsList) {
        this.accountsList = accountsList;
    }
}
