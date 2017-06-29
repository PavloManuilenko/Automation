package ua.dou.utility;

public class Property {

    private String typeOfDriver;
    private String pathToChromeDriver;
    private String pathToFirefoxDriver;

    public Property() {
    }

    public void setTypeOfDriver(String typeOfDriver) {
        this.typeOfDriver = typeOfDriver;
    }

    public void setPathToChromeDriver(String pathToChromeDriver) {
        this.pathToChromeDriver = pathToChromeDriver;
    }

    public void setPathToFirefoxDriver(String pathToFirefoxDriver) {
        this.pathToFirefoxDriver = pathToFirefoxDriver;
    }

    public String getPathToChromeDriver() {
        return pathToChromeDriver;
    }

    public String getPathToFirefoxDriver() {
        return pathToFirefoxDriver;
    }

    public String getTypeOfDriver() {
        return typeOfDriver;
    }
}
