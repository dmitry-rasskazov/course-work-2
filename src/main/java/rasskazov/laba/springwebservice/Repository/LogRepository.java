package rasskazov.laba.springwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.springwebservice.Entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>
{
}
