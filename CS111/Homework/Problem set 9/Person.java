/*
 * Aleksander Skjoelsvik - alsk 
 */

public class Person {
    private String name;
    private Date birthDate;
    
    public Person(String name, Date birthDate) {
        if((name == null) || (null == "")) {
            throw new IllegalArgumentException("Persons name cannot be empty");
        }
        if(birthDate == null) {
            throw new IllegalArgumentException("Persons birthday cannot be null");
        }
        
        this.name = name;
        this.birthDate = birthDate;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Date getDOB() {
        return this.birthDate;
    }
    
    public Date getBirthdayIn(int year) {
        Date birthdayIn = new Date(birthDate.getMonth(), birthDate.getDay(), year);
        return birthdayIn;
    }
    
    public int getAgeOn(Date date) {
        if(birthDate.isAfter(date) == true) {
            throw new IllegalArgumentException("The date to check needs to be after the Date of Birth");
        }
        
        int age = date.getYear() - birthDate.getYear();
        Date birthdayNow = getBirthdayIn(date.getYear());
        
        if(date.isBefore(birthdayNow) == true) {
            age = age - 1;
        }
        
        return age;
    }
    
    public String toString() {
        return name + " (born on " + birthDate.toString() + ")";
    }
}