public class Student {
    private String firstName;
    private String lastName;
    private int userID;
    private String hashPass;
    private double hours;
    private int points;
    public Student(String firstName, String lastName, int userID, String hashPass){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.hashPass = hashPass;
        hours = 0.0;
        points = 0;
    }
    public String getName(){
        return firstName + " " + lastName;
    }
    public int getUserID(){
        return userID;
    }
    public double getHours(){
        return hours;
    }
    public int getPoints(){
        return points;
    }

    public void setFirstName(String name){
        firstName = name;
    }
    public void setLastName(String name){
        lastName = name;
    }
    public void setUserID(int id){
        userID = id;
    }
    public void setPass(String pass){
        hashPass = pass;
    }
    public void updateHours(double hrs){
        hours += hrs;
    }
    public void updatePoints(int pts){
        points += pts;
    }

}
