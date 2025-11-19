package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.OrderRequest;
import com.devsoga.BookStore_V2.dtos.responses.OrderRespone;
import com.devsoga.BookStore_V2.dtos.responses.OrderDetailRespone;
import com.devsoga.BookStore_V2.enties.InvoiceDetailEntity;
import com.devsoga.BookStore_V2.enties.InvoiceEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.enties.EmployeeEntity;

import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.InvoiceRepository;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import com.devsoga.BookStore_V2.repositories.PriceHistoryRepository;
import com.devsoga.BookStore_V2.repositories.CustomerRepository;
import com.devsoga.BookStore_V2.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PromotionRepository promotionRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PromotionOrderRepository promotionOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.CartRepository cartRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.CouponRepository couponRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.InventoryRepository inventoryRepository;

    @Transactional
    public BaseRespone createOrder(OrderRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req.getCustomerCode() == null || req.getDetails() == null || req.getDetails().isEmpty()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("customerCode and details are required");
                resp.setData(null);
                return resp;
            }

            CustomerEntity customer = customerRepository.findByCustomerCode(req.getCustomerCode()).orElse(null);
            if (customer == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Customer not found");
                resp.setData(null);
                return resp;
            }

            EmployeeEntity employee = null;
            if (req.getEmployeeCode() != null) {
                employee = employeeRepository.findByEmployeeCode(req.getEmployeeCode()).orElse(null);
            }

            InvoiceEntity inv = new InvoiceEntity();
            String orderCode = req.getOrderType() == null ? "ORD_" + System.currentTimeMillis() : "ORD_" + System.currentTimeMillis();
            inv.setOrderCode(orderCode);
            // no boolean `status` column — use `orderStatus` only
            // determine order type and payment method
            InvoiceEntity.OrderType chosenType;
            try {
                chosenType = InvoiceEntity.OrderType.valueOf(req.getOrderType());
                inv.setOrderType(chosenType);
            } catch (Exception ex) {
                chosenType = InvoiceEntity.OrderType.Offline;
                inv.setOrderType(chosenType);
            }
            try { inv.setPaymentMethod(InvoiceEntity.PaymentMethod.valueOf(req.getPaymentMethod())); } catch (Exception ex) { inv.setPaymentMethod(InvoiceEntity.PaymentMethod.QR); }
            // set order status (store human-readable string).
            // If client provided a status, use it. Otherwise default:
            // - For Offline orders: 'confirmed'
            // - For other orders: 'pending'
            if (req.getOrderStatus() != null && !req.getOrderStatus().isBlank()) {
                inv.setOrderStatus(req.getOrderStatus());
            } else {
                if (chosenType == InvoiceEntity.OrderType.Offline) {
                    inv.setOrderStatus("confirmed");
                } else {
                    inv.setOrderStatus("pending");
                }
            }
            // set isPaid default based on order type: Offline -> true, Online -> false
            inv.setIsPaid(chosenType == InvoiceEntity.OrderType.Offline ? Boolean.TRUE : Boolean.FALSE);
            inv.setCustomerEntity(customer);
            if (employee != null) inv.setEmployeeEntity(employee);
            inv.setDiscount(req.getDiscount());
            // note: promotions will be handled through promotion_order table, not single promotion reference
            // set optional note and address
            inv.setNote(req.getNote());
            inv.setAddress(req.getAddress());
            // set optional phone number
            inv.setPhoneNumber(req.getPhoneNumber());

            List<InvoiceDetailEntity> details = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal sumLineDiscounts = BigDecimal.ZERO;
            int idx = 1;
            // First, aggregate requested quantities per product to validate inventory availability
            java.util.Map<String, Integer> requestedByProduct = new java.util.HashMap<>();
            for (com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest d : req.getDetails()) {
                if (d.getProductCode() == null || d.getQuantity() == null) continue;
                requestedByProduct.merge(d.getProductCode(), Math.max(1, d.getQuantity()), Integer::sum);
            }
            // Check availability for each product
            java.util.List<String> insufficient = new java.util.ArrayList<>();
            for (var entry : requestedByProduct.entrySet()) {
                String pcode = entry.getKey();
                int need = entry.getValue();
                Integer available = inventoryRepository.findTotalQuantityByProductCode(pcode);
                if (available == null) available = 0;
                if (available < need) {
                    insufficient.add(pcode + " (need=" + need + ", available=" + available + ")");
                }
            }
            if (!insufficient.isEmpty()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Insufficient inventory for products: " + String.join(", ", insufficient));
                resp.setData(null);
                return resp;
            }
            for (com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest d : req.getDetails()) {
                if (d.getProductCode() == null || d.getQuantity() == null) continue;
                ProductEntity prod = productRepository.findByProductCode(d.getProductCode()).orElse(null);
                if (prod == null) continue;
                BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(prod.getProductCode()).orElse(BigDecimal.ZERO);
                int qty = Math.max(1, d.getQuantity());
                BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(qty));

                // determine promotion to apply for this line (product-first)
                String appliedPromoCode = null;
                BigDecimal promotionValue = BigDecimal.ZERO; // Store original promotion value
                BigDecimal lineDiscount = BigDecimal.ZERO;
                BigDecimal finalUnitPrice = unitPrice; // Price after discount per unit
                
                try {
                    String reqPromo = d.getPromotionCode();
                    com.devsoga.BookStore_V2.enties.PromotionEntity promo = null;
                    if (reqPromo != null && !reqPromo.isBlank()) {
                        promo = promotionRepository.findByPromotionCode(reqPromo).orElse(null);
                    }
                    if (promo == null && prod.getPromotionCode() != null) {
                        promo = promotionRepository.findByPromotionCode(prod.getPromotionCode()).orElse(null);
                    }
                    if (promo != null && promo.getPromotionTypeEntity() != null && promo.getValue() != null) {
                        promotionValue = promo.getValue(); // Store original promotion value
                        appliedPromoCode = promo.getPromotionCode();
                        
                        // Calculate discount based on promotion type
                        if (promotionValue.compareTo(BigDecimal.ONE) < 0) {
                            // Percentage discount (< 1.0)
                            BigDecimal discountPerUnit = unitPrice.multiply(promotionValue);
                            finalUnitPrice = unitPrice.subtract(discountPerUnit);
                            lineDiscount = discountPerUnit.multiply(BigDecimal.valueOf(qty));
                        } else {
                            // Fixed amount discount (>= 1.0) 
                            finalUnitPrice = unitPrice.subtract(promotionValue);
                            if (finalUnitPrice.compareTo(BigDecimal.ZERO) < 0) {
                                finalUnitPrice = BigDecimal.ZERO;
                            }
                            lineDiscount = promotionValue.multiply(BigDecimal.valueOf(qty));
                        }
                    }
                } catch (Exception ignore) {
                    // if any issue computing promo, leave discount zero
                    promotionValue = BigDecimal.ZERO;
                    finalUnitPrice = unitPrice;
                }

                InvoiceDetailEntity det = new InvoiceDetailEntity();
                det.setOrderDetailCode("OD_" + System.currentTimeMillis() + "_" + (idx++));
                det.setProductEntity(prod);
                det.setQuantity(qty);
                det.setUnitPrice(unitPrice);
                det.setTotalAmount(lineTotal);
                det.setPromotionCode(appliedPromoCode);
                det.setDiscountValue(promotionValue); // Store original promotion value, not total discount
                det.setFinalPrice(finalUnitPrice.multiply(BigDecimal.valueOf(qty))); // Final price = discounted unit price * quantity
                det.setOrderEntity(inv);
                details.add(det);

                total = total.add(lineTotal);
                sumLineDiscounts = sumLineDiscounts.add(lineDiscount);
            }

            inv.setTotalAmount(total);

            // Calculate customer type promotion (promotion_customer_value)
            // Keep the raw promotion value (percentage as <1, fixed as >=1) so we can apply it per-detail
            BigDecimal promotionCustomerRaw = BigDecimal.ZERO;
            
            // Priority 1: Use client-provided promotion customer value (raw)
            if (req.getPromotionCustomerValue() != null) {
                promotionCustomerRaw = req.getPromotionCustomerValue();
            }
            // Priority 2: Use client-provided promotion code (raw value from promotion)
            else if (req.getPromotionCustomerCode() != null && !req.getPromotionCustomerCode().isBlank()) {
                var customerPromo = promotionRepository.findByPromotionCode(req.getPromotionCustomerCode()).orElse(null);
                if (customerPromo != null && customerPromo.getPromotionTypeEntity() != null && customerPromo.getValue() != null) {
                    promotionCustomerRaw = customerPromo.getValue();
                }
            }
            // Priority 3: Auto-calculate raw value from customer type (fallback)
            else if (customer != null && customer.getCustomerTypeEntity() != null && customer.getCustomerTypeEntity().getPromotionCode() != null) {
                var customerPromo = promotionRepository.findByPromotionCode(customer.getCustomerTypeEntity().getPromotionCode()).orElse(null);
                if (customerPromo != null && customerPromo.getPromotionTypeEntity() != null && customerPromo.getValue() != null) {
                    promotionCustomerRaw = customerPromo.getValue();
                }
            }

            // Calculate coupon discount raw value (percentage as <1, fixed as >=1)
            BigDecimal couponRaw = BigDecimal.ZERO;
            
            // Priority 1: Use client-provided coupon discount value (raw)
            if (req.getCouponDiscountValue() != null) {
                couponRaw = req.getCouponDiscountValue();
            }
            // Priority 2: Auto-calculate raw value from coupon code
            else if (req.getCouponCode() != null && !req.getCouponCode().isBlank()) {
                com.devsoga.BookStore_V2.enties.CouponEntity coupon = couponRepository.findByCouponCode(req.getCouponCode()).orElse(null);
                if (coupon == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Coupon not found");
                    resp.setData(null);
                    return resp;
                }
                // Validate coupon is active and not already used
                java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
                boolean inRange = coupon.getStartDate().before(coupon.getEndDate()) && coupon.getStartDate().before(now) && coupon.getEndDate().after(now);
                if (!coupon.getStatus() || !inRange) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Coupon is not valid or already used");
                    resp.setData(null);
                    return resp;
                }
                // coupon is valid -> use its raw value
                if (coupon.getPromotionTypeEntity() != null && coupon.getValue() != null) {
                    couponRaw = coupon.getValue();
                }
            }

            // Set discount metadata on invoice (store raw values so other places know percent vs fixed)
            inv.setPromotionCustomerValue(promotionCustomerRaw);
            inv.setCouponDiscountValue(couponRaw);
            
            // Manual discount from request (raw, could be percent <1 or fixed >=1)
            BigDecimal manualDiscount = req.getDiscount() == null ? BigDecimal.ZERO : req.getDiscount();
            inv.setDiscount(manualDiscount);

            // Calculate base totalAmount as sum of all detail finalPrices (after line-level discounts)
            BigDecimal totalAmountFromDetails = BigDecimal.ZERO;
            for (InvoiceDetailEntity detail : details) {
                totalAmountFromDetails = totalAmountFromDetails.add(detail.getFinalPrice() == null ? BigDecimal.ZERO : detail.getFinalPrice());
            }
            inv.setTotalAmount(totalAmountFromDetails);

            // Apply discount sequence based on orderType
            BigDecimal finalAmount = totalAmountFromDetails;
            
            // Step 1: Apply customer promotion (promotionCustomerValue) as a percent of totalAmount
            // finalAmount = totalAmount - promotionCustomerValue * totalAmount
            if (promotionCustomerRaw.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal customerDiscount = totalAmountFromDetails.multiply(promotionCustomerRaw);
                finalAmount = totalAmountFromDetails.subtract(customerDiscount);
            }
            
            // Step 2: Apply different discount sequences based on orderType
            if (chosenType == InvoiceEntity.OrderType.Offline) {
                // For Offline: totalAmount - promotionCustomerValue - discount
                // Apply manual discount
                if (manualDiscount.compareTo(BigDecimal.ZERO) > 0) {
                    if (manualDiscount.compareTo(BigDecimal.ONE) < 0) {
                        BigDecimal manualDiscountAmount = finalAmount.multiply(manualDiscount);
                        finalAmount = finalAmount.subtract(manualDiscountAmount);
                    } else {
                        finalAmount = finalAmount.subtract(manualDiscount);
                    }
                }
            } else {
                // For Online: totalAmount - promotionCustomerValue - couponDiscountValue
                // Apply coupon discount
                if (couponRaw.compareTo(BigDecimal.ZERO) > 0) {
                    if (couponRaw.compareTo(BigDecimal.ONE) < 0) {
                        BigDecimal couponDiscount = finalAmount.multiply(couponRaw);
                        finalAmount = finalAmount.subtract(couponDiscount);
                    } else {
                        finalAmount = finalAmount.subtract(couponRaw);
                    }
                }
            }
            
            // Ensure final amount is not negative
            if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
                finalAmount = BigDecimal.ZERO;
            }
            
            inv.setFinalAmount(finalAmount);
            inv.setOrderDetailList(details);

            InvoiceEntity saved = invoiceRepository.save(inv);

            // If order used a coupon code, mark the coupon as used (one-time use)
            try {
                if (req.getCouponCode() != null && !req.getCouponCode().isBlank()) {
                    var cpOpt = couponRepository.findByCouponCode(req.getCouponCode());
                    if (cpOpt.isPresent()) {
                        var cp = cpOpt.get();
                        if (cp.getStatus()) {
                            cp.setStatus(false);
                            couponRepository.save(cp);
                        }
                    }
                }
            } catch (Exception ignore) {}

            // after invoice is saved, clear customer's cart items
            try {
                java.util.List<com.devsoga.BookStore_V2.enties.CartEntity> carts = cartRepository.findByCustomerEntity_CustomerCode(customer.getCustomerCode());
                if (carts != null && !carts.isEmpty()) {
                    cartRepository.deleteAll(carts);
                }
            } catch (Exception ignore) {
                // do not fail order creation if cart cleanup fails
            }

            // After saving the invoice and clearing cart, reduce inventory by updating quantity_sold (FIFO)
            try {
                for (var entry : requestedByProduct.entrySet()) {
                    updateQuantitySoldForProduct(entry.getKey(), entry.getValue());
                }
            } catch (Exception ignore) {}

            // map to response and attach breakdown (if computed above)
            OrderRespone out = toResp(saved);
            try {
                // member/product/other variables exist only when promotionCodes processed
                out.setPromotionCustomerValue(saved.getPromotionCustomerValue());
                out.setCouponDiscountValue(saved.getCouponDiscountValue());
            } catch (Exception ignore) {
                // ignore if variables are not in scope
            }
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Order created");
            resp.setData(out);
        } catch (Exception e) {
            // ensure transaction rollback on unexpected errors
            try {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } catch (Exception ignore) {}
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create order: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<InvoiceEntity> invoices = invoiceRepository.findAll();
            List<OrderRespone> out = invoices.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            resp.setMessage("OK");
            resp.setData(out);
        } catch (Exception e) {
            resp.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to get orders: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String orderCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceRepository.findByOrderCode(orderCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get()));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch order: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCustomerCode(String customerCode) {
        BaseRespone resp = new BaseRespone();
        try {
            if (customerCode == null || customerCode.isBlank()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.BAD_REQUEST.value());
                resp.setMessage("customerCode is required");
                resp.setData(null);
                return resp;
            }
            var list = invoiceRepository.findByCustomerEntity_CustomerCode(customerCode);
            if (list == null || list.isEmpty()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
                resp.setMessage("No orders found for customer");
                resp.setData(null);
                return resp;
            }
            List<OrderRespone> out = new java.util.ArrayList<>();
            for (var inv : list) {
                out.add(toResp(inv));
            }
            resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(out);
        } catch (Exception ex) {
            resp.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch orders: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String orderCode, OrderRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceRepository.findByOrderCode(orderCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order not found");
                resp.setData(null);
                return resp;
            }
            InvoiceEntity inv = opt.get();
            if (req.getNote() != null) inv.setNote(req.getNote());
            if (req.getAddress() != null) inv.setAddress(req.getAddress());
            if (req.getPhoneNumber() != null) inv.setPhoneNumber(req.getPhoneNumber());
            if (req.getDiscount() != null) inv.setDiscount(req.getDiscount());
            if (req.getEmployeeCode() != null) {
                var emp = employeeRepository.findByEmployeeCode(req.getEmployeeCode()).orElse(null);
                if (emp != null) inv.setEmployeeEntity(emp);
            }
            if (req.getOrderType() != null) {
                try { inv.setOrderType(InvoiceEntity.OrderType.valueOf(req.getOrderType())); } catch (Exception ignored) {}
            }
            if (req.getOrderStatus() != null && !req.getOrderStatus().isBlank()) {
                inv.setOrderStatus(req.getOrderStatus());
            }
            if (req.getPaymentMethod() != null) {
                try { inv.setPaymentMethod(InvoiceEntity.PaymentMethod.valueOf(req.getPaymentMethod())); } catch (Exception ignored) {}
            }
            if (req.getCouponCode() != null && !req.getCouponCode().isBlank()) {
                // If client supplies a coupon code when updating an order, validate and apply it (one-time use)
                var coupon = couponRepository.findByCouponCode(req.getCouponCode()).orElse(null);
                if (coupon == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Coupon not found");
                    resp.setData(null);
                    return resp;
                }
                java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
                boolean inRange = coupon.getStartDate().before(coupon.getEndDate()) && coupon.getStartDate().before(now) && coupon.getEndDate().after(now);
                if (!coupon.getStatus() || !inRange) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Coupon is not valid or already used");
                    resp.setData(null);
                    return resp;
                }
                // apply coupon raw value to invoice metadata (actual final recalculation can be triggered by client or other flow)
                inv.setCouponDiscountValue(coupon.getValue());
                // mark coupon used immediately
                try { coupon.setStatus(false); couponRepository.save(coupon); } catch (Exception ignore) {}
            }
            InvoiceEntity saved = invoiceRepository.save(inv);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update order: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getDetails(String orderCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var list = invoiceDetailRepository.findByOrderEntity_OrderCode(orderCode);
            List<OrderDetailRespone> out = new java.util.ArrayList<>();
            if (list != null) {
                for (var d : list) {
                    OrderDetailRespone od = new OrderDetailRespone();
                    od.setOrderDetailCode(d.getOrderDetailCode());
                    if (d.getProductEntity() != null) od.setProductCode(d.getProductEntity().getProductCode());
                    od.setQuantity(d.getQuantity());
                    od.setUnitPrice(d.getUnitPrice());
                    od.setTotalAmount(d.getTotalAmount());
                    out.add(od);
                }
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(out);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch details: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone createDetail(String orderCode, com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceRepository.findByOrderCode(orderCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order not found");
                resp.setData(null);
                return resp;
            }
            InvoiceEntity inv = opt.get();
            if (req.getProductCode() == null || req.getQuantity() == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("productCode and quantity are required");
                resp.setData(null);
                return resp;
            }
            ProductEntity prod = productRepository.findByProductCode(req.getProductCode()).orElse(null);
            if (prod == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Product not found");
                resp.setData(null);
                return resp;
            }
            java.math.BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(prod.getProductCode()).orElse(java.math.BigDecimal.ZERO);
            int qty = Math.max(1, req.getQuantity());
            java.math.BigDecimal lineTotal = unitPrice.multiply(java.math.BigDecimal.valueOf(qty));

            // compute per-line promotion (product-first)
            String appliedPromoCode = null;
            java.math.BigDecimal promotionValue = java.math.BigDecimal.ZERO;
            java.math.BigDecimal finalUnitPrice = unitPrice;
            
            try {
                String reqPromo = req.getPromotionCode();
                com.devsoga.BookStore_V2.enties.PromotionEntity promo = null;
                if (reqPromo != null && !reqPromo.isBlank()) promo = promotionRepository.findByPromotionCode(reqPromo).orElse(null);
                if (promo == null && prod.getPromotionCode() != null) promo = promotionRepository.findByPromotionCode(prod.getPromotionCode()).orElse(null);
                if (promo != null && promo.getPromotionTypeEntity() != null && promo.getValue() != null) {
                    promotionValue = promo.getValue();
                    appliedPromoCode = promo.getPromotionCode();
                    
                    // Calculate discount based on promotion value
                    if (promotionValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                        // Percentage discount
                        java.math.BigDecimal discountPerUnit = unitPrice.multiply(promotionValue);
                        finalUnitPrice = unitPrice.subtract(discountPerUnit);
                    } else {
                        // Fixed amount discount
                        finalUnitPrice = unitPrice.subtract(promotionValue);
                        if (finalUnitPrice.compareTo(java.math.BigDecimal.ZERO) < 0) {
                            finalUnitPrice = java.math.BigDecimal.ZERO;
                        }
                    }
                }
            } catch (Exception ignore) {
                promotionValue = java.math.BigDecimal.ZERO;
                finalUnitPrice = unitPrice;
            }

            InvoiceDetailEntity det = new InvoiceDetailEntity();
            det.setOrderDetailCode("OD_" + System.currentTimeMillis());
            det.setProductEntity(prod);
            det.setQuantity(qty);
            det.setUnitPrice(unitPrice);
            det.setTotalAmount(lineTotal);
            det.setPromotionCode(appliedPromoCode);
            det.setDiscountValue(promotionValue); // Store original promotion value
            det.setFinalPrice(finalUnitPrice.multiply(java.math.BigDecimal.valueOf(qty))); // Final price = discounted unit price * quantity
            det.setOrderEntity(inv);

            invoiceDetailRepository.save(det);

            // recompute invoice totals from details to keep consistency
            var all = invoiceDetailRepository.findByOrderEntity_OrderCode(orderCode);
            java.math.BigDecimal sumFinalPrices = java.math.BigDecimal.ZERO;
            if (all != null) {
                for (var it : all) {
                    sumFinalPrices = sumFinalPrices.add(it.getFinalPrice() == null ? java.math.BigDecimal.ZERO : it.getFinalPrice());
                }
            }
            
            // Set totalAmount to sum of detail final prices
            inv.setTotalAmount(sumFinalPrices);
            
            // Apply order-level discounts with percentage/fixed logic
            java.math.BigDecimal finalAmount = sumFinalPrices;
            
            // Apply customer promotion discount
            java.math.BigDecimal customerPromoValue = inv.getPromotionCustomerValue() == null ? java.math.BigDecimal.ZERO : inv.getPromotionCustomerValue();
            if (customerPromoValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (customerPromoValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal customerDiscount = finalAmount.multiply(customerPromoValue);
                    finalAmount = finalAmount.subtract(customerDiscount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(customerPromoValue);
                }
            }
            
            // Apply coupon discount
            java.math.BigDecimal couponValue = inv.getCouponDiscountValue() == null ? java.math.BigDecimal.ZERO : inv.getCouponDiscountValue();
            if (couponValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (couponValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal couponDiscount = finalAmount.multiply(couponValue);
                    finalAmount = finalAmount.subtract(couponDiscount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(couponValue);
                }
            }
            
            // Apply manual discount
            java.math.BigDecimal manualDiscountValue = inv.getDiscount() == null ? java.math.BigDecimal.ZERO : inv.getDiscount();
            if (manualDiscountValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (manualDiscountValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal manualDiscountAmount = finalAmount.multiply(manualDiscountValue);
                    finalAmount = finalAmount.subtract(manualDiscountAmount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(manualDiscountValue);
                }
            }
            
            if (finalAmount.compareTo(java.math.BigDecimal.ZERO) < 0) finalAmount = java.math.BigDecimal.ZERO;
            inv.setFinalAmount(finalAmount);
            invoiceRepository.save(inv);

            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Detail added");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create detail: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone updateDetail(String orderCode, String detailCode, com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceDetailRepository.findByOrderDetailCode(detailCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order detail not found");
                resp.setData(null);
                return resp;
            }
            InvoiceDetailEntity det = opt.get();
            if (det.getOrderEntity() == null || det.getOrderEntity().getOrderCode() == null || !det.getOrderEntity().getOrderCode().equals(orderCode)) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Detail does not belong to order");
                resp.setData(null);
                return resp;
            }
            // oldTotal calculation removed as not used

            if (req.getQuantity() != null) det.setQuantity(Math.max(1, req.getQuantity()));
            if (req.getProductCode() != null) {
                ProductEntity p = productRepository.findByProductCode(req.getProductCode()).orElse(null);
                if (p != null) det.setProductEntity(p);
            }
            // recalc unit price and total using price history of product
            if (det.getProductEntity() != null) {
                java.math.BigDecimal unitPrice = priceHistoryRepository.findLatestActivePriceByProductCode(det.getProductEntity().getProductCode()).orElse(java.math.BigDecimal.ZERO);
                det.setUnitPrice(unitPrice);
                det.setTotalAmount(unitPrice.multiply(java.math.BigDecimal.valueOf(det.getQuantity() == null ? 1 : det.getQuantity())));
            }
            // recalc promotion/discount for updated detail (product-first or request-provided)
            try {
                String reqPromo = req.getPromotionCode();
                com.devsoga.BookStore_V2.enties.PromotionEntity promo = null;
                if (reqPromo != null && !reqPromo.isBlank()) promo = promotionRepository.findByPromotionCode(reqPromo).orElse(null);
                if (promo == null && det.getProductEntity() != null && det.getProductEntity().getPromotionCode() != null) promo = promotionRepository.findByPromotionCode(det.getProductEntity().getPromotionCode()).orElse(null);
                
                java.math.BigDecimal promotionValue = java.math.BigDecimal.ZERO;
                java.math.BigDecimal finalUnitPrice = det.getUnitPrice();
                String applied = null;
                
                if (promo != null && promo.getPromotionTypeEntity() != null && promo.getValue() != null) {
                    promotionValue = promo.getValue();
                    applied = promo.getPromotionCode();
                    
                    // Calculate discount based on promotion value
                    if (promotionValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                        // Percentage discount
                        java.math.BigDecimal discountPerUnit = det.getUnitPrice().multiply(promotionValue);
                        finalUnitPrice = det.getUnitPrice().subtract(discountPerUnit);
                    } else {
                        // Fixed amount discount
                        finalUnitPrice = det.getUnitPrice().subtract(promotionValue);
                        if (finalUnitPrice.compareTo(java.math.BigDecimal.ZERO) < 0) {
                            finalUnitPrice = java.math.BigDecimal.ZERO;
                        }
                    }
                }
                
                det.setPromotionCode(applied);
                det.setDiscountValue(promotionValue); // Store original promotion value
                det.setFinalPrice(finalUnitPrice.multiply(java.math.BigDecimal.valueOf(det.getQuantity() == null ? 1 : det.getQuantity()))); // Final price = discounted unit price * quantity
            } catch (Exception ignore) {
                det.setDiscountValue(java.math.BigDecimal.ZERO);
                det.setFinalPrice(det.getTotalAmount());
            }
            invoiceDetailRepository.save(det);

            // recompute invoice totals from details
            InvoiceEntity inv = det.getOrderEntity();
            var all = invoiceDetailRepository.findByOrderEntity_OrderCode(orderCode);
            java.math.BigDecimal sumFinalPrices = java.math.BigDecimal.ZERO;
            if (all != null) {
                for (var it : all) {
                    sumFinalPrices = sumFinalPrices.add(it.getFinalPrice() == null ? java.math.BigDecimal.ZERO : it.getFinalPrice());
                }
            }
            inv.setTotalAmount(sumFinalPrices);
            
            // Apply order-level discounts (customer promotion + coupon + manual discount)
            java.math.BigDecimal customerPromoValue = inv.getPromotionCustomerValue() == null ? java.math.BigDecimal.ZERO : inv.getPromotionCustomerValue();
            java.math.BigDecimal couponValue = inv.getCouponDiscountValue() == null ? java.math.BigDecimal.ZERO : inv.getCouponDiscountValue();
            java.math.BigDecimal manualDiscountValue = inv.getDiscount() == null ? java.math.BigDecimal.ZERO : inv.getDiscount();
            
            java.math.BigDecimal finalAmount = sumFinalPrices;
            
            // Apply customer promotion discount
            if (customerPromoValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (customerPromoValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal discountAmount = finalAmount.multiply(customerPromoValue);
                    finalAmount = finalAmount.subtract(discountAmount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(customerPromoValue);
                }
            }
            
            // Apply coupon discount
            if (couponValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (couponValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal discountAmount = finalAmount.multiply(couponValue);
                    finalAmount = finalAmount.subtract(discountAmount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(couponValue);
                }
            }
            
            // Apply manual discount
            if (manualDiscountValue.compareTo(java.math.BigDecimal.ZERO) > 0) {
                if (manualDiscountValue.compareTo(java.math.BigDecimal.ONE) < 0) {
                    // Percentage discount
                    java.math.BigDecimal discountAmount = finalAmount.multiply(manualDiscountValue);
                    finalAmount = finalAmount.subtract(discountAmount);
                } else {
                    // Fixed amount discount
                    finalAmount = finalAmount.subtract(manualDiscountValue);
                }
            }
            
            if (finalAmount.compareTo(java.math.BigDecimal.ZERO) < 0) finalAmount = java.math.BigDecimal.ZERO;
            inv.setFinalAmount(finalAmount);
            invoiceRepository.save(inv);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Detail updated");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update detail: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteDetail(String orderCode, String detailCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceDetailRepository.findByOrderDetailCode(detailCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order detail not found");
                resp.setData(null);
                return resp;
            }
            InvoiceDetailEntity det = opt.get();
            if (det.getOrderEntity() == null || det.getOrderEntity().getOrderCode() == null || !det.getOrderEntity().getOrderCode().equals(orderCode)) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Detail does not belong to order");
                resp.setData(null);
                return resp;
            }
            invoiceDetailRepository.delete(det);
            InvoiceEntity inv = det.getOrderEntity();
            // recompute totals from remaining details
            var all = invoiceDetailRepository.findByOrderEntity_OrderCode(orderCode);
            java.math.BigDecimal sumTotals = java.math.BigDecimal.ZERO;
            java.math.BigDecimal sumDiscounts = java.math.BigDecimal.ZERO;
            if (all != null) {
                for (var it : all) {
                    sumTotals = sumTotals.add(it.getTotalAmount() == null ? java.math.BigDecimal.ZERO : it.getTotalAmount());
                    sumDiscounts = sumDiscounts.add(it.getDiscountValue() == null ? java.math.BigDecimal.ZERO : it.getDiscountValue());
                }
            }
            inv.setTotalAmount(sumTotals);
            java.math.BigDecimal orderLevelDiscount3 = inv.getDiscount() == null ? java.math.BigDecimal.ZERO : inv.getDiscount();
            inv.setFinalAmount(sumTotals.subtract(sumDiscounts).subtract(orderLevelDiscount3));
            invoiceRepository.save(inv);

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Detail deleted");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete detail: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone softDelete(String orderCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = invoiceRepository.findByOrderCode(orderCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
                resp.setMessage("Order not found");
                resp.setData(null);
                return resp;
            }
            InvoiceEntity inv = opt.get();
            if (inv.getOrderStatus() != null && inv.getOrderStatus().equalsIgnoreCase("cancelled")) {
                // already cancelled - return success (idempotent)
                resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
                resp.setMessage("Order already deleted");
                resp.setData(toResp(inv));
                return resp;
            }
            // mark order status as cancelled when soft-deleting
            inv.setOrderStatus("cancelled");
            InvoiceEntity saved = invoiceRepository.save(inv);
            resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            resp.setMessage("Order deleted");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete order: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private OrderRespone toResp(InvoiceEntity inv) {
        if (inv == null) return null;
        OrderRespone r = new OrderRespone();
        r.setOrderCode(inv.getOrderCode());
        r.setDiscount(inv.getDiscount());
        r.setTotalAmount(inv.getTotalAmount());
        r.setFinalAmount(inv.getFinalAmount());
        if (inv.getCustomerEntity() != null) r.setCustomerCode(inv.getCustomerEntity().getCustomerCode());
        if (inv.getEmployeeEntity() != null) r.setEmployeeCode(inv.getEmployeeEntity().getEmployeeCode());
        // Populate customerName and employeeName using entities or repositories (by code) when available
        try {
            if (inv.getCustomerEntity() != null && inv.getCustomerEntity().getCustomerName() != null) {
                r.setCustomerName(inv.getCustomerEntity().getCustomerName());
            } else if (r.getCustomerCode() != null) {
                var custOpt = customerRepository.findByCustomerCode(r.getCustomerCode());
                if (custOpt.isPresent()) r.setCustomerName(custOpt.get().getCustomerName());
            }
        } catch (Exception ignore) {}
        // populate customerEmail from linked Account if available, otherwise fetch via customer repository
        try {
            if (inv.getCustomerEntity() != null && inv.getCustomerEntity().getAccountEntity() != null && inv.getCustomerEntity().getAccountEntity().getEmail() != null) {
                r.setCustomerEmail(inv.getCustomerEntity().getAccountEntity().getEmail());
            } else if (r.getCustomerCode() != null) {
                var custOpt2 = customerRepository.findByCustomerCode(r.getCustomerCode());
                if (custOpt2.isPresent() && custOpt2.get().getAccountEntity() != null) {
                    r.setCustomerEmail(custOpt2.get().getAccountEntity().getEmail());
                }
            }
        } catch (Exception ignore) {}
        try {
            if (inv.getEmployeeEntity() != null && inv.getEmployeeEntity().getEmployeeName() != null) {
                r.setEmployeeName(inv.getEmployeeEntity().getEmployeeName());
            } else if (r.getEmployeeCode() != null) {
                var empOpt = employeeRepository.findByEmployeeCode(r.getEmployeeCode());
                if (empOpt.isPresent()) r.setEmployeeName(empOpt.get().getEmployeeName());
            }
        } catch (Exception ignore) {}
        // Get first promotion code from promotion_order relationships if available
        if (inv.getPromotionOrderList() != null && !inv.getPromotionOrderList().isEmpty()) {
            var firstPromoOrder = inv.getPromotionOrderList().get(0);
            if (firstPromoOrder.getPromotionEntity() != null) {
                // promotion tracking now handled through separate promotion tables
            }
        }
        // populate discount breakdown from promotion_order rows if available
        java.math.BigDecimal memberDisc = java.math.BigDecimal.ZERO;
        java.math.BigDecimal productDisc = java.math.BigDecimal.ZERO;
        java.math.BigDecimal otherDisc = java.math.BigDecimal.ZERO;
        try {
            if (inv.getOrderCode() != null) {
                var promoOrders = promotionOrderRepository.findByOrderEntity_OrderCode(inv.getOrderCode());
                if (promoOrders != null && !promoOrders.isEmpty()) {
                    for (var po : promoOrders) {
                        var promo = po.getPromotionEntity();
                        if (promo == null) continue;
                        BigDecimal amt = po.getDiscountAmount() == null ? BigDecimal.ZERO : po.getDiscountAmount();
                        String customerPromoCode = null;
                        if (inv.getCustomerEntity() != null && inv.getCustomerEntity().getCustomerTypeEntity() != null) {
                            customerPromoCode = inv.getCustomerEntity().getCustomerTypeEntity().getPromotionCode();
                        }
                        if (customerPromoCode != null && customerPromoCode.equalsIgnoreCase(promo.getPromotionCode())) {
                            memberDisc = memberDisc.add(amt);
                        } else if (productRepository.countByPromotionCode(promo.getPromotionCode()) > 0) {
                            productDisc = productDisc.add(amt);
                        } else {
                            otherDisc = otherDisc.add(amt);
                        }
                    }
                } else {
                    // fallback: use invoice discount as total in 'other'
                    otherDisc = inv.getDiscount() == null ? BigDecimal.ZERO : inv.getDiscount();
                }
            }
        } catch (Exception ex) {
            // ignore and leave zeros/fallback
            otherDisc = inv.getDiscount() == null ? BigDecimal.ZERO : inv.getDiscount();
        }
        // old discount breakdown fields removed, using new schema fields
        r.setPromotionCustomerValue(inv.getPromotionCustomerValue());
        r.setCouponDiscountValue(inv.getCouponDiscountValue());
    r.setNote(inv.getNote());
    r.setAddress(inv.getAddress());
    r.setPhoneNumber(inv.getPhoneNumber());
    r.setIsPaid(inv.getIsPaid());
    // Populate orderType and paymentMethod as strings for the response
    if (inv.getOrderType() != null) r.setOrderType(inv.getOrderType().name());
    if (inv.getPaymentMethod() != null) r.setPaymentMethod(inv.getPaymentMethod().name());
    if (inv.getOrderStatus() != null) r.setOrderStatus(inv.getOrderStatus());
            // populate orderDate from created_date (BaseAuditable)
            try {
                var cd = inv.getCreatedDate();
                if (cd != null) r.setOrderDate(cd);
            } catch (Exception ignore) {}

            List<OrderDetailRespone> outDetails = new ArrayList<>();
        if (inv.getOrderDetailList() != null) {
            for (InvoiceDetailEntity d : inv.getOrderDetailList()) {
                OrderDetailRespone od = new OrderDetailRespone();
                od.setOrderDetailCode(d.getOrderDetailCode());
                if (d.getProductEntity() != null) {
                    od.setProductCode(d.getProductEntity().getProductCode());
                    try {
                        if (d.getProductEntity().getProductName() != null) od.setProductName(d.getProductEntity().getProductName());
                        if (d.getProductEntity().getImage() != null) od.setImage(d.getProductEntity().getImage());
                    } catch (Exception ignore) {}
                } else {
                    if (d.getProductEntity() != null) od.setProductCode(d.getProductEntity().getProductCode());
                }
                od.setQuantity(d.getQuantity());
                od.setUnitPrice(d.getUnitPrice());
                od.setTotalAmount(d.getTotalAmount());
                od.setPromotionCode(d.getPromotionCode());
                od.setDiscountValue(d.getDiscountValue());
                od.setFinalPrice(d.getFinalPrice());
                // fallback: if productName or image not set above, try to fetch by productCode
                try {
                    if ((od.getProductName() == null || od.getProductName().isBlank()) && od.getProductCode() != null) {
                        var pOpt = productRepository.findByProductCode(od.getProductCode());
                        if (pOpt.isPresent()) {
                            var p = pOpt.get();
                            if (p.getProductName() != null) od.setProductName(p.getProductName());
                            if (p.getImage() != null) od.setImage(p.getImage());
                        }
                    }
                } catch (Exception ignore) {}
                outDetails.add(od);
            }
        }
        r.setDetails(outDetails);
        return r;
    }

    /**
     * Update quantity_sold in import_invoice_detail when products are sold.
     * Uses FIFO approach - updates oldest approved import records first.
     */
    private void updateQuantitySoldForProduct(String productCode, int quantityToSell) {
        try {
            // Find all import_invoice_detail for this product from APPROVED invoices, ordered by creation date
            var importDetails = purchaseOrderDetailRepository.findByProductEntity_ProductCodeAndImportInvoiceEntity_StatusOrderByCreatedDateAsc(productCode, "APPROVED");
            
            int remainingToSell = quantityToSell;
            
            for (var detail : importDetails) {
                if (remainingToSell <= 0) break;
                
                int quantityImported = detail.getQuantityImported() == null ? 0 : detail.getQuantityImported();
                int quantitySold = detail.getQuantitySold() == null ? 0 : detail.getQuantitySold();
                int available = quantityImported - quantitySold;
                
                if (available > 0) {
                    int toSellFromThisBatch = Math.min(remainingToSell, available);
                    detail.setQuantitySold(quantitySold + toSellFromThisBatch);
                    
                    // Save the updated import_invoice_detail
                    purchaseOrderDetailRepository.save(detail);
                    
                    // Update corresponding inventory_detail quantities
                    purchaseOrderService.updateInventoryDetailQuantities(detail);
                    
                    remainingToSell -= toSellFromThisBatch;
                }
            }
        } catch (Exception ex) {
            // Log error but don't fail order creation
        }
    }
}
