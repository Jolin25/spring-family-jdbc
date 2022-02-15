package top.jrl.springfamily.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.CommonDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 配置单数据源
 * 1.写好pom里的依赖
 * 2.application.properties里写url、账号、密码等必要信息
 * （当然这个例子用的是内嵌的h2，所以其实什么配置都不需要写）
 *
 * @author jrl
 * @date 2022-2-15
 */
@Slf4j
@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {
    /**
     * todo 这个是什么
     *
     * @author jrl
     * @date 2022-2-15
     */
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    /**
     * 这里只是输出相关信息查看一下
     *
     * @param
     * @return
     * @date 2022-2-15
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("==================start======================");
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info("dataSource.toString():" + dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info("conn.toString():" + conn.toString());
        conn.close();
    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> log.info(row.toString()));
    }
}
