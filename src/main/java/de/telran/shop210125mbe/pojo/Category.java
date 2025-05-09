package de.telran.shop210125mbe.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor //конструктор с инициализацией всех параметров
@NoArgsConstructor //конструктор без аргументов
@Setter //для создания set
@Getter //для создания get
@ToString //для создания метода toString
@EqualsAndHashCode // для создания hashCode() и equals(Object o)
//@Data // заменяет набор вышеуказанных аннотаций (не всегда корректно!!!)
@Builder // создание объекта класса через паттерн Builder
public class Category {
    private Long categoryId;
    private String name;
}
