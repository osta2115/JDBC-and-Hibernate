package pl.sda.introduction.jdbc;

public class SQLs {
    static final String CREATE_MOVIES_TABLE = """
            create table if not exists movies (
                id int not null auto_increment,
                title varchar(100) not null,
                primary key(id)
            );
            """;

    static final String INSERT_SIMPLE_MOVIE = """
            insert into movies (title)
            values ('Avengers');
            """;

    static final String DELETE_MOVIE_BY_ID = "delete from movies m where m.id = ?;";

    static final String GET_MOVIE_BY_TITLE = """
            select id, title from movies where title = ?
            """;

    static final String DELETE_ALL_MOVIES = "delete from movies;";
}
