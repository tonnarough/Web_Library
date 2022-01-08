package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.queryoperation.Impl.QueryOperationImpl;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.dao.queryoperation.QueryOperator;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.UserService;
import by.epam.trainig.service.impl.UserServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

public class Main {
    public static void main(String[] args)  {

        UserService userService = new UserServiceImpl();
        List<User> list = new ArrayList<>();


        try {
           list = userService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

//        methodUserDAO.create(new User(6, "dfhgsdfsa", "dfhgsdfsa", "dfhgsdfsa", "dfhgsdfsa", "dfhgsdfsa", "dfhgsdfsa"
//                , "Male", "+375333458932", "MP5678496", Date.valueOf("2000-12-12")));




    }
}
