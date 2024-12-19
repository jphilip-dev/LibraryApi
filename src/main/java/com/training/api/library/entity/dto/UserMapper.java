package com.training.api.library.entity.dto;
import java.util.List;
import java.util.stream.Collectors;

import com.training.api.library.entity.User;

public class UserMapper {
    
    public static UserDTO toDTO(User user) {
        List<RoleDTO> roleDTOs = user.getRoles().stream()
                                    .map(role -> new RoleDTO(role.getRole()))
                                    .collect(Collectors.toList());
        
        return new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName(), user.getStatus(), roleDTOs);
    }
}

