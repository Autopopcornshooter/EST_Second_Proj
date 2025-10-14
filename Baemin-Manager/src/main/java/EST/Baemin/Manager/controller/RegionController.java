package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Region;
import EST.Baemin.Manager.dto.RegionRequest;
import EST.Baemin.Manager.dto.RegionResponse;
import EST.Baemin.Manager.dto.UpdateRegionRequest;
import EST.Baemin.Manager.service.RegionService;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import EST.Baemin.Manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/regions")
public class RegionController {

  private final RegionService regionService;

  @Value("${GOOGLE_MAPS_API_KEY}")
  private String apiKey; // API Key 필드 추가

    // Regionpage.html
    @GetMapping
    public String getAllRegions(Model model) {
        List<RegionResponse> regionList = regionService.findAllRegion()
                .stream()
                .map(RegionResponse::new)
                .toList();
        model.addAttribute("regionList", regionList);
        model.addAttribute("apiKey", apiKey); // 뷰로 API Key 전달
        return "Regionpage";  // templates/Regionpage.html
    }

    // RegionUpdatepage.html
    @GetMapping("/update/{id}")
    public String getRegionForUpdate(@PathVariable Long id, Model model) {
        Region region = regionService.findById(id); // RegionService에 findById 메서드 필요
        model.addAttribute("region", new RegionResponse(region));
        model.addAttribute("apiKey", apiKey); // 뷰로 API Key 전달
        return "RegionUpdatepage"; // templates/RegionUpdatepage.html
    }

  // 등록 (폼 제출)
  @PostMapping
  public String createRegion(@ModelAttribute RegionRequest request) {
    Region savedRegion = regionService.saveRegion(request);
    return "redirect:/api/regions/update/" + savedRegion.getId();
  }

  // 수정 (폼 제출)
  @PostMapping("/update/{id}")
  public String updateRegion(@PathVariable Long id, @ModelAttribute UpdateRegionRequest request, Model model) {
    regionService.updateRegion(id, request);

    Region updatedRegion = regionService.findById(id);
    model.addAttribute("region", new RegionResponse(updatedRegion));
    model.addAttribute("apiKey", apiKey);

    return "RegionUpdatepage";
  }

  // 삭제
  @PostMapping("/delete/{id}")
  public String deleteRegion(@PathVariable Long id) {
    regionService.deleteRegion(id);
    return "redirect:/regions";
  }
}

