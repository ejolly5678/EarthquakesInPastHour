
/**
 * @author Liz Jolly
 * @version Fall 2018 edited on Feburary 2020 to remove mentions of the class this assignment was for
 */
public class Map {
    /**
     * generate Map URL generates the URL of the map for the earthquakes
     * used by the project driver to generate the map
     * @return a string that is the url of the map for the quake.
     */
    public String generateMapURL(){
        String url = "https://maps.googleapis.com/maps/api/staticmap?center=USA&zoom=2&size=400x400";
        EarthquakeFinder find = new EarthquakeFinder();
        EarthquakeData[] quakeData = find.createEarthquakeData();
        for(int i = 0; i<quakeData.length; i++){
            url += "&markers=color:blue|label:" +(i+1) + "1|" + quakeData[i].getLongLat();
        }
        url += "&key=AIzaSyBjSRFRGdF4jWo_7_iNzrzkdjuekpS5AVo";

        return url;
    }
}
