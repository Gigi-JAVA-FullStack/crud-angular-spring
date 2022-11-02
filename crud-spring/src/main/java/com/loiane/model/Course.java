package com.loiane.model;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.loiane.enums.Category;
import com.loiane.enums.Status;
import com.loiane.enums.converters.CategoryConverter;
import com.loiane.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 200)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 8, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status;

    // @NotNull
    // @NotEmpty
    @Valid
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Singular
    @ToString.Exclude
    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "courseID", referencedColumnName = "id")
    private Set<Lesson> lessons;
}
