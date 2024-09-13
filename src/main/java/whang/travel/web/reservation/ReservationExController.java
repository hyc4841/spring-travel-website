package whang.travel.web.reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import whang.travel.web.reservation.errorForm.ReservationApiErrorForm;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ReservationExController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ReservationApiErrorForm illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ReservationApiErrorForm("BAD REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ReservationApiErrorForm exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ReservationApiErrorForm("EX", "서버 내부 오류");
    }



}
