import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * EarthwuakeData is a POJO class that keeps track of the data given during an earthquake
 * @author Liz Jolly
 * @version Fall 2018 edited on Feburary 2020 to remove mentions of the class this was code was written for
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class EarthquakeData {


    @JsonProperty("mag") private double mag;
    @JsonProperty("time") private String time;
    @JsonProperty("place") private String place;
    @JsonProperty("coordinates") private double[] coordinates;


    /**
     * constructor for earthquake Data
     * @param mag the magnitude of the earthquake on the richter scale
     * @param time  the time that the earthquake occured
     * @param place the location of the earthquake
     */
    @JsonCreator
    EarthquakeData(@JsonProperty("mag") double mag, @JsonProperty("time") String time, @JsonProperty("place") String place,
                   @JsonProperty("coordinates") double[] coordinates){
        this.mag = mag;
        this.time = time;
        this.place = place;
        this.coordinates = coordinates;
    }

    /**
     * get the magnitude of the earthquake
     * @return magnitude of the earthquake
     */
    public double getMag(){
        return mag;
    }

    /**
     * set Mag takes the magnitude given and stores it as the magitude
     * @param magnitude magnitude on the ricter scale
     */
    public void setMag(double magnitude){
        this.mag = magnitude;
    }

    /**
     * get Time gets a time and returns it to the user
     * @return returns the time of the earthquake to the user
     */
    public String getTime(){
        return time;
    }

    /**
     * set time sets the time that the earthquake happened
     * @param time time the earthquake happened
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * getPlace gets the place that the earthquake happened
     * @return String for the place of where the earthquake happened
     */
    public String getPlace(){
        return place;
    }

    /**
     * setPlace sets the place that the earthquake happened
     * @param place is a string of where the earthquake happened
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * getCordinates returns the coordinates of the earthquake
     * @return returns the array of doubles with the coordiates for the location of the earthquake
     */

    public double[] getCoordinates(){
        return coordinates;
    }

    /**
     * setCoordinates sets the coordinates for the coordinates of the earthquake
     * @param coordinates takes an array of doubles that are the coordinates of the earthquake
     */
    public void setCoordinates(double[] coordinates){
        this.coordinates = coordinates;
    }

    /**
     * getLongLat gets the longitude and latitude of the earthquake
     * @return String with the first two cordinates (long and lat)
     */
    public String getLongLat(){
        return coordinates[1] + "," + coordinates[0];
    }

    /**
     * To String
     * @return the magnitude, time, place, longitude, and latitude of the earthquake
     */
    public String toString(){
        return "mag: " + mag + " time: " + time + " place: " + place + " longitude: " + coordinates[1] + " latitude: " + coordinates[0];
    }
}
