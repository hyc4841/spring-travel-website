package whang.travel.domain.paging.accommodation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import whang.travel.domain.paging.bulletinboard.Criteria;

@Slf4j
@Data
public class PageMakerAccommo {

    private int startPage;
    private int endPage;
    private boolean prev, next;
    private int total;
    private AccommoCriteria criteria;

    public PageMakerAccommo(AccommoCriteria criteria, int total) {
        this.criteria = criteria;
        this.total = total;

        this.endPage = (int)(Math.ceil(criteria.getPageNum() / 10.0)) * 10;
        this.startPage = this.endPage - 9;

        int realEnd = (int) (Math.ceil(total * 1.0 / criteria.getAmount()));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        log.info("startPage={}", startPage);
        log.info("endPage={}", endPage);
        log.info("prev={}", prev);
        log.info("next={}", next);
        log.info("total={}", total);

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
