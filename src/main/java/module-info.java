module com.inventory.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.inventory.inventory.controllers to javafx.fxml;
    opens  com.inventory.inventory.businesslayer.entity to org.hibernate.commons.annotations,org.hibernate.orm.core,javafx.base;
    opens com.inventory.inventory.businesslayer.implementation to org.hibernate.orm.core;

    exports com.inventory.inventory.businesslayer.entity;
    exports com.inventory.inventory;
    exports com.inventory.inventory.controllers;
    exports com.inventory.inventory.utility.hibernate;
    exports com.inventory.inventory.dao.implementation;
    exports com.inventory.inventory.dao.services;
}