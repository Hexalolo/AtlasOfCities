import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class AtlasEngineTest {
    private AtlasEngine engine;
    private String line;
    private City city;
    private ArrayList<String> listOfLines;
    private ArrayList<City> listOfCities;
    private ArrayList<String> listOfCentralPoints;
    private ArrayList<City> listOfDots;

    @BeforeEach
    void setUp() {
        engine = new AtlasEngine();
    }

    @Test
    void fileNotFoundException() {
        Assertions.assertThrows(IOException.class, () -> {
            engine.readSingleLineFromFile("noExistingFile.txt");
        });

    }

    @Test
    void hasReadSingleLine() throws IOException {
        assertThat(engine.readSingleLineFromFile("listOfCities.txt"), is(not(emptyOrNullString())));

    }

    @Test
    void hasReadAllLines() throws IOException {
        listOfLines = engine.readAllLinesFromFile("listOfCities.txt");
        assertThat(listOfLines, is(notNullValue()));
        assertThat(listOfLines.get(0), is(not(emptyOrNullString())));
        assertThat(listOfLines.get(1), is(not(emptyOrNullString())));
    }

    @Test
    void hasCreatedCityWithTrueValue(){
        line = "Wieruszów; 51°17′43″N; 18°09′07″E; 8; true;";
        city = engine.createCityFromStringLine(line);
        assertThat(city.getName(), is("Wieruszów"));
        assertThat(city.getSize(), is(equalTo(8)));
        assertThat(city.getCoordinateX(), is(not(emptyOrNullString())));
        assertThat(city.getCoordinateY(), is(not(emptyOrNullString())));
        assertThat(city.isCentralPoint(), is(equalTo(true)));

    }

    @Test
    void hasCreatedCityWithFalseValue() {
        line = "Bolesławiec; 51°11′55″N; 18°11′26″E; 1; false;";
        city = engine.createCityFromStringLine(line);
        assertThat(city.isCentralPoint(), is(equalTo(false)));
    }

    @Test
    void hasFilledArrayOfCities() {
        listOfLines = new ArrayList<String>();
        line = "Wieruszów; 51°17′43″N; 18°09′07″E; 8; true;";
        listOfLines.add(line);
        line = "Bolesławiec; 51°11′55″N; 18°11′26″E; 1; false;";
        listOfLines.add(line);
        listOfCities = engine.fillArrayOfCitiesFromStringArray(listOfLines);
        assertThat(listOfCities, is(not(Matchers.<City>empty())));
        assertThat(listOfCities.get(0), is(notNullValue()));

    }

    @Test
    void returnedCentralPoints() {
        listOfLines = new ArrayList<String>();
        line = "Bolesławiec; 51°11′55″N; 18°11′26″E; 1; false;";
        listOfLines.add(line);
        line = "Wieruszów; 51°17′43″N; 18°09′07″E; 8; true;";
        listOfLines.add(line);
        listOfCities = engine.fillArrayOfCitiesFromStringArray(listOfLines);
        listOfCentralPoints = new ArrayList<String>();
        listOfCentralPoints = engine.returnArrayOfCentralPoints();
        assertThat(listOfCentralPoints.get(0), is(equalTo("Wieruszów")));
    }

    @Test
    void returnedArrayOfDots() {
        listOfLines = new ArrayList<String>();
        line = "Wieruszów; 51°17′43″N; 18°09′07″E; 8; true;";
        listOfLines.add(line);
        line = "Bolesławiec Poza X; 51°04′55″N; 18°11′26″E; 1; false;";
        listOfLines.add(line);
        line = "Bolesławiec Poza Y; 51°16′55″N; 18°23′26″E; 1; false;";
        listOfLines.add(line);
        line = "Bolesławiec Dobry; 51°05′55″N; 17°58′26″E; 1; false;";
        listOfLines.add(line);
        line = "Wieruszów Drugi; 51°29′40″N; 18°21′06″E; 8; true;";
        listOfLines.add(line);
        listOfCities = engine.fillArrayOfCitiesFromStringArray(listOfLines);
        listOfDots = new ArrayList<City>();
        listOfDots = engine.returnArrayOfDots(listOfCities.get(0).getRelativeX(), listOfCities.get(0).getRelativeY());
        assertThat(listOfDots.size(), is(equalTo(3)));
    }

}