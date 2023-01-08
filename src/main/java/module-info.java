module subsc.smart_electronic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires fontawesomefx;
    requires com.microsoft.sqlserver.jdbc;
    requires jasperreports;
    requires org.apache.poi.poi;
    requires org.apache.xmlbeans;
    requires org.apache.poi.ooxml;
    requires org.apache.logging.log4j;

   
    opens subsc.smart_electronic to javafx.fxml;
    exports subsc.smart_electronic;
    opens subsc.smart_electronic.controllers;
    exports subsc.smart_electronic.controllers;
    opens subsc.smart_electronic.models;
    exports subsc.smart_electronic.models;
}
