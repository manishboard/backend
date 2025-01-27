package kr.manish.manishboard.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    // private String id;

    @NotBlank(message="User Name is Required!!!")
    private String userName;

    @NotBlank(message="User Name is Required!!!")
    @Size(min=3, max=25, message = "name must be between 3 to 25 letters")
    private String name;

    @NotBlank(message="Password is Required!!!")
    private String password;

    @Email(message = "Must be Valid Email")
    private String email;

    @NotBlank(message="Gender is Required!!!")
    private String gender;
    
}
