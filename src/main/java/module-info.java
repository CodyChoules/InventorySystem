module codychoules.application.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens codychoules.application.main to javafx.fxml;
    exports codychoules.application.main;
    exports codychoules.application.controllers;
    opens codychoules.application.controllers to javafx.fxml;
    exports codychoules.application.model;
    opens codychoules.application.model to javafx.fxml;
}