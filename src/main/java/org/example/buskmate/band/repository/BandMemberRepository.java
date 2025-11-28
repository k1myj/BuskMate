package org.example.buskmate.band.repository;

import org.example.buskmate.band.domain.BandMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BandMemberRepository extends JpaRepository<BandMember, Long> {

    List<BandMember> findAllByBand_BandId(String bandId);

    boolean existsByBand_BandIdAndUserId(String bandId, String userId);

    Optional<BandMember> findByBand_BandIdAndBandMemberId(String bandId, String bandMemberId);
}
