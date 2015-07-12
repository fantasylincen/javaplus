package org.hhhhhh.prophet.hibernate;
public class UserService {
    private UsersDAO userDao;
    
    public int userCount(){
        return userDao.getAllUser().size();
    }

    public UsersDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UsersDAO userDao) {
        this.userDao = userDao;
    }

}