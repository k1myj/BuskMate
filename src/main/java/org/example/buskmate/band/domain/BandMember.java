package org.example.buskmate.band.domain;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "band_members",
        indexes = {
                @Index(
                        name = "idx_band_member_band_member_id",
                        columnList = "band_member_id",
                        unique = true
                )
        })
@Entity
public class BandMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "band_member_id", nullable = false, length = 26, unique = true, updatable = false)
    private String bandMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private BandMemberRole role;

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    private BandMember(Band band, String userId, BandMemberRole role) {
        this.band = band;
        this.userId = userId;
        this.role = role;
    }

    public static BandMember join(Band band, String userId, BandMemberRole role) {
        return new BandMember(band, userId, role);
    }

    @PrePersist
    private void fillBandMemberIdIfNull() {
        if (this.bandMemberId == null || this.bandMemberId.isBlank()) {
            this.bandMemberId = UlidCreator.getUlid().toString();
        }
    }
}
