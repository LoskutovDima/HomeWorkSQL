package LoskutovDV;

import java.sql.*;

public class HomeWork {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty5%";
    private static final String CREATE_STUDENT_TABLE =
            "CREATE TABLE Student(id int auto_increment primary key,fio varchar(50),sex varchar(1),id_group int, foreign key(id_group) references Groupa(id))";
    private static final String CREATE_CURATOR_TABLE =
            "CREATE TABLE Curator(id int auto_increment primary key, fio varchar(50))";
    private static final String CREATE_GROUPP_TABLE =
            "CREATE TABLE Groupa (id  int auto_increment primary key, name varchar(50),id_curator int, foreign key (id_curator) references Curator(id))";
    private static final String INSERT_INTO_STUDENT_TABLE = "INSERT INTO Student(fio, sex, id_group) VALUES ('Иванов Иван Иванович', + 'м', '1'), " +
            "('Петров Петр Петрович', 'м', '1')," + " ('Смирнов Илья Алексеевич','м', '1')," +
            "('Смирнова Ольга Викторовна','ж','1')," + "('Петрова Светлана Владимировна','ж','1')," +
            "('Сидиров Василий Петрович','м','2')," + "('Кошкина Лариса Васильевна','ж','2')," +
            "('Иванов Иван Петрович','м','2')," + "('Сухарева Ира Васильевна','ж','2')," +
            "('Круглов Игнат Иванович','м', '2')," + "('Воробьев Иван Иванович','м','3')," +
            "('Птичкина Инна Петровна','ж','3')," + "('Краснов Василий Васильевич','м', '3')," + "('Синичкин Юрий Иванович','м', '3'),"+"('Васильчук Раиса Викторовна','ж', '3')";

    private static final String INSERT_INTO_CURATOR_TABLE = "INSERT INTO Curator(fio) VALUES ('Муромкин Юрий Николаевич')," +
            "('Барочкин Василий Петрович'), ('Качанов Дмитрий Анатольевич'), ('Лебедев Эдуард Аркадьевич')";
    private static final String INSERT_INTO_GROUPP_TABLE = "INSERT INTO Groupa(name, id_curator) VALUES ('Робототехника', '1' ),('Программирование','2'),('Тестирование','3')";

    private static final String GET_INFO_FOR_STUDENT_AND_NAME_GROUP = "SELECT student.id, student.fio, groupa.name, curator.fio FROM Student" +
            " JOIN Groupa ON Student.id_group = Groupa.id JOIN Curator ON Groupa.id_curator = Curator.id";
    private static final String GET_NUMBER_STUDENT = "SELECT COUNT(id) FROM Student";
    private static final String GET_MALE_STUDENT = "SELECT id, fio, sex FROM Student WHERE sex = 'ж'";
    private static final String UPDATE_GROUPA_TABLE = "UPDATE Groupa SET id_curator = '4' WHERE id_curator = '3'";
    private static final String GET_LIST_GRUOPA_AND_NAME_CURATOR = "SELECT groupa.id,groupa.name,curator.fio FROM Groupa JOIN Curator " +
            "ON Groupa.id_curator = Curator.id";

    public  void  createStudentTable(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute(CREATE_STUDENT_TABLE);
        }
    }
        public void createCuratorTable(Connection connection) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                statement.execute(CREATE_CURATOR_TABLE);
            }
        }

        public void createGrouppTable(Connection connection) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                statement.execute(CREATE_GROUPP_TABLE);
            }
        }
        public void insertIntoDataStudent(Connection connection) throws SQLException {
        try (Statement statement = connection.prepareStatement(INSERT_INTO_STUDENT_TABLE)){
          int row = statement.executeUpdate(INSERT_INTO_STUDENT_TABLE);
            System.out.println("Успешно добавлено " + row + " записей в таблицу Student");
        }
        }
        public void insertIntoDataCurator(Connection connection) throws SQLException {
        try (Statement statement = connection.prepareStatement(INSERT_INTO_CURATOR_TABLE)) {
            int row = statement.executeUpdate(INSERT_INTO_CURATOR_TABLE);
            System.out.println("Успешно добавлено " + row + " записи в таблицу Curator");
        }
        }
        public void insertIntoDataGroupp(Connection connection) throws SQLException {
        try(Statement statement = connection.prepareStatement(INSERT_INTO_GROUPP_TABLE)) {
            int row = statement.executeUpdate(INSERT_INTO_GROUPP_TABLE);
            System.out.println("Успешно добавлено "+ row +" записи в таблицу Groupa");
        }
        }

        public void getInfoForStudent(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_INFO_FOR_STUDENT_AND_NAME_GROUP);
            while (resultSet.next()) {
                int StudentId = resultSet.getInt(1);
                String StudentFio = resultSet.getString("fio");
                String GruopName = resultSet.getString("name");
                String CuratorName = resultSet.getString("fio");
                String rowData = String.format("ID: %s, FIO: %s, NAME: %s, FIO: %s", StudentId, StudentFio, GruopName, CuratorName);
                System.out.println(rowData);
            }

        }
        }
        public void getNumberStudent(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_NUMBER_STUDENT);
            while (resultSet.next()) {
                int Number = resultSet.getInt(1);
                System.out.println("Количество студентов " + Number);

            }
        }
        }
        public void getMaleStudent(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_MALE_STUDENT);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fio = resultSet.getString("fio");
                String sex = resultSet.getString("sex");
                String resultRow = String.format("ID: %s,FIO: %s,SEX: %s", id, fio,sex);
                System.out.println(resultRow);
            }
        }
        }
        public void updateGroupaTable(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(UPDATE_GROUPA_TABLE);
            System.out.println("Данные в таблицу Groupa успешно обновлены");
        }
        }
        public void getListGroupaAndCuratorName(Connection connection) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_LIST_GRUOPA_AND_NAME_CURATOR);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String fio = resultSet.getString("fio");
                String resultRow = String.format("ID: %s, NAME: %s, FIO_CURATOR: %s", id,name,fio);
                System.out.println(resultRow);
            }
        }
        }






    public static void main(String[] args) throws SQLException {
        HomeWork homeWorkSql = new HomeWork();


        try(Connection connection = DriverManager.getConnection(CONNECTION_URL,USER,PASSWORD)) {
        homeWorkSql.createCuratorTable(connection);
        homeWorkSql.createGrouppTable(connection);
        homeWorkSql.createStudentTable(connection);
        System.out.println("Таблицы успешно созданы");
        homeWorkSql.insertIntoDataCurator(connection);
        homeWorkSql.insertIntoDataGroupp(connection);
        homeWorkSql.insertIntoDataStudent(connection);
        homeWorkSql.getInfoForStudent(connection);
        homeWorkSql.getNumberStudent(connection);
        homeWorkSql.getMaleStudent(connection);
        homeWorkSql.updateGroupaTable(connection);
        homeWorkSql.getListGroupaAndCuratorName(connection);


        }


    }


}
