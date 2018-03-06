import com.kchmielewski.sda.java.spring01java.model.Player;
import com.kchmielewski.sda.java.spring01java.repository.PlayerRepository;
import com.kchmielewski.sda.java.spring01java.service.PlayerService;
import com.kchmielewski.sda.java.spring01java.web.PlayerController;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PlayerControllerTest {

    private final PlayerService service = new PlayerService(mock(PlayerRepository.class));
    private final PlayerController controller = new PlayerController(service,mock(MessageSource.class));
    private MockMvc mvc;

    @Before
    public void setup() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");

        mvc = standaloneSetup(controller).setViewResolvers(resolver).build();
    }

    @Test
    public void shouldReturnPlayersDisplayView() throws Exception {

        MvcResult result = mvc.perform(get("/players"))
                .andExpect(status().isOk()).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualTo("playersDisplay");
    }

    @Test
    public void shouldReturnPlayersDisplayViewAndPassNewPlayerAsParameter() throws Exception {

        MvcResult result = mvc.perform(post("/players").param("firstName","Adam")
                .param("lastName","Lenovo")).andExpect(status().isOk()).andReturn();

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(result.getModelAndView().getModel().values().contains(new Player("Adam","Lenovo")));
        soft.assertThat(result.getModelAndView().getViewName()).isEqualTo("playersDisplay");
        soft.assertAll();
    }

    @Test
    public void shouldReturnDisplayAllPlayersViewAndPassPlayerToDeleteAsParameter() throws Exception {

        MvcResult result = mvc.perform(post("/deletePlayerByName").param("playerToDelete","Player1"))
                .andExpect(status().isOk()).andReturn();
        assertThat(result.getRequest().getParameter("playerToDelete")).isEqualTo("Player1");
        assertThat(result.getModelAndView().getViewName()).isEqualTo("displayAllPlayers");
    }
}
