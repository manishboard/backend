package kr.manish.manishboard.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class TaskDto {

    private String id;

    @NotBlank(message="User Name is Required!!!")
    private String userName;

    @NotBlank(message="Details is Required!!!")
    private String details;

    @NotBlank(message="Priority is Required!!!")
    private Integer priority;

    @NotBlank(message="Status is Required!!!")
    private String status;
}
