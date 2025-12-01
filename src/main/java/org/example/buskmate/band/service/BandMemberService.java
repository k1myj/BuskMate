package org.example.buskmate.band.service;

import org.example.buskmate.band.dto.bandmember.BandMemberListItemResponse;

import java.util.List;

public interface BandMemberService {

    List<BandMemberListItemResponse> getMembers(String bandId);

    void inviteMember(String bandId, String userId);

}
