package pl.sda.introduction.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.sda.introduction.jdbc.SQLs.*;

@Slf4j
public class MainJdbc {

    //private static final Logger LOGGER = LoggerFactory.getLogger(MainJdbc.class);

    public static void main(String[] args) {
        try(var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "admin", "Password1");
            var statement = connection.createStatement();
            var preparedStatement = connection.prepareStatement(GET_MOVIE_BY_TITLE)) {
            log.info("Successfully connected to database");
//            statement.execute(CREATE_MOVIES_TABLE);
            statement.execute(INSERT_SIMPLE_MOVIE);
//            statement.execute(DELETE_MOVIE_BY_ID);
//            statement.execute(DELETE_ALL_MOVIES);
//            int movieId = 5;
            String movieTitle = "Avengers";
            preparedStatement.setString(1, movieTitle);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int movieId = resultSet.getInt("id");
                resultSet.getString("title");
                log.info("Movie id: {}, movie title: {}", movieId, movieTitle);
            }
        } catch (SQLException e) {
            log.error("Something went wrong: ", e);
        }
    }
}
