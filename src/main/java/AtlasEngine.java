import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AtlasEngine {
    private ArrayList<City> listOfCities;
    private BufferedReader reader;
    private int scaleFactor = 3;
    private int centralPointRelativeX;
    private int centralPointRelativeY;

    private void createBufferedReader(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    private void closeBufferedReader() throws IOException {
        reader.close();
    }

    public String readSingleLineFromFile(String fileName) throws IOException {
        String line = "";
        createBufferedReader(fileName);
        line = reader.readLine();
        closeBufferedReader();
        return line;
    }

    public ArrayList<String> readAllLinesFromFile(String fileName) throws IOException {
        String line = "";
        ArrayList<String> arrayOfLines = new ArrayList<String>();
        createBufferedReader(fileName);
        line = reader.readLine();
        while(line != null){
            arrayOfLines.add(line);
            line = reader.readLine();
        }
        closeBufferedReader();
        return arrayOfLines;
    }

    public City createCityFromStringLine(String line){
        String[] arrayFromLine = line.split(";");
        int size = Integer.parseInt(arrayFromLine[3].trim());
        boolean isCentralPoint = Boolean.parseBoolean(arrayFromLine[4].trim());
        City city = new City(arrayFromLine[0], arrayFromLine[1], arrayFromLine[2], size, isCentralPoint);
        return city;
    }

    public ArrayList<City> fillArrayOfCitiesFromStringArray(ArrayList<String> listOfLines){
        listOfCities = new ArrayList<City>();
        listOfLines.forEach(line -> {
            listOfCities.add(createCityFromStringLine(line));
        });
        return listOfCities;
    }

    public ArrayList<City> getListOfCities(){
        return listOfCities;
    }

    public ArrayList<String> returnArrayOfCentralPoints(){
        ArrayList<String> listOfCentralPoints = new ArrayList<String>();
        for(int i = 0; i < listOfCities.size(); i++){
            if(listOfCities.get(i).isCentralPoint()){
                listOfCentralPoints.add(listOfCities.get(i).getName());
            }
        }
        return listOfCentralPoints;
    }

    public int getScaleFactor(){
        return scaleFactor;
    }

    public void setScaleFactor(int newScaleFactor){
        scaleFactor = newScaleFactor;
    }

    public ArrayList<City> returnArrayOfDots(int centralPointRelativeX, int centralPointRelativeY){
        ArrayList<City> listOfDots = new ArrayList<City>();
        this.centralPointRelativeX = centralPointRelativeX;
        this.centralPointRelativeY = centralPointRelativeY;

        for(int i = 0; i < listOfCities.size(); i++){
            if (isWithinBounds(listOfCities.get(i), centralPointRelativeX, centralPointRelativeY)) {
                        listOfDots.add(listOfCities.get(i));
            }
        }
        return listOfDots;
    }

    public boolean isWithinBounds(City actualCity, int centralPointRelativeX, int centralPointRelativeY){

        if(     (actualCity.getRelativeX() > centralPointRelativeX - 240*scaleFactor) &&
                (actualCity.getRelativeX() < centralPointRelativeX + 240*scaleFactor) &&
                (actualCity.getRelativeY() > centralPointRelativeY - 240*scaleFactor) &&
                (actualCity.getRelativeY() < centralPointRelativeY + 240*scaleFactor)) {
            return true;
        }
        else {
            return false;
        }
    }
}
