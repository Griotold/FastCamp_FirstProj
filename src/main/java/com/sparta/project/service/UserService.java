package com.sparta.project.service;


import com.sparta.project.config.jwt.JwtUtil;
import com.sparta.project.domain.User;
import com.sparta.project.dto.user.UserLoginRequest;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.dto.user.UserUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserOrException(long userId) {
        return userRepository.findById(userId).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public void createUser(final UserSignupRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new CodeBloomException(ErrorCode.DUPLICATE_USERNAME);
        }
        userRepository.save(User.create(
                request.username(), passwordEncoder.encode(request.password()),
                request.nickname(), request.role())
        );
    }

    public String login(final UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(()->
                new CodeBloomException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CodeBloomException(ErrorCode.INVALID_PASSWORD);
        }
        return jwtUtil.generateToken(String.valueOf(user.getUserId()), user.getRole());
    }

    @Transactional
    public void updateUser(long userId, final UserUpdateRequest request) {
        User user = getUserOrException(userId);
        user.update(
                request.username(),
                (request.password()!=null)?passwordEncoder.encode(request.password()):null,
                request.nickname()
        );
    }

    @Transactional
    public void withdraw(long userId) {
        User user = getUserOrException(userId);
        user.deleteBase(String.valueOf(userId));
    }

    @Transactional
    public void deleteUser(long adminId, long userId) {
        User user = getUserOrException(userId);
        if(user.getIsDeleted()) {
            throw new CodeBloomException(ErrorCode.ALREADY_PROCESSED);
        }
        user.deleteBase(String.valueOf(adminId));
    }

}
