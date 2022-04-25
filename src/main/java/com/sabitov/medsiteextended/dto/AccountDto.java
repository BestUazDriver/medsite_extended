package com.sabitov.medsiteextended.dto;

import com.sabitov.medsiteextended.models.Account;
import com.sabitov.medsiteextended.models.Account.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String name;
    private String email;
    private String university;
    private Role role;
    private State state;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .name(account.getName())
                .role(account.getRole())
                .state(account.getState())
                .university(account.getUniversity())
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
