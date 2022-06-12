package pl.sda.library.common;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class BookBasicInfo {

    private Integer id;
    private String title;

}
