package org.example.buskmate.map.repository;

import org.example.buskmate.map.domain.MapMarker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapMarkerRepository extends JpaRepository<MapMarker, Long> {
}
