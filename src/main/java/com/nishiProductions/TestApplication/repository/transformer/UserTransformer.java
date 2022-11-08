package com.nishiProductions.TestApplication.repository.transformer;

import com.nishiProductions.TestApplication.model.entity.User;
import com.nishiProductions.TestApplication.repository.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements BaseTransformer<User, UserDto> {
    @Override
    public UserDto transformEntityToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNo(user.getPhoneNo());
            userDto.setUserType(user.getUserType());
        }
        return userDto;
    }

    @Override
    public User transformDtoToEntity(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user = new User();
            user.setUserId(userDto.getUserId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNo(userDto.getPhoneNo());
            user.setUserType(userDto.getUserType());
        }

        return user;
    }
}
