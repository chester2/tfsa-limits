package io.github.chester2.tfsalimits.dao;

import io.github.chester2.tfsalimits.model.Limit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ApplicationDao {
    private class LimitMapper implements RowMapper<Limit> {
        @Override
        public Limit mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Limit(
                resultSet.getInt("year"),
                resultSet.getBigDecimal("amount")
            );
        }
    }

    private final JdbcTemplate jdbcTemplate;

    public ApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Limit> getAll() {
        return jdbcTemplate.query(
            "SELECT * FROM limits",
            new LimitMapper()
        );
    }

    public Limit get(int year) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM limits WHERE year = ?",
            new Object[] { year },
            new LimitMapper()
        );
    }

    public int put(Limit limit) {
        return jdbcTemplate.update(
            "INSERT INTO limits (year, amount) VALUES (?, ?) ON DUPLICATE KEY UPDATE amount = ?",
            limit.getYear(),
            limit.getAmount(),
            limit.getAmount()
        );
    }

    public int delete(int year) {
        return jdbcTemplate.update(
            "DELETE FROM limits WHERE year = ?",
            year
        );
    }
}
