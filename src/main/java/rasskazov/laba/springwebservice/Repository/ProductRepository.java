package rasskazov.laba.springwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.springwebservice.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
}
