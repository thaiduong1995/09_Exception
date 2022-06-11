package bai1;

import bai2.ValidatePlayer;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAccount {
    private static int number = 0;
    private String id = "USER00";
    private String account;
    private String password;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;

    public UserAccount() {
        if (number < 10) {
            id += "0" + String.valueOf(number);
        } else {
            id += String.valueOf(number);
        }
        number++;
    }

    public boolean register(Scanner scanner, ManagerAccount data) throws ValidatePlayer {
        System.out.println("====================HOÀN TẤT THÔNG TIN ĐỂ ĐĂNG KÝ TÀI KHOẢN====================");
        try {
            for (UserAccount ua :
                    data.getAccountsList()) {
                if (ua.getId().equals(id)) {
                    throw new ValidatePlayer("Mã tài khoản đã tồn tại trong hệ thống");
                }
            }

            System.out.print("Tài khoản: ");
            String account = scanner.nextLine();

            if (account.length() < 8) {
                throw new ValidateAccount("Tài khoản có độ dài ít nhất 8 ký tự");
            }

            for (UserAccount ua :
                    data.getAccountsList()) {
                if (ua.getAccount().equals(account)) {
                    throw new ValidatePlayer("Tài khoản đã tồn tại trong hệ thống");
                }
            }

            if (!ValidateAccount.isValidate(account, "^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new ValidateAccount("Tài khoản chỉ được gồm chữ in thường và sô");
            }

            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();

            if (password.length() < 6) {
                throw new ValidateAccount("Mật khẩu có độ dài ít nhất 6 ký tự");
            }

            if (ValidateAccount.isValidate(password,"[^A-Z]+$")) {
                throw new ValidateAccount("Mật khẩu phải có chữ in hoa");
            }

            if (ValidateAccount.isValidate(password, "[^a-z]+$")) {
                throw new ValidateAccount("Mật khẩu phải có chữ thường");
            }

            if (ValidateAccount.isValidate(password,"[^\\d]+$")) {
                throw new ValidateAccount("Mật khẩu phải có chữ số");
            }

            if (ValidateAccount.isValidate(password,"[^\\W]+$")) {
                throw new ValidateAccount("Mật khẩu phải có ký tự đặc biệt");
            }

            System.out.print("Họ tên: ");
            String name = scanner.nextLine();

            if (name.isEmpty()) {
                throw new ValidateAccount("Tài khoản không được để trống");
            }

            if (!ValidateAccount.isValidate(name, "^[\\p{L} .'-]+$")) {
                throw new ValidateAccount("Họ tên chỉ được gồm các chữ cái");
            }

            System.out.print("Ngày tháng năm sinh( ngày/tháng/năm): ");
            String birthday = scanner.nextLine();

            try {
                LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeException e) {
                throw new ValidateAccount("Ngày tháng năm sinh nhập vào không hợp lệ");
            }

            System.out.print("Số điện thoại: ");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.length() != 10) {
                throw new ValidateAccount("Số điện thoại không đúng");
            }

            if (ValidateAccount.isValidate(password, "[^\\D]+$")) {
                throw new ValidateAccount("Số điện thoại không hợp lệ");
            }

            this.account = account;
            this.password = password;
            this.name = name;
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.phoneNumber = phoneNumber;

            if (data.isExit(this)) {
                System.out.println("Tài khoản đã tồn tại");
                return false;
            } else {
                data.getAccountsList().add(this);
                return true;
            }

        } catch (ValidateAccount e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public String toString() {
        String result = "";
        result += "Tài khoản:           " + account + "\n";
        result += "Mật khẩu:            " + password + "\n";
        result += "Họ tên:              " + name + "\n";
        result += "Ngày tháng năm sinh: " + birthday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        result += "Số điện thoại:       " + phoneNumber + "\n";
        result += "======================================================================\n";
        return result;
    }

    public void update(Scanner scanner) {
        System.out.println("====================CHỈNH SỬA TÀI KHOẢN====================");
        try {

            System.out.print("Mật khẩu cũ: ");
            String oldPassword = scanner.nextLine();

            if (!oldPassword.equals(this.password)) {
                System.out.println("Nhập mật khẩu sai");
            } else {
                System.out.print("Mật khẩu mới: ");
                String newPassword = scanner.nextLine();

                if (!newPassword.isEmpty()) {

                    if (newPassword.equals(this.password)) {
                        System.out.println("Mật khẩu mới không được trùng với mật khẩu cũ");
                    }

                    if (newPassword.length() < 6) {
                        System.out.println("Mật khẩu có độ dài ít nhất 6 ký tự");
                    }

                    if (newPassword.matches("[^A-Z]+")) {
                        throw new ValidateAccount("Mật khẩu phải có chữ in hoa");
                    }

                    if (newPassword.matches("[^a-z]+")) {
                        throw new ValidateAccount("Mật khẩu phải có chữ thường");
                    }

                    if (newPassword.matches("[^0-9]+")) {
                        throw new ValidateAccount("Mật khẩu phải có chữ số");
                    }

                    if (newPassword.matches("[^A-Za-z0-9]+")) {
                        throw new ValidateAccount("Mật khẩu phải có ký tự đặc biệt");
                    }

                    password = newPassword;
                }
            }

            System.out.print("Họ tên mới: ");
            String name = scanner.nextLine();

            if (!name.isEmpty()) {
                Pattern pattern = Pattern.compile("[^A-Za-z]");
                Matcher matcher = pattern.matcher(account);
                boolean b = matcher.find();
                if (b) {
                    throw new ValidateAccount("Họ tên chỉ được gồm các chữ cái");
                }

                this.name = name;
            }

            System.out.print("Ngày tháng năm sinh( ngày/tháng/năm) mới: ");
            String birthday = scanner.nextLine();
            if (!birthday.isEmpty()) {
                try {
                    LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (DateTimeException e) {
                    throw new ValidateAccount("Ngày tháng năm sinh nhập vào không hợp lệ");
                }
            }

            System.out.print("Số điện thoại mới: ");
            String phoneNumber = scanner.nextLine();

            if (!phoneNumber.isEmpty()) {

                if (phoneNumber.length() != 10) {
                    throw new ValidateAccount("Số điện thoại không đúng");
                }

                Pattern pattern = Pattern.compile("[^0-9]");
                Matcher matcher = pattern.matcher(phoneNumber);
                Boolean b = matcher.find();
                if (b) {
                    throw new ValidateAccount("Số điện thoại không hợp lệ");
                }

                this.phoneNumber = phoneNumber;
            }
        } catch (ValidateAccount e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Cập nhật tài khoản thành công");
        }
    }

    public UserAccount(String account, String password, String name, String birthday, String phoneNumber) {
        if (number < 10) {
            id += "0" + String.valueOf(number);
        } else {
            id += String.valueOf(number);
        }
        this.account = account;
        this.password = password;
        this.name = name;
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.phoneNumber = phoneNumber;
        number++;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
