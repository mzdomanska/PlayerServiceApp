import com.kchmielewski.sda.java.spring01java.model.Player;
import com.kchmielewski.sda.java.spring01java.repository.PlayerRepository;
import com.kchmielewski.sda.java.spring01java.service.PlayerService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class PlayerServiceTest {

    private PlayerService service = new PlayerService(mock(PlayerRepository.class));
    //private PlayerService service = mock(PlayerService.class);

    //TE TESTY W TAKIEJ POSTACI NIE MAJA SENSU, TRZEBA BY BYLO ZROBIC TESTY W STYLU SqlConfigTest
    @Test
    public void getAllPlayersShouldReturnListOfPlayers() {

        List<Player> expectedList = new ArrayList<>();
        expectedList.add(new Player("Sherlock","Holmes"));
        expectedList.add(new Player("John","Wattson"));

        List<Player> resultPlayers = service.getAllPlayers();

        assertThat(resultPlayers).isEqualTo(expectedList);
    }

    @Test
    public void addNewPlayerShouldThrowAnExceptionForNullParameter() {
        assertThatThrownBy(()->service.addNewPlayer(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void addNewPlayerShouldThrowAnExceptionIfPlayerToBeAddedHasAnId() {
        assertThatThrownBy(()->service.addNewPlayer(new Player(1,"Frank","Linux"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addNewPlayerShouldAddAPlayerToAList() {
        Player newPlayer = new Player("Chet","Faker");
        service.addNewPlayer(newPlayer);
        assertThat(service.getAllPlayers()).contains(newPlayer);
    }

    @Test
    public void deletePlayerByIdShouldThrowAnExceptionForNullParameter() {
        assertThatThrownBy(()->service.deletePlayerById(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void deletePlayerByIdShouldThrowAnExceptionIfPlayerWithGivenIdIsNotInDB() {
        assertThatThrownBy(()->service.deletePlayerById(20)).isInstanceOf(IllegalArgumentException.class);
    }

//    @Test
//    public void deletePlayerShouldRemoveOnePlayerFromAList() {
//        Player playerToRemove = new Player("Alex","Linux");
//        service.deletePlayerByName(playerToRemove);
//        assertThat(service.getAllPlayers()).doesNotContain(playerToRemove);
//    }
}
