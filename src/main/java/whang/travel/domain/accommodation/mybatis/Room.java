package whang.travel.domain.accommodation.mybatis;

import lombok.Data;

@Data
public class Room {

    private Long roomId;           // pk
    private String name;            //
    private Integer personnel;      //
    private Integer maxPersonnel;  //
    private Long accommodation;     // foreign key accommodation

}
