package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Region;
import EST.Baemin.Manager.dto.RegionRequest;
import EST.Baemin.Manager.dto.UpdateRegionRequest;
import EST.Baemin.Manager.repository.RegionRepository;

import java.util.List;

import EST.Baemin.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    //기능 추가(이경원): User와의 join을 위해 지역 저장 할 때 현재 로그인 된 user 엔티티의 필드에 Region 삽입

    public Region save(RegionRequest request) {
        return regionRepository.save(request.toEntity());
    }

    public List<Region> findAllRegion() {
        return regionRepository.findAll();
    }

    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    public Region findById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("findById Not Found with id : " + id));
    }

    @Transactional
    public Region updateRegion(Long id, UpdateRegionRequest request) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("updateRegion Not Found with id : " + id));
        region.update(request.getAddress(), request.getLatitude(), request.getLongitude(), request.getGoogle_place_id());
        return region;
    }
}
