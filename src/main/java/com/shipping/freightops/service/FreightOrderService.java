package com.shipping.freightops.service;

import com.shipping.freightops.dto.CreateFreightOrderRequest;
import com.shipping.freightops.entity.Agent;
import com.shipping.freightops.entity.Container;
import com.shipping.freightops.entity.FreightOrder;
import com.shipping.freightops.entity.Voyage;
import com.shipping.freightops.enums.VoyageStatus;
import com.shipping.freightops.repository.AgentRepository;
import com.shipping.freightops.repository.ContainerRepository;
import com.shipping.freightops.repository.FreightOrderRepository;
import com.shipping.freightops.repository.VoyageRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Handles freight order creation and queries. */
@Service
public class FreightOrderService {

  private final FreightOrderRepository orderRepository;
  private final VoyageRepository voyageRepository;
  private final ContainerRepository containerRepository;
  private final AgentRepository agentRepository;

  public FreightOrderService(
      FreightOrderRepository orderRepository,
      VoyageRepository voyageRepository,
      ContainerRepository containerRepository,
      AgentRepository agentRepository) {
    this.orderRepository = orderRepository;
    this.voyageRepository = voyageRepository;
    this.containerRepository = containerRepository;
    this.agentRepository = agentRepository;
  }

  @Transactional
  public FreightOrder createOrder(CreateFreightOrderRequest request) {
    Voyage voyage =
        voyageRepository
            .findById(request.getVoyageId())
            .orElseThrow(
                () -> new IllegalArgumentException("Voyage not found: " + request.getVoyageId()));

    if (voyage.getStatus() == VoyageStatus.CANCELLED) {
      throw new IllegalStateException("Cannot book freight on a cancelled voyage");
    }

    Container container =
        containerRepository
            .findById(request.getContainerId())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Container not found: " + request.getContainerId()));

    Agent agent =
        agentRepository
            .findById(request.getAgentId())
            .orElseThrow(
                () -> new IllegalArgumentException("Agent not found: " + request.getAgentId()));

    if (!agent.isActive()) {
      throw new IllegalStateException("Cannot place order with inactive agent: " + agent.getId());
    }

    FreightOrder order = new FreightOrder();
    order.setVoyage(voyage);
    order.setContainer(container);
    order.setAgent(agent);
    order.setNotes(request.getNotes());

    return orderRepository.save(order);
  }

  @Transactional(readOnly = true)
  public FreightOrder getOrder(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Freight order not found: " + id));
  }

  @Transactional(readOnly = true)
  public List<FreightOrder> getAllOrders() {
    return orderRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<FreightOrder> getOrdersByVoyage(Long voyageId) {
    return orderRepository.findByVoyageId(voyageId);
  }
}
