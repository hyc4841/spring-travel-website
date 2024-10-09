package whang.travel.domain.paging.accommodation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccommoCriteria {

    private int pageNum;
    private int amount;
    private int skip;
    @NotBlank
    private String region;
    @NotNull
    private LocalDate checkIn;
    @NotNull
    private LocalDate checkOut;
    @NotNull
    private Integer personnel;

    public AccommoCriteria() { // 파라미터 없이 기본으로 생성하였을 때, 1, 10의 값을 가지도록 함
        this(1, 10);
        this.skip = 0;
    }

    public AccommoCriteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum - 1) * amount;
    }

    public void setPageNum(int pageNum) {
        this.skip = (pageNum - 1) * this.amount;
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        this.skip = (this.pageNum - 1) * amount;
        this.amount = amount;
    }


}
