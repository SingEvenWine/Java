package Lab3;

import java.sql.*;

public class StudentDatabase {
    // 定义数据库连接URL，指向本地的HSQLDB服务器上的默认test数据库
    // shutdown=true 选项确保在JVM关闭时，数据库会正常关闭
    // hsqldb.default_silent=true 选项用于减少数据库运行时的日志输出
    private static final String DB_URL = "jdbc:hsqldb:hsql://localhost/;shutdown=true;hsqldb.default_silent=true";

    // 定义数据库连接用户名，默认为"sa"
    private static final String DB_USER = "sa";

    // 定义数据库连接密码，默认为空字符串
    private static final String DB_PASSWORD = "";

    // StudentDatabase构造函数
    public StudentDatabase() {
        // 使用try-with-resources语句确保Connection资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 创建一个Statement对象，用于执行SQL语句
            Statement stmt = conn.createStatement();

            // 定义一个创建表格的SQL语句，如果"students"表格不存在，则创建该表格
            // students表格包含两个字段：name（VARCHAR(255)类型，用于存储学生姓名）
            // 和student_number（VARCHAR(255)类型，用于存储学生学号）
            String sql = "CREATE TABLE IF NOT EXISTS students (name VARCHAR(255), student_number VARCHAR(255))";

            // 执行创建表格的SQL语句
            stmt.execute(sql);
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 检查指定学号是否已存在于students表中
    private boolean studentNumberExists(String studentNumber) {
        // 定义一个SQL查询语句，通过student_number查找学生
        String sql = "SELECT 1 FROM students WHERE student_number = ?";

        // 使用try-with-resources语句确保Connection和PreparedStatement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置查询参数：学号
            pstmt.setString(1, studentNumber);

            // 执行查询，并获取查询结果集
            ResultSet rs = pstmt.executeQuery();

            // 如果查询结果集非空（即找到了匹配的学号），返回true
            return rs.next();
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
            return false;
        }
    }

    // 向students表中添加新学生
    public void addStudent(Student student) {
        // 首先检查学号是否已经存在
        if (studentNumberExists(student.getStudentNumber())) {
            System.out.println("学生学号已存在，无法添加");
            return;
        }

        // 定义一个SQL插入语句，向students表中添加一条新记录
        String sql = "INSERT INTO students (name, student_number) VALUES (?, ?)";

        // 使用try-with-resources语句确保Connection和PreparedStatement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置插入参数：学生姓名和学号
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentNumber());

            // 执行插入操作
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 显示所有学生信息
    public void displayAllStudents() {
        // 定义一个SQL查询语句，用于获取students表中的所有记录
        String sql = "SELECT * FROM students";

        // 使用try-with-resources语句确保Connection、Statement和ResultSet资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // 遍历查询结果集
            while (rs.next()) {
                // 从结果集中提取学生的姓名和学号，然后输出
                System.out.println("Name: " + rs.getString("name") + ", Student Number: " + rs.getString("student_number"));
            }
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 按学号查找学生
    public void findStudentByStudentNumber(String studentNumber) {
        // 定义一个SQL查询语句，用于根据学号查找学生
        String sql = "SELECT * FROM students WHERE student_number = ?";

        // 使用try-with-resources语句确保Connection和PreparedStatement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置查询参数：学号
            pstmt.setString(1, studentNumber);

            // 执行查询，并获取查询结果集
            ResultSet rs = pstmt.executeQuery();

            // 如果查询结果集非空（即找到了匹配的学号），输出学生的姓名和学号
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Student Number: " + rs.getString("student_number"));
            } else {
                // 如果未找到匹配的学号，输出提示信息
                System.out.println("未找到该学生");
            }
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 按姓名查找学生
    public void findStudentByName(String name) {
        // 定义一个SQL查询语句，用于根据姓名查找学生
        String sql = "SELECT * FROM students WHERE name = ?";

        // 使用try-with-resources语句确保Connection和PreparedStatement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置查询参数：姓名
            pstmt.setString(1, name);

            // 执行查询，并获取查询结果集
            ResultSet rs = pstmt.executeQuery();

            // 如果查询结果集非空（即找到了匹配的姓名），输出学生的姓名和学号
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Student Number: " + rs.getString("student_number"));
            } else {
                // 如果未找到匹配的姓名，输出提示信息
                System.out.println("未找到该学生");
            }
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 按学号删除学生信息
    public void deleteStudentByStudentNumber(String studentNumber) {
        // 定义一个SQL删除语句，用于根据学号删除学生
        String sql = "DELETE FROM students WHERE student_number = ?";

        // 使用try-with-resources语句确保Connection和PreparedStatement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置删除参数：学号
            pstmt.setString(1, studentNumber);

            // 执行删除操作，并获取受影响的行数
            int rowsAffected = pstmt.executeUpdate();

            // 如果受影响的行数大于0，表示删除成功
            if (rowsAffected > 0) {
                System.out.println("成功删除该学生");
            } else {
                // 如果受影响的行数等于0，表示未找到匹配的学号
                System.out.println("未找到该学生");
            }
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }

    // 按指定字段排序并显示学生信息
    public void displayStudentsSortedBy() {
        // 定义一个SQL查询语句，用于按学号升序排序并选择所有学生
        String sql = "SELECT * FROM students ORDER BY student_number";

        // 使用try-with-resources语句确保Connection和Statement资源会被自动关闭
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             // 执行查询，并获取查询结果集
             ResultSet rs = stmt.executeQuery(sql)) {

            // 遍历查询结果集，输出每个学生的姓名和学号
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Student Number: " + rs.getString("student_number"));
            }
        } catch (SQLException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }
}

