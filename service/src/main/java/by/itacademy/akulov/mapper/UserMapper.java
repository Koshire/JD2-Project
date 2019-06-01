package by.itacademy.akulov.mapper;

import by.itacademy.akulov.dto.LoginDto;
import by.itacademy.akulov.entity.Role;
import by.itacademy.akulov.entity.User;

public class UserMapper implements BaseMapper<User, LoginDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public LoginDto mapToDto(User entity) {
        return LoginDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole().toString())
                .build();
    }

    @Override
    public User mapToEntity(LoginDto dto) {
        return new User()
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setRole(Role.valueOf(dto.getRole()));
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
