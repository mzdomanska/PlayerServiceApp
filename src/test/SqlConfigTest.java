import com.kchmielewski.sda.java.spring01java.config.SqlConfig;
import com.kchmielewski.sda.java.spring01java.entity.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SqlConfig.class)
@ActiveProfiles("test")
public class SqlConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void smoke() {
        assertThat(dataSource).isNotNull();
    }

    @Test
    public void selectAllPlayers() {

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        List<PlayerEntity> playerEntities = template.query("SELECT * FROM player", (rs, rowNum) ->
                new PlayerEntity(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName")));

        assertThat(playerEntities).hasSize(2);
    }
}
