package com.exam.cafe.dto;

public class SessionUser extends User {
    private int idUser;
    public SessionUser(int id ,int idUser, String userName, String password) {
        super(id, userName, password);
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
