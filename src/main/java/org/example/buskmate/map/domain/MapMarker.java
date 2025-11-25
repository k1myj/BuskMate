package org.example.buskmate.map.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "map_marker")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class MapMarker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "marker_type", nullable = false)
    private MarkerType markerType;

    @Column(name = "band_id")
    private String bandId;

    @Column(name = "busking_id")
    private String buskingId;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lng", nullable = false)
    private double lng;

    @Column(nullable = false)
    private String title;

    private String summary;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;




    private MapMarker(
            MarkerType markerType,
            String bandId,
            String buskingId,
            double lat,
            double lng,
            String title,
            String summary
    ) {
        this.markerType = markerType;
        this.bandId = bandId;
        this.buskingId = buskingId;
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.summary = summary;
        validateTarget();
    }

    private void validateTarget() {
        if (markerType == MarkerType.BAND && bandId == null) {
            throw new IllegalArgumentException("BAND 마커는 bandId가 필요합니다.");
        }
        if (markerType == MarkerType.BUSKING && buskingId == null) {
            throw new IllegalArgumentException("BUSKING 마커는 buskingId가 필요합니다.");
        }
    }

    public static MapMarker bandMarker(String bandId, double lat, double lng, String title, String summary) {
        return new MapMarker(
                MarkerType.BAND,
                bandId,
                null,
                lat,
                lng,
                title,
                summary
        );
    }

    public static MapMarker buskingMarker(String buskingId, double lat, double lng, String title, String summary) {
        return new MapMarker(
                MarkerType.BUSKING,
                null,
                buskingId,
                lat,
                lng,
                title,
                summary
        );
    }

    @PrePersist
    void onPrePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
