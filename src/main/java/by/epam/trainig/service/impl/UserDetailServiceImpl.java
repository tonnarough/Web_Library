package by.epam.trainig.service.impl;

import by.epam.trainig.dao.UserDetailDAO;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.UserDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UserDetailServiceImpl extends CommonService<UserDetail> implements UserDetailService {

    private static final Logger logger = LogManager.getLogger(UserDetailServiceImpl.class);

    private final UserDetailDAO userDetailDAO;

    UserDetailServiceImpl(UserDetailDAO userDetailDAO) {
        super(userDetailDAO, logger);
        this.userDetailDAO = userDetailDAO;
    }

    public static UserDetailService getInstance() {
        return UserDetailServiceImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDetailService INSTANCE = new UserDetailServiceImpl(
                UserDetailDAO.getInstance()
        );
    }

}
