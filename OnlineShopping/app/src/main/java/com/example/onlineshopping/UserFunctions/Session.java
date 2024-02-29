package com.example.onlineshopping.UserFunctions;

public  class Session {
    private String username;
    private  String answer;

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    static Session instance = null;

    public String getUsername() {
        return username;
    }

    private Session(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public static Session getInstance(){
        if(instance == null){
            synchronized (Session.class){
                if(instance == null){
                    instance = new Session();
                }
            }
        }
        return instance;
    }
}
