package org.example.buskmate.recruit.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.buskmate.recruit.post.domain.RecruitPostStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecruitPostListDto {
    private String postId;
    private String bandId;
    private String title;
    private RecruitPostStatus status;
    private LocalDateTime createdAt;
}
