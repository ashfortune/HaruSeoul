package com.m2j2.haruseoul.anonymous.program.dto;

import com.m2j2.haruseoul.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramDetailImageDto {

    private List<String> imgSrc;

}