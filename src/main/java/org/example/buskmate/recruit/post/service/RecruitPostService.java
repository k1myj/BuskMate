package org.example.buskmate.recruit.post.service;

import org.example.buskmate.recruit.post.dto.CreateRecruitPostRequestDto;
import org.example.buskmate.recruit.post.dto.CreateRecruitPostResponseDto;
import org.example.buskmate.recruit.post.dto.RecruitPostDetailResponseDto;
import org.example.buskmate.recruit.post.dto.RecruitPostListDto;

import java.util.List;

public interface RecruitPostService {
    CreateRecruitPostResponseDto create(CreateRecruitPostRequestDto req, String currentUserId);

    RecruitPostDetailResponseDto getDetail(String postId);

    List<RecruitPostListDto> getActiveList();

}
