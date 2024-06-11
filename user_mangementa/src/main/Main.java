package main;

import javax.xml.ws.Endpoint;

import Services.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Endpoint.publish("http://localhost:8081/UserManagementService?wsdl",new UserManagementService());
        System.out.println("Student Service is running at: http://localhost:8081/UserManagementService?wsdl");
       
    }
}
