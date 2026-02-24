package com.shipping.freightops.repository;

import com.shipping.freightops.entity.FreightOrder;
import com.shipping.freightops.enums.OrderStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreightOrderRepository extends JpaRepository<FreightOrder, Long> {

  List<FreightOrder> findByVoyageId(Long voyageId);

  List<FreightOrder> findByStatus(OrderStatus status);

  List<FreightOrder> findByAgentId(Long agentId);
}
