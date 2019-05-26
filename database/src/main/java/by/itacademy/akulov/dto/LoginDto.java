package by.itacademy.akulov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    private Long id;
    private String email;
    private String password;
    private String role;
}
