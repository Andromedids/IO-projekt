package pl.wojcikania.sfi.controllers;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.wojcikania.sfi.dto.Workshop;
import pl.wojcikania.sfi.service.WorkshopService;

@RestController
@AllArgsConstructor
@Slf4j
public class WorkshopController {

  @Autowired
  private WorkshopService workshopService;


  @GetMapping("/workshops")
  public List<Workshop> getWorkshops(){
    log.info("fetching all workshops");
    return workshopService.getWorkshops();
  }

  @GetMapping("/workshops/{workshopId}")
  public Workshop getWorkshopBy(@PathVariable String workshopId){
    log.info("fetching workshop with id " + workshopId);
    return workshopService.getWorkshop(Long.valueOf(workshopId));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleBadIdInRequest(IllegalArgumentException exception,
                                                     HttpServletRequest httpServletRequest) {
    log.warn("bad workshop id was provided");
    return new ResponseEntity<>("Provided id is not valid", HttpStatus.NOT_FOUND);
  }
}
