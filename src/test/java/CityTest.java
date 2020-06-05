import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class CityTest {
    private City city1;
    private City city2;

    @BeforeEach
    void setUp() {
        city1 = new City("Wieruszów"," 18°09′07″E"," 51°17′43″N",8, true);
        city2 = new City("Bolesławiec", " 19°11′26″E", " 53°11′55″N", 1, false);
    }

    @Test
    void getRelativeX() {
        assertThat(city1.getRelativeX(), is(equalTo(51*3600 + 17*60 + 43)));
        assertThat(city2.getRelativeX(), is(equalTo(53*3600 + 11*60 + 55)));

    }

    @Test
    void getRelativeY() {
        assertThat(city1.getRelativeY(), is(equalTo(18*3600 + 9*60 + 7)));
        assertThat(city2.getRelativeY(), is(equalTo(19*3600 + 11*60 + 26)));
    }

}