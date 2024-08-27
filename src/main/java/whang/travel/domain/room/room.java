package whang.travel.domain.room;

import lombok.Data;

@Data
public class room {

    private Long room_id;           // pk
    private String name;            //
    private Integer personnel;      //
    private Integer max_personnel;  //
    private Long accommodation;     // foreign key accommodation

}
