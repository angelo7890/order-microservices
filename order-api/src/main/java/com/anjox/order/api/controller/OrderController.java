package com.anjox.order.api.controller;

import com.anjox.order.api.model.OrderModel;
import com.anjox.order.api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Recurso para criar um novo pedido")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Operation(summary = "Cria um novo pedido",
                description = "Contem as operacoes para criar um novo pedido",
                responses = @ApiResponse(responseCode = "201",
                                        description = "Pedido criado com sucesso",
                                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class)))
    )
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
        logger.info("pedido recebido: {}", order.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.enqueue(order));
    }
}
