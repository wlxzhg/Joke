package cn.xiaozhigang.joke.dao;

import cn.xiaozhigang.joke.domain.Joke;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JokeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Joke findJokeById(final Integer id) {
        final Joke joke = new Joke();
        String sql = " SELECT content FROM joke WHERE id = ? ";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                joke.setId(id);
                joke.setContent(resultSet.getString("content"));
            }
        });
        return joke;
    }

    public List<Joke> findByContent(final String content) {
        if(content == null) return null;
        String sql = " SELECT id FROM joke WHERE content = ?";
        return jdbcTemplate.query(sql, new Object[]{content},
                new RowMapper<Joke>() {
                    public Joke mapRow(ResultSet resultSet, int i) throws SQLException {
                        Joke joke = new Joke();
                        joke.setId(resultSet.getInt("id"));
                        joke.setContent(content);
                        return joke;
                    }
                });
    }

    public List<Joke> findAllJoke() {
        String sql = " SELECT id,content FROM joke";
        return jdbcTemplate.query(sql,
                new RowMapper<Joke>() {
                    public Joke mapRow(ResultSet resultSet, int i) throws SQLException {
                        Joke joke = new Joke();
                        joke.setId(resultSet.getInt("id"));
                        joke.setContent(resultSet.getString("content"));
                        return joke;
                    }
                });
    }

    public int updateJokeById(Joke joke) {
        if(joke == null || joke.getId() == null) return 0;
        String sql = " UPDATE joke SET content = ? WHERE id = ? ";
        return jdbcTemplate.update(sql, joke.getContent(), joke.getId());
    }

    public int findMaxId() {
        String sql = " SELECT MAX(id) FROM joke ";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public int findMinId() {
        String sql = " SELECT MIN(id) FROM joke ";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
}
