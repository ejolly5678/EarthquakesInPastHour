import java.net.*;
import java.io.*;
import net.sf.json.*;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Earthquake finder finds the earthquakes using the JSON API given by USGS
 * @author Liz Jolly
 * @version Fall 2018 edited on Feburary 2020 to remove mentions of the class this assignment was for
 */
public class EarthquakeFinder {
    /**
     *Finds the data for the earthquake
     * @return EarthquakeData[] array of earthquake data containing every earthquake from the past hour
     */
    public EarthquakeData[]  createEarthquakeData() {
        try {
            String JSonString = readURL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");

            JSONObject x = JSONObject.fromObject(JSonString);
            JSONArray locArr = x.getJSONArray("features");

            ObjectMapper objectMapper = new ObjectMapper();

            double[] magData = new double[locArr.size()];
            String[] timeData = new String[locArr.size()];
            String[] locatData = new String[locArr.size()];
            double[][] coordinateData = new double[locArr.size()][3];
            EarthquakeData[] quakeData = new EarthquakeData[locArr.size()];
            for (int i = 0; i < locArr.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) locArr.get(i);
                JSONObject properties = (JSONObject) jsonObject1.get("properties");

                magData[i] = properties.getDouble("mag");
                timeData[i] =objectMapper.writeValueAsString(properties.get("time"));
                locatData[i] = objectMapper.writeValueAsString(properties.get("place"));

                JSONObject geometry = (JSONObject) jsonObject1.get("geometry");

                JSONArray coordinates = (JSONArray) geometry.get("coordinates");
                for(int j = 0; j<coordinates.size(); j++){
                    coordinateData[i][j] = coordinates.getDouble(j);
                }
                quakeData[i] = new EarthquakeData(magData[i], timeData[i], locatData[i], coordinateData[i]);
            }
            return quakeData;
        }
        catch(Exception e){ //returns all exceptions as nothing
            return null;
        }
    }

    /**
     *
     * private helper method demonstrating how to read the contents of a URL as a String.
     * Based upon the method given in Lab04 in -Class Redacted for professional reasons- class
     *
     * @param webservice provides the URL address of the web service to be accessed
     * @return String representation of the given web page or service
     * @throws java.net.MalformedURLException if the given url is poorly formed
     * @throws java.io.IOException if IOUtils encounters an IOException
     *
     **/
    private static String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException
    {
        URL service = new URL(webservice);

        String result = IOUtils.toString(service, "UTF-8");
        return result;
    }
}
