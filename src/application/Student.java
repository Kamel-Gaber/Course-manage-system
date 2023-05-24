/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author fatma
 */
public class Student {
    private String Email;
    private String Academ_Number;
    private String FirstName;
    private String LastName;
    private String Password;   
    private Double Gpa;

    
    
    public Student(String Academ_Number, String Firstname,String Lastname,String Email, String Password,Double Gpa){
        this.Academ_Number=Academ_Number;
        this.Email=Email;
        this.FirstName=Firstname;
        this.LastName=Lastname;
        this.Password=Password;
        this.Gpa=Gpa;
    }
    
    public Student(String Academ_Number, String Firstname,String Lastname,String Email, String Password){
        this.Academ_Number=Academ_Number;
        this.Email=Email;
        this.FirstName=Firstname;
        this.LastName=Lastname;
        this.Password=Password;
   
    }
    
   

	public Double getGpa() {
		return Gpa;
	}



	public void setGpa(Double gpa) {
		Gpa = gpa;
	}



	public String getAcadem_Number(){
        return Academ_Number;
    }    
    
    public void setAcadem_Number(String Academ_Number){
        this.Academ_Number=Academ_Number;
    }
    
    public String getFirstName(){
        return FirstName;
    }    
    
    public void setFirstname(String FirstName){
        this.FirstName=FirstName;
    }
    
      public String getLastName(){
        return LastName;
    }    
    
    public void setLastname(String LastName){
        this.LastName=LastName;
    }
    
      public String getEmail(){
        return Email;
    }    
    
    public void setEmail(String Email){
        this.Email=Email;
    }
    
     public String getPassword(){
        return Password;
    }    
    
    public void setPassword(String Password){
        this.Password=Password;
    }
}
