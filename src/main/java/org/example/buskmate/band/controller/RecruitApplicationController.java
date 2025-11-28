package org.example.buskmate.band.controller;


import lombok.RequiredArgsConstructor;
import org.example.buskmate.band.dto.recruitapplication.RecruitApplyResponseDto;
import org.example.buskmate.band.service.RecruitApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitApplicationController {
    private final RecruitApplicationService recruitApplicationService;

    @PostMapping("/{postId}/apply")
    public ResponseEntity<RecruitApplyResponseDto> apply(@PathVariable String postId, @AuthenticationPrincipal CustomUser user){
        RecruitApplyResponseDto response = recruitApplicationService.apply(postId, user.getUserId());

        return ResponseEntity.ok(response);
    }
}
