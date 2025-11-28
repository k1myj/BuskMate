package org.example.buskmate.recruit.application.service;

import lombok.RequiredArgsConstructor;
import org.example.buskmate.recruit.application.dto.RecruitApplyResponseDto;
import org.example.buskmate.recruit.application.repository.RecruitApplicationRepository;
import org.example.buskmate.recruit.post.repository.RecruitPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class RecruitApplicationServiceImpl implements RecruitApplicationService{
    private final RecruitApplicationRepository recruitApplicationRepository;
    private final RecruitPostRepository recruitPostRepository;

    @Transactional
    @Override
    public RecruitApplyResponseDto apply(String postId, String currentUserId){

    }
}
