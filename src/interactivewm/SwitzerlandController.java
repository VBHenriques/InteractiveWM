package interactivewm;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.Label;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class SwitzerlandController implements Initializable {

    @FXML
    private Label localtime;

    @FXML
    private ImageView locationimg;

    @FXML
    private Label maxtemp;

    @FXML
    private Label temperature;

    @FXML
    private Label humidity;

    @FXML
    private Label locationcountry;

    @FXML
    private Label locationcity;

    @FXML
    private ImageView climateimg;

    @FXML
    private Label mintemp;
    
    @FXML
    private GridPane SwiAlumni;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image;
        String wcode;

        String API_KEY = "db3c971c4ed3a1eab44ad7b67bdb1738";
        String LOCATION = "Geneva,CH";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result.toString());
            wcode = result.toString();
            wcode = wcode.substring(wcode.indexOf("icon") + 7, wcode.indexOf("icon") + 9);
            image = new Image("Images/" + wcode + "d.png");
            climateimg.setImage(image);
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            
            String temperaturex = ""+mainMap.get("temp");
            double tempx = Double.parseDouble(temperaturex);            
            
            
            String tempminx = ""+mainMap.get("temp_min");
            double tempmx = Double.parseDouble(tempminx);
            
            String humidityx = ""+mainMap.get("humidity");
            double humidx = Double.parseDouble(humidityx);
            
            String tempmaxx = ""+mainMap.get("temp_max");
            double tempmax = Double.parseDouble(tempmaxx);
            
            temperature.setText((int)(tempx)+"°C");
            humidity.setText((int)(humidx)+"%");
            mintemp.setText((int)(tempmx)+"°C");
            maxtemp.setText((int)(tempmax)+"°C");
            
            LocalDateTime mytime = LocalDateTime.now();
            DateTimeFormatter mytimeformat = DateTimeFormatter.ofPattern("HH:mm");
            String formattedtime = mytime.format(mytimeformat);
            localtime.setText(formattedtime);
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }    
        public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
        
    }

}
