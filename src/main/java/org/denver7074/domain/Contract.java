package org.denver7074.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.denver7074.domain.common.IdentityEntity;
import org.denver7074.util.ColumnName;

import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract extends IdentityEntity {

    @ColumnName(name = "Номер договора")
    Integer numberContract;
    @ColumnName(name = "Номер договора")
    Integer numberCount;


}
