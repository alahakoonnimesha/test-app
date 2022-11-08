package com.nishiProductions.TestApplication.service.serviceImpl;

import com.nishiProductions.TestApplication.repository.dao.UserDao;
import com.nishiProductions.TestApplication.repository.dto.ResponseDto;
import com.nishiProductions.TestApplication.repository.dto.UserDto;
import com.nishiProductions.TestApplication.service.UserService;
import com.nishiProductions.TestApplication.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public ResponseDto getAllUsers() {
        log.info("UserServiceImpl.getActiveUsersByClient(Long clientId) invoked.");
        ResponseDto responseDto = null;
        try {
            List<UserDto> users = userDao.getAllUsers();
            if (users != null && !users.isEmpty()) {
                responseDto = serviceUtil.getServiceResponse(users);
            } else {
                responseDto = serviceUtil.getErrorServiceResponse("error");
            }
        } catch (Exception e) {
            log.error("sdsdsdsd", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties("exception");
        }
        return responseDto;
    }
}
