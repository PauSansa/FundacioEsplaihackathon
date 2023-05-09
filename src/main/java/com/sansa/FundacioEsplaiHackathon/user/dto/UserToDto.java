package com.sansa.FundacioEsplaiHackathon.user.dto;

import com.sansa.FundacioEsplaiHackathon.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserToDto implements Function<User,UserDto> {

    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .build();
    }


}
