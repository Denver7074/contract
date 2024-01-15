package org.denver7074.domain;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.denver7074.domain.common.IdentityEntity;
import org.denver7074.util.ColumnName;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {

    @Id
    @ColumnName(name = "ИНН")
    Integer inn;
    @ColumnName(name = "Компании")
    String nameCompany;
    @ColumnName(name = "Юр адрес")
    String legalAddress;
    @ColumnName(name = "Факт адрес")
    String actualAddress;
    @ColumnName(name = "КПП")
    Integer kpp;
    @ColumnName(name = "Банк")
    String bank;
    @ColumnName(name = "Кор счёт")
    String correspondentAccount;
    @ColumnName(name = "Расчётный счет")
    String paymentAccount;
    @ColumnName(name = "БИК")
    Integer bic;
    @ColumnName(name = "ОГРН")
    Long ogrn;
    @ColumnName(name = "e-mail")
    String email;
    @ColumnName(name = "Телефон")
    String phone;
    @ColumnName(name = "ФИО")
    String fio;
    @ColumnName(name = "Должность")
    String post;
}
