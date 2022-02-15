package by.epam.trainig.service;

import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.impl.UserDetailServiceImpl;

public interface UserDetailService extends EntityService<UserDetail> {

    static UserDetailService getInstance(){
        return UserDetailServiceImpl.getInstance();
    }

}
