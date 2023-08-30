package com.example.multiplicationtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ChatTableRepository extends JpaRepository<ChatMessageTable, Long> {
List<ChatMessageTable> findAll();
}
