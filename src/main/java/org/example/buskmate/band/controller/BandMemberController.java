package org.example.buskmate.band.controller;

import lombok.RequiredArgsConstructor;
import org.example.buskmate.band.dto.bandmember.BandMemberListItemResponse;
import org.example.buskmate.band.dto.bandmember.BandMemberRegisterRequest;
import org.example.buskmate.band.service.BandMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bands")
public class BandMemberController {

    private final BandMemberService bandMemberService;

    @GetMapping("/{bandId}/members")
    public ResponseEntity<List<BandMemberListItemResponse>> getMembers(
            @PathVariable String bandId
    ) {
        List<BandMemberListItemResponse> members = bandMemberService.getMembers(bandId);
        return ResponseEntity.ok(members);
    }

    @PostMapping("/{bandId}/members/invite")
    public ResponseEntity<Void> inviteMember(
            @PathVariable String bandId,
            @AuthenticationPrincipal CustomUser leader,
            @RequestBody BandMemberRegisterRequest request  // 초대할 유저아이디
    ) {
        bandMemberService.inviteMember(
                bandId,
                leader.getUserId(),     // 초대하는 사람 - 리더
                request.getUserId()     // 초대받는 사람
        );
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bandId}/members/invitations/accept")
    public ResponseEntity<Void> acceptInvitation(
            @PathVariable String bandId,
            @AuthenticationPrincipal CustomUser user
    ) {
        bandMemberService.acceptInvitation(bandId, user.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bandId}/members/invitations/reject")
    public ResponseEntity<Void> rejectInvitation(
            @PathVariable String bandId,
            @AuthenticationPrincipal CustomUser user
    ) {
        bandMemberService.rejectInvitation(bandId, user.getUserId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bandId}/members/{targetUserId}")
    public ResponseEntity<Void> kickMember(
            @PathVariable String bandId,
            @PathVariable String targetUserId,
            @AuthenticationPrincipal CustomUser leader
    ) {
        bandMemberService.kickMember(bandId, leader.getUserId(), targetUserId);
        return ResponseEntity.noContent().build();
    }


}
