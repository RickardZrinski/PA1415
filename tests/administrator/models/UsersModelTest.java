package tests.administrator.models;

import administrator.models.UsersModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.Hash;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UsersModelTest {

    UsersModel test;


    @Before
    public void setUp() throws Exception {

       test = new UsersModel();
    }

    @After
    public void tearDown() throws Exception {
        test = null;
    }

    @Test
    public void testGetNrOfUsers() throws Exception {

        assertEquals("If the number of users i 2 test is passed.",2,test.getNrOfUsers());
    }

    @Test
    public void testEditUser() throws Exception {

        //editUser(int index, String userName, String password, String firstName, String lastName)
        test.editUser(0,"Test","12345","Test","Testian");

        assertEquals("If username is Test", "Test", test.getUser(0).getUsername());
        assertTrue("If password is 12345", Hash.validatePassword("12345",test.getUser(0).getPassword()));
        assertEquals("If firstname is Test","Test",test.getUser(0).getFirstName());
        assertEquals("If lastname is Testian","Testian",test.getUser(0).getLastName());
    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testAddUsersFromDB() throws Exception {

    }
}