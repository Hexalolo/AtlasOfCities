import java.io.IOException;

public class AtlasOfCities {

    public static void main(String[] args) {

        AtlasEngine atlas = new AtlasEngine();

        try {
            atlas.fillArrayOfCitiesFromStringArray(atlas.readAllLinesFromFile("listOfCities.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Window mainWindow = new Window(atlas);
        mainWindow.generateCentralPointButtons(atlas.returnArrayOfCentralPoints());

    }

}
