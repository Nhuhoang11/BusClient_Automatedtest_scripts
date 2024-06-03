package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.DBConfig.*;

//class Child {
//    private String name;
//    private String nickname;
//
//    public Child(String name, String nickname) {
//        this.name = name;
//        this.nickname = nickname;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//}

public class Parent_GetChild {
    public static List<String[]> getChildNameAndNickname(String email) throws Exception {
        String query = "SELECT c.child_name, c.nickname " +
                "FROM \"public\".\"Parent\" p " +
                "JOIN \"public\".\"Child\" c ON p.id = c.parent_id " +
                "WHERE p.email = ?";

        List<String[]> children = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);
            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();
            // Lặp qua tất cả các hàng trong ResultSet
            while (resultSet.next()) {
                String childName = resultSet.getString("child_name");
                String childNickname = resultSet.getString("nickname");

                // Tạo đối tượng Child và thêm vào danh sách
                children.add(new String[]{childName, " (", childNickname, ")"});

                // In ra thông tin của mỗi child
                System.out.println("Child Name (nickname): " + childName + " (" + childNickname + ")");
                System.out.println("------------------------------------------");
            }
        }
        return children;
    }

    public static void getChildOfParent(String email) throws Exception {
        String query = "SELECT p.user_name, p.email, c.child_name, c.nickname " +
                "FROM \"public\".\"Parent\" p " +
                "JOIN \"public\".\"Child\" c ON p.id = c.parent_id " +
                "WHERE p.email = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Đặt giá trị cho tham số email
            preparedStatement.setString(1, email);

            // Thực thi truy vấn
            ResultSet resultSet = preparedStatement.executeQuery();
            // Lặp qua tất cả các hàng trong ResultSet
            while (resultSet.next()) {
                String parentName = resultSet.getString("user_name");
                String parentEmail = resultSet.getString("email");
                String childName = resultSet.getString("child_name");
                String childNickname = resultSet.getString("nickname");

                // In ra thông tin của mỗi child
                System.out.println("Parent Name: " + parentName);
                System.out.println("Parent Email: " + parentEmail);
                System.out.println("Child Name: " + childName);
                System.out.println("Child Nickname: " + childNickname);
                System.out.println("------------------------------------------");
            }
        }
    }
}
