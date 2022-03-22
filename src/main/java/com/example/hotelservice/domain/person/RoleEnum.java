package com.example.hotelservice.domain.person;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleEnum {

    GUEST("Гость"),
    DISPATCHER("Диспетчер"),
    WORKER("Сотрудник"),
    ADMIN("Администратор");

    private final String name;
}
