module fr.iut.saeterraria.sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires jdk.xml.dom;
    requires jdk.jdi;


    opens fr.iut.saeterraria.sae to javafx.fxml;
    exports fr.iut.saeterraria.sae;
    exports fr.iut.saeterraria.sae.Controller;
    opens fr.iut.saeterraria.sae.Controller to javafx.fxml;
}