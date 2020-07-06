package com.course.calypso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Golfer {
    private String first;
    private String last;
    private int score;
    // ... other methods ...
}