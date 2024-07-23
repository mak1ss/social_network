package com.practice.social_network.model;

import com.practice.social_network.model.base.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Identifiable {

    private Integer id;

    private String refreshToken;

    private LocalDateTime expirationDate;

    private User user;
}
