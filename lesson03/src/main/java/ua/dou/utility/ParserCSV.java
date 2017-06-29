package ua.dou.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserCSV {
    public static Property parseTheProperty(String fileCSV, String separator) {
        Property property = new Property();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileCSV))) {
            String line;
            while (null != (line = reader.readLine())) {
                if (!line.startsWith("driver")) {
                    String[] fields = line.split(separator);

                    String typeOfDriver = fields[0];
                    String pathToChromeDriver = fields[1];
                    String pathToFirefoxDriver = fields[2];

                    property.setTypeOfDriver(typeOfDriver);
                    property.setPathToChromeDriver(pathToChromeDriver);
                    property.setPathToFirefoxDriver(pathToFirefoxDriver);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
