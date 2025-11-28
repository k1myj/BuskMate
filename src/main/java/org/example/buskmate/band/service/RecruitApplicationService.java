package org.example.buskmate.band.service;

import org.example.buskmate.band.dto.recruitapplication.RecruitApplyResponseDto;

public interface RecruitApplicationService {

    RecruitApplyResponseDto apply(String postId, String currentUserId);


}
