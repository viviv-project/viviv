package ru.project.viviv.common;

import org.springframework.stereotype.Component;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.User;

import javax.validation.constraints.NotNull;

@Component
public class ConverterDTO {

    public UserDTO UserToDTO(@NotNull User user){
        return new UserDTO();
    }
}
