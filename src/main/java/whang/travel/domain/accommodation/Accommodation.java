package whang.travel.domain.accommodation;

import lombok.Data;

import java.util.List;

@Data
public class Accommodation {


    private Long accommodationId;   // pk
    private String name;
    private String location;
    private String category;
    private String introduction;
    private List<String> service;
    private String information;
    private Long seller;            // seller foreign key

}
