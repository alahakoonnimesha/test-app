package com.nishiProductions.TestApplication.repository.daoImpl;

import com.nishiProductions.TestApplication.model.entity.User;
import com.nishiProductions.TestApplication.repository.dao.UserDao;
import com.nishiProductions.TestApplication.repository.dto.UserDto;
import com.nishiProductions.TestApplication.repository.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Autowired
    UserTransformer userTransformer;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("UserDaoImpl.getAllUsers() invoked.");
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        List<UserDto> userDtos = null;
        List<User> users = criteria.list();
        if (!users.isEmpty()) {
            userDtos = new ArrayList<>();
            for (User user : users) {
                userDtos.add(userTransformer.transformEntityToDto(user));
            }
        }
        return userDtos;
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("UserDaoImpl.saveUser() invoked.");
        User user = userTransformer.transformDtoToEntity(userDto);
        User saveUser = saveOrUpdate(user);
        return userTransformer.transformEntityToDto(saveUser);
    }

    @Override
    public User findById(Long userId) {
        log.info("UserDaoImpl.findById(Long userId) invoked.");
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userId", userId));
        return (User) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("UserDaoImpl.updateUser(UserDto userDto)");
        User user = userTransformer.transformDtoToEntity(userDto);
        user = merge(user);
        return userTransformer.transformEntityToDto(user);
    }
}
