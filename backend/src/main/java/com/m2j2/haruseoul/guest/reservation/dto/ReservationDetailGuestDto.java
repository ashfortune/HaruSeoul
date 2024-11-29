package com.m2j2.haruseoul.guest.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailGuestDto {
    //memberName, memberEmail
    private String memberName;

    private String memberEmail;

//    private String memberPhone;

//    private String memberSns;

}
