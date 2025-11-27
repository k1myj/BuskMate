package org.example.buskmate.recruit.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRecruitPostRequestDto {
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 2000)
    private String content;
}
