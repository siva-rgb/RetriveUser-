package com.example.userretrive;

public class User {

    private String id;
    private String imageURL;
    private String useremail;
    private String username;
    private String usernumber;
    private String userpassword;



    public User(String id, String imageURL, String useremail,String username,String usernumber,String userpassword ) {
        this.id = id;
        this.imageURL = imageURL;
        this.useremail = useremail;
        this.username = username;
        this.usernumber = usernumber;
        this.userpassword = userpassword;


    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
