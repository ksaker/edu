package ru.platonov.edu.jpa;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Created by platonov on 02/04/17.
 * <a href="http://www.oracle.com/technetwork/middleware/ias/toplink-jpa-annotations-096251.html#CHDDBCAF">doc</a>
 */
@Entity
@Table(name = "TableName",     //Имя таблицы
        catalog = "RECORDS", //Some databases using catalogs to separate users schemas
        schema = "RECORDS", //Некторые базы данных используют разграничение доступа по пользователям называя их схемами
        uniqueConstraints = { //Аналог unique из аннотации @Column - нужен для генерации таблиц, показывает уникальность значений колонок
        @UniqueConstraint(columnNames = "primary_key_first_field, primary_key_second_field")
},
        indexes = {  //Индексы нужны для генерации таблиц при помощи хибернейта
            @Index(name = "unique_index_name", columnList = "first_index_column, second_index_column", unique = true),
                @Index(name = "not_unique_index_name", columnList = "third_column", unique = false)
        })
public class Employee implements Serializable {


}
