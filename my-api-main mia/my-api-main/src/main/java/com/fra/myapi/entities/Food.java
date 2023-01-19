package com.fra.myapi.entities;

import com.fra.myapi.dto.requests.FoodRequestDTO;
import lombok.*;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    public Food(@NotNull FoodRequestDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Food food = (Food) o;
        return id != null && Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
