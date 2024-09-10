package whang.travel.domain.accommodation.mybatis;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Room {

    private Long roomId;           // pk
    private String name;            //
    private Integer personnel;      //
    private Integer maxPersonnel;  //
    private String roomType;       //
    private Integer basePrice;      //
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Long accommodation;     // foreign key accommodation

}
