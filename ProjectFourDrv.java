import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 *
 * This is the driver for project 4. Project 4 is an earthquake reader which reads the values of earthquakes from USGS's
 * JSON avaliable for free online.
 * It gives the earthquakes in the last hour. I have then used googles API and turned their data into a display.
 * Under this map display, there is a table mentioning data about each earthquake
 * @author Liz Jolly
 * @version Fall 2018 updated Feburary 2020 to remove mentions of the class this was for.
 *
 */
public class ProjectFourDrv extends Application {

    private static Stage guiStage;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private TableView<EarthquakeData> table = new TableView<EarthquakeData>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        guiStage = primaryStage;
        primaryStage.setTitle("Earthquakes");
        Scene scene = new Scene(new Group());
        //label for scene title
        Label label = new Label("Earthquakes in the United States in the last hour:");
        label.setFont(new Font("Arial Rounded MT Bold", 20));
        //putting earthquake data into chart
        EarthquakeFinder find = new EarthquakeFinder();
        EarthquakeData[] quakeData = find.createEarthquakeData();

         ObservableList<EarthquakeData> data = FXCollections.observableArrayList(quakeData);

        table.setEditable(false);

        TableColumn mag = new TableColumn("Magnitude");
        mag.setMinWidth(130);
        mag.setCellValueFactory(
                new PropertyValueFactory<EarthquakeData, String>("mag"));

        TableColumn time = new TableColumn("time");
        time.setMinWidth(130);
        time.setCellValueFactory(
                new PropertyValueFactory<EarthquakeData, String>("time"));

        TableColumn place = new TableColumn("place");
        place.setMinWidth(300);
        place.setCellValueFactory(
                new PropertyValueFactory<EarthquakeData, String>("place"));

        table.setItems(data);
        table.getColumns().addAll(mag,time, place);
//adding the map
        Map map = new Map();
        String urlStr = map.generateMapURL();
        ImageView mapView = new ImageView(urlStr);
//putting everything into the vbox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 30, 0, 30));

        vbox.getChildren().addAll(label, table, mapView);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}