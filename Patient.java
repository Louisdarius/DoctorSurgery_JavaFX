
/**
 
 * @author Louis Hounvio (U1521012)
 
 * This class holds information about a patient
 * It implements Serializable to allow its objects to be writing to a file,
 * and to be read from  a file (permanent records)
 * 
 * Note:
 * .First name: The patient first name.
 * .Last name: The patient last name.
 * .ID: the patient unique ID created by the system (formula provided in addPatient() method of the main class.)
 * .Gender: the patient gender.
 * Age: The patient age.
 */

import java.io.Serializable;

public class Patient implements Serializable
{
    String firstName, lastName, id, gender;
    int age;

    public Patient(String firstName, String lastName, String id, String gender, int age) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() 
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public int getAge() 
    {
        return age;
    }

    public String getGender() 
    {
        return gender;
    }

    public void display() 
    {
            System.out.println(" ");
            System.out.println("ID: " + id);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Gender: " + gender);
            System.out.println("Age: " + age);
            System.out.println("");
            System.out.println("=============================================");

    }
    
    
}
