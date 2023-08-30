package com.example.multiplicationtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MultiplicationTableRepository extends JpaRepository<MultiplicationTable, Long> {
List<MultiplicationTable> getByNumber(int number);
}
