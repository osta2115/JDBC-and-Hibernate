package pl.sda.library.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    private Integer id;
}
