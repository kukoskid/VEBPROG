package mk.ukim.finki.wp.lab.model;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Category {
    public String category;

    public Category(String category) {
        this.category = category;
     }
}
