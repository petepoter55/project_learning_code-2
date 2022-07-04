package com.project.market.entity.repository.payment;

import com.project.market.entity.payment.HistoryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryOrderRepository extends JpaRepository<HistoryOrder, Integer> {
    List<HistoryOrder> findByUsername(String username);
}
