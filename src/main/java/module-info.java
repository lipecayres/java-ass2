module com.example.javaassfelipecayres {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaassfelipecayres to javafx.fxml;
    exports com.example.javaassfelipecayres;
}