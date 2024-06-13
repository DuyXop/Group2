package fa.training.interviewmanagement.service;

import fa.training.interviewmanagement.entity.UserEntity;
import fa.training.interviewmanagement.model.UserDto;
import fa.training.interviewmanagement.model.UserGetResponse;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);
    UserGetResponse findAll(int page, int size);
    void deleteUser(Integer id);
    UserEntity findById(Integer id);
    List<UserEntity> searchUser(String key);
    UserEntity updateUser(Integer id, UserDto userDto);

}
