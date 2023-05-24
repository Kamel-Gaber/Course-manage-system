/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author SOFT LAPTOP
 */
public class doctordata {
    private int doctor_id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    
    public doctordata (int doctor_id,String first_name,String last_name,String email,String password){
        this.doctor_id=doctor_id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.password=password;
        this.email=email;
   
    
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public int getdoctorid(){
        return doctor_id;
        
    }
    
     public String getfirstname(){
        return first_name;
        
    }
    public String getlastname(){
        return last_name;
        
    } 
   
     public String getemail(){
        return email;
        
    } 
     public String getpass(){
        return password;
        
    } 
    
}

    

