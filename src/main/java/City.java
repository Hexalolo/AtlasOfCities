public class City {
    private String name;
    private String coordinateX;
    private String coordinateY;
    private int size;
    private boolean isCentralPoint;

    public City(String name, String coordinateY, String coordinateX, int size, boolean isCentralPoint) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.size = size;
        this.isCentralPoint = isCentralPoint;
    }

    public String getName() {
        return name;
    }

    public String getCoordinateX() {
        return coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public int getSize() {
        return size;
    }

    public boolean isCentralPoint() {
        return isCentralPoint;
    }

    public int getRelativeX(){
        int degrees = Integer.parseInt(coordinateX.substring(1, 3));
        int minutes = Integer.parseInt(coordinateX.substring(4, 6));
        int seconds = Integer.parseInt(coordinateX.substring(7, 9));
        int relativeX = seconds + minutes * 60 + degrees * 3600;
        return relativeX;
    }

    public int getRelativeY(){
        int degrees = Integer.parseInt(coordinateY.substring(1, 3));
        int minutes = Integer.parseInt(coordinateY.substring(4, 6));
        int seconds = Integer.parseInt(coordinateY.substring(7, 9));
        int relativeY = seconds + minutes * 60 + degrees * 3600;
        return relativeY;
    }
}
