package org.example.buskmate.band.controller;

import lombok.RequiredArgsConstructor;
import org.example.buskmate.band.dto.bandmember.BandMemberListItemResponse;
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
            @AuthenticationPrincipal CustomUser user
    ) {
        String userId = user.getUserId();

        bandMemberService.inviteMember(bandId, userId);
        return ResponseEntity.noContent().build();
    }

}
