package com.pet.project.db;


import com.pet.project.exceptions.SQLReturnedIncorrectDataException;
import com.pet.project.exceptions.TestErrorException;
import com.pet.project.exceptions.TestFrameworkCoreException;
import com.pet.project.logging.Logger;
import com.pet.project.properties.PropertiesSupplier;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class SQL {

    private static ThreadLocal<SQL> instances;
    private final JdbcTemplate jdbcTemplate;
    private final DatabaseConfiguration cfg = ConfigFactory.create(DatabaseConfiguration.class);

    private SQL() {
        this.jdbcTemplate = new JdbcTemplate(dataSource());
    }

    public static SQL execute() {
        if (Objects.isNull(instances)) {
            instances = new ThreadLocal<>();
        }

        if (Objects.isNull(instances.get())) {
            instances.set(new SQL());
        }
        return instances.get();
    }

    private DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(cfg.getDriverName());
        ds.setUrl(cfg.getDbUrl());
        ds.setUsername(cfg.getDbUsername());
        ds.setPassword(cfg.getDbPassword());
        return ds;
    }

    public Map<String, Object> selectTop1(String sql, Object... args) {
        List<Map<String, Object>> maps = selectAndCheckNotEmpty(sql, args);
        return maps.get(0);
    }

    public Map<String, Object> selectTop1(String sql) {
        List<Map<String, Object>> maps = selectAndCheckNotEmpty(sql);
        return maps.get(0);
    }

    public List<Map<String, Object>> selectAndCheckNotEmpty(String sql, Object... args) {
        List<Map<String, Object>> select = select(sql, args);
        checkNonEmptyResponse(sql, select, args);
        return select;
    }

    public List<Map<String, Object>> selectAndCheckNotEmpty(String sql) {
        List<Map<String, Object>> selectRes = select(sql);
        checkNonEmptyResponse(sql, selectRes, null);
        return selectRes;
    }

    private void checkNonEmptyResponse(String sql, List<Map<String, Object>> select, Object[] args) {
        if (select.isEmpty()) {
            Logger.log().error("SQL responded with empty response");
            Logger.log().error("SQL: {}", sql);
            if (Objects.nonNull(args)) {
                Logger.log().error("Args: {}", args);
            }
            Logger.log().error("Response: {}", select);
            throw new SQLReturnedIncorrectDataException("SQL responded with empty response. See details above");
        }
    }

    public <T> List<T> selectMultipleObjects(String sql, Class<T> mappedClass, Object... args) {
        List<T> select = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(mappedClass));
        if (select.isEmpty()) {
            Logger.log().error("SQL responded with empty response");
            Logger.log().error("SQL: {}", sql);
            Logger.log().error("Args: {}", args);
            Logger.log().error("Response: {}", select);
            throw new SQLReturnedIncorrectDataException("SQL responded with empty response. See details above");
        }
        return select;

    }

    public List<Map<String, Object>> select(String sql) {
        return select(sql, null);
    }

    public String selectString(String sql, Object... args) {
        List<Map<String, Object>> select = selectAndCheckNotEmpty(sql, args);
        return String.valueOf(select.get(0).values().toArray()[0]);
    }

    public String selectCount(String sql, Object... args) {
        List<Map<String, Object>> select = select(sql, args);
        if (select.size() != 1) {
            throw new TestFrameworkCoreException("SQL returned more or less than 1 cell. result:", select);
        }

        return String.valueOf(select.get(0).getOrDefault(StringUtils.EMPTY, -1));
    }

    public List<Map<String, Object>> select(String sql, Object... args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Object> task = () -> {
            List<Map<String, Object>> result;
            Logger.log().info("Executing SQL query: {} with params {}",
                    sql, args);
            if (Objects.isNull(args)) {
                result = jdbcTemplate.queryForList(sql);
            } else {
                result = jdbcTemplate.queryForList(sql, args);
            }
            Logger.log().info("SQL response: {}", result);
            return result;
        };
        Future<Object> future = executor.submit(task);
        try {
            List<Map<String, Object>> result = (List<Map<String, Object>>) future.get(PropertiesSupplier.getPropertyAsInteger("core.test.framework.sql.timeout.sec", 300), TimeUnit.SECONDS);
            return result;
        } catch (TimeoutException ex) {
            throw new TestErrorException("SQL execution time was too long");
        } catch (InterruptedException | ExecutionException e) {
            throw new TestFrameworkCoreException(e);
        }
    }

    public int insert(String sql, Object... args) {
        Logger.log().info("Executing INSERT/UPDATE query: {} with params {}", sql, args);
        return jdbcTemplate.update(sql, args);
    }

    public int update(String sql, Object... args) {
        return insert(sql, args);
    }

}
