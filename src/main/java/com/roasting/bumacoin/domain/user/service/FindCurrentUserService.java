package com.roasting.bumacoin.domain.user.service;

import com.roasting.bumacoin.domain.user.UserFacade;
import com.roasting.bumacoin.domain.user.domain.User;
import com.roasting.bumacoin.domain.user.presentation.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindCurrentUserService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserResponseDto execute() {
        User user = userFacade.getCurrentUser();

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
    }
}