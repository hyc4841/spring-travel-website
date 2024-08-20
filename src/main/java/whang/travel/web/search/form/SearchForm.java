package whang.travel.web.search.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchForm {

    @NotBlank
    private String searchTitle;
}
