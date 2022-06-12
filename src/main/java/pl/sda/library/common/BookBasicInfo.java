package pl.sda.library.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookBasicInfo {

    private int id;
    private String title;

}
