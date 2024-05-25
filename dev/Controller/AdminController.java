package Controller;

import Domain.*;

public class AdminController {
    IO_Data io_data;
    String[] UserPass;
    String UserKind;
//    public AdminController(String[] userPass, String userKind) {
//        UserPass = userPass;
//        UserKind = userKind;
//    }
    public AdminController() {
        io_data = new IO_Data();
    }

    public void PrintEmployees(){
        System.out.println(io_data.ImportEmployees("C:\\BGU\\Year 2\\Semester 2\\Analysis & Design Of Software Systems\\ADSS_Group_AT\\dev\\Data\\Employees.csv"));
    }
}
