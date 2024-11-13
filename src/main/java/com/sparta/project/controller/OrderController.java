package com.sparta.project.controller;

import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.order.OrderCreateRequest;
import com.sparta.project.service.OrderService;
import com.sparta.project.util.PermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PermissionValidator permissionValidator;

//    // 자신의 주문내역 목록 조회(CUSTOMER)
//    @GetMapping("/my")
//    public ApiResponse<PageResponse<OrderResponse>> getMyOrders(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<OrderResponse> orders = orderService.getMyOrders(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(orders));
//    }
//
//    // 주문내역 상세 조회(CUSTOMER)
//    @GetMapping("/{order_id}")
//    public ApiResponse<OrderResponse> getOrderById(@PathVariable String order_id) {
//        OrderResponse order = orderService.getOrderById(order_id);
//        return ApiResponse.success(order);
//    }
//
//    // 고객의 주문내역 목록 조회(OWNER, MANAGER, MASTER)
//    @GetMapping("/users")
//    public ApiResponse<PageResponse<OrderResponse>> getAllOrdersByUser(
//            @RequestParam Long userId,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<OrderResponse> orders = orderService.getAllOrdersByUser(userId, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(orders));
//    }
//
    // 주문 요청(CUSTOMER, OWNER)
    @PostMapping
    public ApiResponse<String> createOrder(@RequestBody @Validated OrderCreateRequest request,
                                           Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name(), Role.OWNER.name());
        return ApiResponse.success(orderService.createOrder(Long.parseLong(authentication.getName()), request));
    }
//
//    // 주문 승인(OWNER)
//    @PatchMapping("/{order_id}")
//    public ApiResponse<OrderResponse> updateOrderStatus(
//            @PathVariable String order_id,
//            @RequestBody OrderRequest orderRequest) {
//        OrderResponse updatedOrder = orderService.updateOrderStatus(order_id, orderRequest);
//        return ApiResponse.success(updatedOrder);
//    }
//
//    // 주문 취소(CUSTOMER, OWNER)
//    @DeleteMapping("/{order_id}")
//    public ApiResponse<Void> deleteOrder(@PathVariable Long order_id) {
//        orderService.deleteOrder(order_id);
//        return ApiResponse.success();
//    }
}
