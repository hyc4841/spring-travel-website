package whang.travel.domain.paging.profilePost;

import lombok.Data;

@Data
public class MemberPostCriteria {

    private int pageNum;
    private int amount;
    private int skip;
    private String keyword;
    private Long memberId;

    public MemberPostCriteria() { // 파라미터 없이 기본으로 생성하였을 때, 1, 10의 값을 가지도록 함
        this(1, 10);
        this.skip = 0;
    }

    public MemberPostCriteria(int pageNum, int amount) {
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
