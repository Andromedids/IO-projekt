package pl.wojcikania.sfi.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wojcikania.sfi.dto.Presenter;
import pl.wojcikania.sfi.service.PresenterService;

@RestController
@Slf4j
public class PresenterController {

  private PresenterService presenterService;

  public PresenterController(@Autowired PresenterService presenterService) {
    this.presenterService = presenterService;
  }

  @GetMapping("/presenters")
  public ResponseEntity<List<Presenter>> getPresenters() {
    log.info("fetching all Presenters");
    return new ResponseEntity<>(presenterService.getPresenters(), OK);
  }

  @GetMapping("/presenters/{presenterId}")
  public ResponseEntity<Presenter> getPresenterBy(@PathVariable("presenterId") long presenterId) {
    log.info("fetching Presenter with id " + presenterId);
    var presenter = presenterService.getPresenter(presenterId);
    return new ResponseEntity<>(presenter, OK);
  }

  @PostMapping("/presenters/")
  public ResponseEntity<PresenterDto> addPresenter(@RequestBody Presenter presenter) {
    log.info("fetching Presenter with new id");
    var presenterId = presenterService.savePresenter(presenter);
    var newPresenterDto = PresenterDto.builder()
        .presenterId(presenterId)
        .build();
    return new ResponseEntity<>(newPresenterDto, CREATED);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleBadIdInRequest(
      IllegalArgumentException exception,
      HttpServletRequest httpServletRequest
  ) {
    log.warn("bad presenter id was provided");
    return new ResponseEntity<>("Provided id is not valid", HttpStatus.NOT_FOUND);
  }
}
