module codychoules.application.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens codychoules.application.main to javafx.fxml;
    exports codychoules.application.main;
    exports codychoules.application.controllers;
    opens codychoules.application.controllers to javafx.fxml;
}