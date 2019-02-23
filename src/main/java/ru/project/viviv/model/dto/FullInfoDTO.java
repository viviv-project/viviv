package ru.project.viviv.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FullInfoDTO {
    private UserDTO userDto;
    private ProfileDTO profileDto;
}
