
/*
@author Louis Hounvio(U1521012)

This class holds details about a patient appointment.
So therefore, it has an instant of patient class.

Note:
. Patient: represents the patient that book the appointment.
. Date: The date the pappointment was booked for.
. Time: the time the appointment was booked for.
. Symptom: the symptoms that the patient is having (would be used for diagnosis in the main class)
. Doctor: Since is a local surgery, there is only one doctor for all the patients. Therefore, it is static.
 
 */

import java.io.Serializable;


public class Appointment implements Serializable
{    
    Patient patient;
    String date;
    String time;
    String symptom;
    static final String  doctor = " Dr Adele Mills";

    public Appointment(Patient patient, String date, String time, String symptom) 
    {
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.symptom = symptom;
    }
    
    public Patient getPatient() 
    {
        return patient;
    }
    
    public String getDate() 
    {
        return date;
    }

    public String getTime() 
    {
        return time;
    }

    public String getSymptom()
    {
        return symptom;
    }
 
    public void display() 
    {
            System.out.println(" ");
            System.out.println("Patient ID: " + patient.getId());
            System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
            System.out.println("Date booked: " + date + " at " + time);
            System.out.println("Symptom: " + symptom);
            System.out.println("");
            System.out.println("=============================================");

    }
    
}
