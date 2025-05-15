module fr.iut.saeterraria.sae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.iut.saeterraria.sae to javafx.fxml;
    exports fr.iut.saeterraria.sae;
    exports fr.iut.saeterraria.sae.Controller;
    opens fr.iut.saeterraria.sae.Controller to javafx.fxml;
}