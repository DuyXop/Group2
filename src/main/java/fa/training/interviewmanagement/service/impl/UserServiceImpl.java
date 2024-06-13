package fa.training.interviewmanagement.service.impl;

import fa.training.interviewmanagement.entity.UserEntity;
import fa.training.interviewmanagement.model.StatusUserEnum;
import fa.training.interviewmanagement.model.UserDto;
import fa.training.interviewmanagement.model.UserGetResponse;
import fa.training.interviewmanagement.repository.UserRepository;
import fa.training.interviewmanagement.service.MailService;
import fa.training.interviewmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Override
    public void createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setRole(userDto.getRole());
        userEntity.setDob(userDto.getDob());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setGender(userDto.getGender());
        userEntity.setStatus(userDto.getStatus());
        userEntity.setNote(userDto.getNote());
        userRepository.save(userEntity);
        mailService.sendEmail(userDto.getEmail(), "We send password for you", "Password: " + userDto.getNote());
    }

    @Override
    public UserGetResponse findAll(int page, int size) {
        UserGetResponse userGetResponse = new UserGetResponse();
        Page<UserEntity> userEntityPage = userRepository.findAll(PageRequest.of(page, size));
        List<UserGetResponse.UserResponse> userResponseList = userEntityPage
                .getContent()
                .stream()
                .map(user -> {
                    UserGetResponse.UserResponse userResponse = new UserGetResponse.UserResponse();
                    userResponse.setUserId(user.getUserId());
                    userResponse.setUsername(user.getUsername());
                    userResponse.setRole(user.getRole());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setPhone(user.getPhone());
                    userResponse.setStatus(user.getStatus());
                    return userResponse;
        }).collect(Collectors.toList());
        userGetResponse.setUserResponseList(userResponseList);
        userGetResponse.setTotalPage(userEntityPage.getTotalPages());
        userGetResponse.setTotalElements((int) userEntityPage.getTotalElements());
        return userGetResponse;
    }

    @Override
    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
    }

    @Override
    public List<UserEntity> searchUser(String key) {
        return null;
    }

    @Override
    public UserEntity updateUser(Integer id, UserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userEntity.setUsername(userDto.getUsername());
            userEntity.setRole(userDto.getRole());
            userEntity.setDob(userDto.getDob());
            userEntity.setPhone(userDto.getPhone());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setAddress(userDto.getAddress());
            userEntity.setGender(userDto.getGender());
            userEntity.setStatus(userDto.getStatus());
            userEntity.setNote(userDto.getNote());
            return userRepository.save(userEntity);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
