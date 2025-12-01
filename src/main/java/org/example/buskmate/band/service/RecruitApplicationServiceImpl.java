package org.example.buskmate.band.service;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.RequiredArgsConstructor;
import org.example.buskmate.band.domain.*;
import org.example.buskmate.band.dto.recruitapplication.RecruitApplyResponseDto;
import org.example.buskmate.band.repository.RecruitApplicationRepository;
import org.example.buskmate.band.repository.RecruitPostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class RecruitApplicationServiceImpl implements RecruitApplicationService {
    private final RecruitApplicationRepository recruitApplicationRepository;
    private final RecruitPostRepository recruitPostRepository;

    @Transactional
    @Override
    public RecruitApplyResponseDto apply(String postId, String currentUserId){
        //모집글 조회
        RecruitPost post=recruitPostRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 모집 글을 찾을 수 없습니다."));

        //모집중인지 확인하기..
        if(post.getStatus() != RecruitPostStatus.OPEN){
            throw new IllegalStateException("지원할 수 없는 모집 글입니다.");
        }

        boolean alreadyApplied = recruitApplicationRepository.existsRecruitApplicationByRecruitPost_PostIdAndApplicantId(postId, currentUserId);

        //중복지원 금지
        if(alreadyApplied){
            throw new IllegalStateException("이미 이 모집 글에 지원한 상태입니다.");
        }

        String applicationId = UlidCreator.getUlid().toString();

        RecruitApplication application = RecruitApplication.builder()
                .applicationId(applicationId)
                .applicantId(currentUserId)
                .recruitPost(post)
                .build();

        recruitApplicationRepository.save(application);

        return RecruitApplyResponseDto.builder()
                .applicationId(application.getApplicationId())
                .postId(postId)
                .applicantId(currentUserId)
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();

    }

    @Transactional
    @Override
    public void delete(String applicationId, String currentUserId){
        RecruitApplication application = recruitApplicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지원 내역을 찾을 수 없습니다."));

        if(!application.getApplicantId().equals(currentUserId)){
            throw new AccessDeniedException("본인이 작성한 지원만 철회할 수 있습니다.");
        }

        if(application.getStatus() == RecruitApplicationStatus.ACCEPTED ){
            throw new IllegalStateException("이미 수락된 지원은 철회할 수 없습니다.");
        }

        application.delete();
    }
}
