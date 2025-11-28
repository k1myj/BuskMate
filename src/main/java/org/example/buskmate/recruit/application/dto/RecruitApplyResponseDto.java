package org.example.buskmate.recruit.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.buskmate.recruit.application.domain.RecruitApplicationStatus;

@Getter
@Builder
@AllArgsConstructor
public class RecruitApplyResponseDto {
    private String applicationId;
    private String postId;
    private String applicantId;
    private RecruitApplicationStatus status;
}
