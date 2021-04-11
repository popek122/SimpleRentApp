package net.popocode.springrent.components.category;

import net.popocode.springrent.components.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category>findByNameIgnoreCase(String name);
}
