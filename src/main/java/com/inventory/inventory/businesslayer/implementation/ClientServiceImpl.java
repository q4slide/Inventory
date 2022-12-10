package com.inventory.inventory.businesslayer.implementation;
import com.inventory.inventory.businesslayer.entity.Client;
import com.inventory.inventory.businesslayer.services.ClientService;

import java.time.LocalDate;

public class ClientServiceImpl implements ClientService {
    private Client client;
    public  void addClient(String fName,String lName,String email,String phoneNumber){
        if (checkIfEmailIsValid(email) && checkIfPhoneNumberIsValid(phoneNumber))  client = new Client(fName, lName, email, phoneNumber, today());
        else throw new RuntimeException("Wrong information");
    }

    private  Boolean checkIfEmailIsValid(String email){
        if(email.contains("@") && email.contains(".")) return true;
        return false;
    }
    private  Boolean checkIfPhoneNumberIsValid(String phoneNumber){
        if (phoneNumber.length()==13) {
            if (checkIfNumberContainsOnlyNumeric(phoneNumber))
                return true;
        }

        return false;
    }
    private  Boolean checkIfNumberContainsOnlyNumeric(String phoneNumber){
        if (phoneNumber.charAt(0) == '+') {
            if (phoneNumber.chars().anyMatch(Character::isDigit))
                return true;
        }
        return false;
    }
    private  LocalDate today(){
        return LocalDate.now();
    }

    public  Client getClient() {
        return client;
    }
}
