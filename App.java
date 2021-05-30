
/*
 * @author louis Hounvio U1521012
 * Use admin1, admin2 or admin3 to gain access to list patients/list appointments 
 */


package com.mycompany.doctorsurgeryinterface;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class App extends Application 
{
    private String [] ages = 
    {
        "1", "2", "3", "4", "5" ,"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25" ,"26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
        "41", "42", "43", "44", "45" ,"46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
        "61", "62", "63", "64", "65" ,"66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
        "81", "82", "83", "84", "85" ,"86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",
        "101", "102", "103", "104", "105" ,"106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120",
        "121", "122", "123", "124", "125" ,"126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140",
        "141", "142", "143", "144", "145" ,"146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160",
        "161", "162", "163", "164", "165" ,"166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180",
        "181", "182", "183", "184", "185" ,"186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200"
        
    };   
    private String [] times ={"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};    
    private String [] adminPasswords = {"admin1", "admin2", "admin3"};
    private final String headache = "Constant ache that affects both sides of the head";
    private final String stomackAche = "Trapped wind, indigestion, and diarrhoea";
    private final String coronavirus = "High temperature, continuous cough and loss of taste";
    private final String asthma =  "Wheezing (a whistling sound when you breathe)";
    private final String constipation = "Unable to completely empty your bowel.";
    private final String [] symptomsList = {headache, stomackAche, coronavirus, asthma, constipation};
    
    private List <Patient> patients = new ArrayList<>();
    private List <Appointment> appointments = new ArrayList<>();
    
    private Label headingLabel = new Label("Doctor Surgery App"); 
    
    // Patient components
    private Label firstNameLabel = new Label("First Name");
    private TextField firstNameField = new TextField();   
    private Label lastNameLabel = new Label("Last Name");
    private TextField lastNameField = new TextField();
    private Label ageLabel = new Label("Age"); 
    private ComboBox ageChoice =new ComboBox(FXCollections.observableArrayList(ages));   
    private Label genderLabel = new Label("Gender");  
    private ComboBox genderChoice =new ComboBox(FXCollections.observableArrayList(new String[] {"Male", "Female"}));   
    private Button addPatientButton = new Button("Add Patient");
    private Button removePatientButton = new Button("Remove Patient");
    private Button listPatientsButton = new Button("List All Patients");
    TextArea patientDisplayArea = new TextArea();
    
    
    // Appointment components 
    private Label dateLabel = new Label("Date");
    private DatePicker dateChoice = new DatePicker();
    private Label timeLabel = new Label("Time");
    private ComboBox timeChoice =new ComboBox(FXCollections.observableArrayList(times));   
    private Label symptomLabel = new Label("Symptom");
    private ComboBox symptomChoice =new ComboBox(FXCollections.observableArrayList(symptomsList));   
    private Button makeAppointmentButton = new Button("Make Appointment");
    private Button cancelAppointmentButton = new Button("Cancel Appointment");
    private Button listAppointmentsButton = new Button("List Appointments");
    private Button getReportButton = new Button("Get report");
    TextArea appointmentDisplayArea = new TextArea();
    
    // Quit and Save
    private Button exitButton = new Button("Save & Exit");
    

    @Override
    public void start(Stage stage) 
    {
        // File handling
        readPatient(patients);
        readAppointment(appointments);
        
        // Declaring HBoxes
        HBox title = new HBox(10);
        HBox patientDetails = new HBox(10);
        HBox patientButtons = new HBox(10);
        HBox appointmentDetails = new HBox(10);
        HBox appointmentButtons = new HBox(10);
        HBox exit = new HBox(10);
        VBox root = new VBox(30);
 

        // Adding components to HBoxes
        title.getChildren().addAll(headingLabel);
        patientDetails.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel,
                lastNameField, ageLabel, ageChoice, genderLabel, genderChoice);
        patientButtons.getChildren().addAll(addPatientButton, removePatientButton, listPatientsButton);
        appointmentDetails.getChildren().addAll(dateLabel, dateChoice, timeLabel, timeChoice, symptomLabel, symptomChoice);
        appointmentButtons.getChildren().addAll(makeAppointmentButton, cancelAppointmentButton, 
                listAppointmentsButton, getReportButton);
        exit.getChildren().addAll(exitButton);
        
        root.getChildren().addAll(title, patientDetails, patientButtons, patientDisplayArea,
                appointmentDetails, appointmentButtons, appointmentDisplayArea, exit);
        
        
        
        // Set Event Handlers
        addPatientButton.setOnAction(e -> addPatientButtonHandler());
        removePatientButton.setOnAction(e -> removePatientButtonHandler());
        listPatientsButton.setOnAction(e -> listPatientsButtonHandler());
        makeAppointmentButton.setOnAction(e -> makeAppointmentButtonHandler());
        cancelAppointmentButton.setOnAction(e -> cancelAppointmentButtonHandler());
        listAppointmentsButton.setOnAction(e -> listAppointmentsButtonHandler());
        getReportButton.setOnAction(e -> getReportButtonHandler());
        getReportButton.setOnAction(e -> getReportButtonHandler());
        exitButton.setOnAction(e -> exitButtonHandler());
        
        // Set Display areas to not editable
        patientDisplayArea.setEditable(false);
        appointmentDisplayArea.setEditable(false);

        // set fonts
        Font headingFont = new Font("Calibri", 40);
        Font labelsFont = new Font("Calibri", 15);
        
        headingLabel.setFont(headingFont);
        firstNameLabel.setFont(labelsFont);
        lastNameLabel.setFont(labelsFont);
        ageLabel.setFont(labelsFont);
        genderLabel.setFont(labelsFont);
        dateLabel.setFont(labelsFont);
        timeLabel.setFont(labelsFont);
        symptomLabel.setFont(labelsFont);
        
        
        // Set alignment of HBoxes
        title.setAlignment(Pos.CENTER);
        patientDetails.setAlignment(Pos.CENTER);
        patientButtons.setAlignment(Pos.CENTER);
        appointmentDetails.setAlignment(Pos.CENTER);
        appointmentButtons.setAlignment(Pos.CENTER);
        exit.setAlignment(Pos.CENTER);
        
        // set alignment for root
        root.setAlignment(Pos.CENTER);
                
       // customise the VBox border and background
       BorderStroke style = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2) );
       root.setBorder(new Border (style));
       root.setBackground(Background.EMPTY);
       
       
       // customise buttons
       addPatientButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       removePatientButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       listPatientsButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       makeAppointmentButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       cancelAppointmentButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       listAppointmentsButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       getReportButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
       exitButton.setStyle("-fx-font: 15 arial; -fx-base: #99ccff;");
      
        
       // Getting the stage ready
        Scene myScne = new Scene(root, 800, 800);
        stage.setScene(myScne);
        stage.show();
        
        


    }

    public static void main(String[] args) 
    {
        launch();
    }
    
    
    /* 
    Private Methods 
    Resposible for the program core functions.   
    */  
    private void addPatientButtonHandler()
    {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String id;
        String age = (String) ageChoice.getSelectionModel().getSelectedItem();
        String gender = (String) genderChoice.getSelectionModel().getSelectedItem();
        
        if(firstName.length() == 0 || lastName.length() == 0 || age.length() == 0 || gender.length() == 0)       
        {
            patientDisplayArea.setText("All fields are required");
        }
        else if (isNumberInString(firstName))
        {
            patientDisplayArea.setText("First Name cannot contain numbers");
        }
        else if (isNumberInString(lastName))
        {
            patientDisplayArea.setText("Last Name cannot contain numbers");
        }
            
        else
        {
            // Ok to add patient
            
            // Capitalize the first character
            firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1); 
            
            // Capitalize last name
            lastName = lastName.toUpperCase();
 
             // Creating the id using a combination of initials and some random numbers;       
            id = String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0)) + (new Random().nextInt(1000000000) + patients.size());

            // Add patient to the list
            patients.add(new Patient(firstName, lastName, id, gender, Integer.parseInt(age)));
            
            // Clear fields
            firstNameField.setText("");
            lastNameField.setText("");
            
            patientDisplayArea.setText("Hi " + firstName + " " + lastName + ". You have been successfully added." + lineBreak());
            patientDisplayArea.appendText("Please make note of your Patiend ID: " + id);
        }

    }
    
    private void removePatientButtonHandler()
    {
        String id;
        Patient foundPatient;
        Appointment foundAppointment;
        
        
        // Get user input       
        if((id = getUserInput("Patient ID")) == null)
        {
            patientDisplayArea.setText("Patient ID not provided");
        }
        else if(((foundPatient = getPatient(patients, id)) == null))
        {
            patientDisplayArea.setText("A patient with this ID does not exist");
        }

        else
        {
            // If found remove patient appointment
            foundAppointment = getAppointment(patients, appointments, foundPatient.getId());
            if(foundAppointment != null)
            {
                appointments.remove(foundAppointment);
            }
            
            patients.remove(foundPatient); 
            patientDisplayArea.setText("We are sorry to see you go " + foundPatient.getFirstName() + " " + foundPatient.getLastName());
        }

    }
    
    private void listPatientsButtonHandler()
    {
        String admin = getUserInput("Admin Password");
        if(admin == null)
        {
            patientDisplayArea.setText("Password not provided");

        }
          
        // Check if if password is correct
        else if(!(isAdmin(admin)))
        {
            patientDisplayArea.setText("Wrong Password");
        }
        
        else
        {
            if(patients.size() == 0)
            {
                patientDisplayArea.setText("Patient list is empty");
            } 
            else
            {
                patientDisplayArea.setText(" ================= List of patients ================= " + lineBreak() + lineBreak());
                for(int i = 0; i < patients.size(); i++)
                {
                    patientDisplayArea.appendText("ID: " + patients.get(i).getId() + lineBreak());
                    patientDisplayArea.appendText("First Name: " + patients.get(i).getFirstName() + lineBreak());
                    patientDisplayArea.appendText("Last Name: " + patients.get(i).getLastName() + lineBreak());
                    patientDisplayArea.appendText("Gender: " + patients.get(i).getGender() + lineBreak());
                    patientDisplayArea.appendText("Age: " + patients.get(i).getAge() + lineBreak());
                    patientDisplayArea.appendText("===================" + lineBreak() + lineBreak());
                }
            }
        }

    
    }
    
    private void makeAppointmentButtonHandler()
    {
        String id;
        LocalDate date;
        String time;
        //String time = (String) timeChoice.getSelectionModel().getSelectedItem();
        //String symptom = (String) symptomChoice.getSelectionModel().getSelectedItem();
        String symptom;
        Patient foundPatient;
        
        // Check if user ID is empty
        if((id = getUserInput("Patient ID")) == null)
        {
            appointmentDisplayArea.setText("ID not provided");
        }
        
        // Check if patient exists
        else if((foundPatient = getPatient(patients, id)) == null)
        {
            appointmentDisplayArea.setText("Cannot find a Patient with that ID");
        }
        // Check if Patient already has an appointment
        else if(patientHasAppointment(appointments, id))
        {
            appointmentDisplayArea.setText("You already have an appointment booked.");
        }
        else if((date = dateChoice.getValue()) == null)
        {
            appointmentDisplayArea.setText("Please provide a date");
        }
        else if((time = ((String)timeChoice.getSelectionModel().getSelectedItem())) == null)
        {
            appointmentDisplayArea.setText("Please provide a time");
        }
        else if((symptom = ((String)symptomChoice.getSelectionModel().getSelectedItem())) == null)
        {
            appointmentDisplayArea.setText("Please provide a symptom");
        }
        else
        {
            appointments.add(new Appointment(foundPatient, date.toString(), time, symptom));
            appointmentDisplayArea.setText("Appointment Successfully booked." + lineBreak() + 
                    "Please attend your appointment on the " + date + " at " + time);
        }
            
           
            
    }
    
    private void cancelAppointmentButtonHandler()
    {
        String id;
        Patient foundPatient;
        Appointment foundAppointment;
        
        // Get user input       
        if((id = getUserInput("Patient ID")) == null)
        {
            appointmentDisplayArea.setText("Patient ID not provided");
        }
        else if(((foundPatient = getPatient(patients, id)) == null))
        {
            appointmentDisplayArea.setText("A patient with this ID does not exist");
        }
        else if((foundAppointment = getAppointment(patients, appointments, foundPatient.getId())) == null)
        {
            appointmentDisplayArea.setText("You do not have an appointment booked");
        }
        else 
        {
            appointments.remove(foundAppointment);
            appointmentDisplayArea.setText("Appointment successfully removed.");
        }
   
    }
    
    private void listAppointmentsButtonHandler(){
        
        
        String admin = getUserInput("Admin Password");
        if(admin == null)
        {
            appointmentDisplayArea.setText("Password not provided");

        }
          
        // Check if if password is correct
        else if(!(isAdmin(admin)))
        {
            appointmentDisplayArea.setText("Wrong Password");
        }
        
        else
        {
            if(appointments.size() == 0)
            {
                appointmentDisplayArea.setText("Appointment list is empty");
            } 
            else
            {
                appointmentDisplayArea.setText(" =============== List of appointments ================= " + lineBreak() + lineBreak());
                for(int i = 0; i < appointments.size(); i++)
                {
                    appointmentDisplayArea.appendText("ID: " + appointments.get(i).getPatient().getId() + lineBreak());
                    appointmentDisplayArea.appendText("Name: " + appointments.get(i).getPatient().getFirstName() + "" + appointments.get(i).getPatient().getLastName() + lineBreak());
                    appointmentDisplayArea.appendText("Date: " + appointments.get(i).getDate() + " at " + appointments.get(i).getTime() + lineBreak());
                    appointmentDisplayArea.appendText("Symptoms: " + appointments.get(i).getSymptom() + lineBreak());
                    appointmentDisplayArea.appendText("==============================================================" + lineBreak() + lineBreak());
                }
            }
        }
        
        
        
        
        
        
    }
    
    private void getReportButtonHandler()
    {
        String id;
        Patient foundPatient;
        Appointment foundAppointment;
        
        // Get user input       
        if((id = getUserInput("Patient ID")) == null)
        {
            appointmentDisplayArea.setText("Patient ID not provided");
        }
        else if(((foundPatient = getPatient(patients, id)) == null))
        {
            appointmentDisplayArea.setText("A patient with this ID does not exist");
        }
        else if((foundAppointment = getAppointment(patients, appointments, foundPatient.getId())) == null)
        {
            appointmentDisplayArea.setText("You do not have an appointment booked");
        }
        else
        {
            String [][] diagnosis = diagnosis(foundAppointment.getSymptom());
            
            appointmentDisplayArea.setText("=========================================" + lineBreak());
            appointmentDisplayArea.appendText("                               REPORT" + lineBreak());
            appointmentDisplayArea.appendText("=========================================" + lineBreak() + lineBreak());
                    
            appointmentDisplayArea.appendText("---------- Patient Details -------" + lineBreak());
            appointmentDisplayArea.appendText("Name: " + foundAppointment.getPatient().getFirstName() + " " + foundAppointment.getPatient().getLastName() + lineBreak());
            appointmentDisplayArea.appendText("Age: " + foundAppointment.getPatient().getAge() + lineBreak());
            appointmentDisplayArea.appendText("Gender: " + foundAppointment.getPatient().getGender() + lineBreak() + lineBreak());
                    
            appointmentDisplayArea.appendText("---------- Appointment Details ---------" + lineBreak());
            appointmentDisplayArea.appendText("Date: " + foundAppointment.getDate() + " at " + foundAppointment.getTime() + lineBreak());
            appointmentDisplayArea.appendText("Symptom " + foundAppointment.getSymptom() + lineBreak() + lineBreak());
                    
            appointmentDisplayArea.appendText("---------- Diagnosis ----------" + lineBreak());  
            appointmentDisplayArea.appendText("Result: " + diagnosis[0][0] + lineBreak() + lineBreak());
                           
            appointmentDisplayArea.appendText("---------- What to do ---------" + lineBreak());
            for(int i = 0; i < diagnosis[1].length; i++)
            {
                appointmentDisplayArea.appendText(diagnosis[1][i] + lineBreak());
            }
            appointmentDisplayArea.appendText(lineBreak());
  
            appointmentDisplayArea.appendText( "Report by: " + foundAppointment.doctor);

            // Delete appointment
            appointments.remove(foundAppointment);

        }
    }
    
    private void exitButtonHandler()
    {
        writePatient(patients);
        writeAppointment(appointments);
        patientDisplayArea.setText("Thank you for using the app. Good Bye");
	System.exit(0);
    }
    

    
    
    // File handling
    private void readPatient(List<Patient> patientsIn) 
        {
            
            /* This method reads patient objects from a file and copy it into the patient list 
               as soon as the program starts
            */ 
            
		Patient p;
		boolean end = false;
		
		try(
                        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Patient.txt"))
		) 
		
		{
			do {
				p = (Patient)reader.readObject();
			    patientsIn.add(p);
			} while(end != true);	
		} 
		catch(EOFException e) {
			end = true;			
		}
		
		catch(Exception e) {
			System.out.println("Oh Oh Exception occured");
			System.out.println(e);		
		}
		
	}
    
    private void writePatient(List <Patient> patientsIn)
    {
            
            // This method simply write Patient objects into a file when users end the program
            
        try(
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Patient.txt"));
            )
        {
            patientsIn.forEach(patient -> {
                    try {
                        writer.writeObject(patient);
                    } catch (IOException e) {
                        System.out.println("Something went wrong while writing Patients to the file");
                    }
                });
            System.out.println("Successfully written Patients to the file");
            
        }
        catch(Exception e){
            System.out.println("Exception caught");
        }
    }
    
     private void readAppointment(List<Appointment> appointmentsIn) 
    {
        /*
        This method reads apppointment objects from a file and copy them into 
        appointment ArrayList as soon as the program starts.
        */
       
		Appointment a;
		boolean end = false;
		
		try(
                        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Appointment.txt"))
		) 
		
		{
			do {
				a = (Appointment)reader.readObject();
			    appointmentsIn.add(a);
			} while(end != true);	
		} 
		catch(EOFException e) {
			end = true;			
		}
		
		catch(Exception e) {
			System.out.println("Oh Oh Exception occured");
			System.out.println(e);		
		}
		
	}
    
    private void writeAppointment(List <Appointment> appointmentsIn)
    {
        /*
        This method writes Appointment objects into a file at the end of the program 
        
        */
        
        try(
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Appointment.txt"));
            )
        {
            appointmentsIn.forEach(appointment -> {
                    try {
                        writer.writeObject(appointment);
                    } catch (IOException e) {
                        System.out.println("Something went wrong while writing Appointments to the file");
                    }
                });
            System.out.println("Successfully written Appointments to the file");
            
        }
        catch(Exception e){
            System.out.println("Exception caught");
        }
    }

    
    
    

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////  OTHER METHODS   //////////////////////////////////////////
 
    private Patient getPatient(List <Patient> patientsIn, String idIn)
    {
        
        /*
    This method is used to check if a patient exist.
    It accepts a patient list and a string representing the ID
    of the patient to be searched for. If no patient with the that ID is found,
    a null value is returned otherwise, it returns the patient with that ID.
    */
        
        
       for(int i = 0; i < patientsIn.size(); i++)
       {
           if(patientsIn.get(i).id.equals(idIn))
           {
           return patientsIn.get(i);
           }
       }
         return null;
       
    }
    
    private Appointment getAppointment(List <Patient> patientsIn, List <Appointment> appointmentsIn, String idIn)
    {
        
        /*
        This method accept an ID and return an apponitment
        Other methods rely on this method for their functinoalities
        It loop through the appointment list and for each appointment, compares and see wether
        the received ID is associated with any appointments.
        */
    
          for(int i = 0; i < appointmentsIn.size(); i++){
                  if(appointmentsIn.get(i).getPatient().getId().equals(idIn)){
                      return appointmentsIn.get(i);
                  } 
          }
          return null;
    }
    
    private String getUserInput(String typeIn)
    {
        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setHeaderText("Enter " + typeIn);
        passwordDialog.showAndWait();
        String admin = passwordDialog.getEditor().getText();
        // check if the user input is empty
        if(admin == null || admin.length() == 0)
        {
            return null;

        }
        return admin;
    }
    
    private boolean isNumberInString(String stringIn)
    {
        
        // Check if there is number is String. Useful when numbers are not expected in input
        // Ex: names
        
              char[] chars = stringIn.toCharArray();
              for(char c: chars){
                  if(Character.isDigit(c)){
                      return true;  
                  } 
              }
              return false;
    }
    
    private boolean isAdmin(String passwordIn)
    {        /*
        To be able to view patient or appointment list, you must have administration priviledge.
        There is an array called adminPasswords in this class that conatins the pre-set admin passwords
        */
         
        for(int i = 0; i < adminPasswords.length; i++)
        {
            if(adminPasswords[i].equals(passwordIn))
            {
            return true;
            }
        }
        
        return false;
    }
    
    private boolean patientHasAppointment(List <Appointment> appointmentsIn, String patientIdIn)
    {
        for(int i = 0; i < appointmentsIn.size(); i++)
        {
            if(appointmentsIn.get(i).getPatient().getId().equals(patientIdIn))
            {
                return true;
            }
        }
        return false;
    }
    
    
    private String lineBreak()
    {
    return "\n";
    }
    
    private String [][] diagnosis(String symptomIn)
     {
         /*
         This is one of the essential methods in this project.
         It receives a symptom as an input and returns a 2D array containing:
         1. A possible sickness that the patient is suffering from
         2. A list of solutions of how to deal with the illness.      
         */
              
         String [][] diagnosis = new String [2][5];
         
         
         /* A 3D array holding 2D arrays containing each the diagnosis 
         and a list of the things to do(threatments).     
         */
         
         String[][][] diagnosisList = new String [][] [] 
        {
             
            {
                 {
                 "Headache"
                 },
                 {
                     "Take two tablets of Paracetamol every four hours (no more than 3 times a day) if you are an adult ",
                     "Take one every four hours (no more than three times a day) if you are a child",
                     "Get more rest",
                     "Drink enough of fluids"
                 }  
            },
       
            {
                {
                    "Stomack Ache"
                },
                {
                   "Place a hot water bottle or heated wheat bag on your abdomen.",
                   "Soak in a warm bath.",
                   "Drink plenty of clear fluids such as water.",
                   "Reduce your intake of coffee, tea and alcohol as these can make the pain worse."
                }        
            },
         
            {
                {
                    "Coronavirus"
                },
                {
                    "Do not go to work, school or public places.- Work from home if you can",
                    "Do not have visitors in your home, including friends and family.",
                    "Take paracetamol or Ibuprofen if you feel uncomfortable.",
                    "Drink plenty of fluids (water is best) to avoid dehydration.",
                    "Get lots of rest"
                }
            },
         
         
            {
                {
                    "Asthma"
                },
                {
                    "Use a reliever inhaler to treat your symptoms when they occur.",
                    "Take tablets such as Leukotriene receptor antagonists, Theophyllin, and steroid if using an inhaler alone is not helping control your symptoms: "
                }
            },
            
            {
                {
                    "Constipation"
                },
                {
                    "Eat enough fibre, such as fruit, vegetables and cereals",
                    "Adapt a good diet routine.",
                    "Do not ignore the urge to pass stools",
                    "Drink enough fluids"
                }
            }
        };
         
       
         
        switch (symptomIn) 
        {
            case headache:
                diagnosis = diagnosisList[0];
                break;
            case stomackAche:
                diagnosis = diagnosisList[1];
                break;
            case coronavirus:
                diagnosis = diagnosisList[2];
                break;
            case asthma:
                diagnosis = diagnosisList[3];
                break;
            case constipation:
                diagnosis = diagnosisList[4];
                break;
            default:
                break;
        }
         return diagnosis;
    }

}