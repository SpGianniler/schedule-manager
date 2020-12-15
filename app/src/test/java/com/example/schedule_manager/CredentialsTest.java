package com.example.schedule_manager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CredentialsTest {

    @Test
    public void credentialsPassing() {
        ArrayList<Credentials> listOfCreds=new ArrayList<>();
        Credentials cred = new Credentials(1,true,"Basilis","Kalogirou");
        listOfCreds.add(cred);
        boolean result=Credentials.isValid("Basilis","Kalogirou",true,listOfCreds);
        assertTrue(result);

    }

    @Test
    public void credentialsNotPassing() {
        ArrayList<Credentials> listOfCreds=new ArrayList<>();
        Credentials cred = new Credentials(1,true,"Giannis","Papadopoulos");
        listOfCreds.add(cred);
        boolean result=Credentials.isValid("Basilis","Kalogirou",true,listOfCreds);
        assertFalse(result);

    }

}