module com.champqcsoft.champexamen_by_nathan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.champqcsoft.champexamen_by_nathan to javafx.fxml;
    exports com.champqcsoft.champexamen_by_nathan;
}