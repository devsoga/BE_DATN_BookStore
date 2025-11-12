package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.OrderRequest;
import com.devsoga.BookStore_V2.dtos.responses.OrderRespone;
import com.devsoga.BookStore_V2.dtos.responses.OrderDetailRespone;
import com.devsoga.BookStore_V2.enties.InvoiceDetailEntity;
import com.devsoga.BookStore_V2.enties.InvoiceEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.enties.EmployeeEntity;
import com.devsoga.BookStore_V2.enties.PromotionOrderEntity;
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
            // default order status set to true when creating
            inv.setStatus(Boolean.TRUE);
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

                InvoiceDetailEntity det = new InvoiceDetailEntity();
                det.setOrderDetailCode("OD_" + System.currentTimeMillis() + "_" + (idx++));
                det.setProductEntity(prod);
                det.setQuantity(qty);
                det.setUnitPrice(unitPrice);
                det.setTotalAmount(lineTotal);
                det.setOrderEntity(inv);
                details.add(det);

                total = total.add(lineTotal);
            }

            inv.setTotalAmount(total);

            // determine discount: if client provides promotionCodes, apply each and sum discounts
            BigDecimal totalDiscount = BigDecimal.ZERO;
            BigDecimal memberDiscount = BigDecimal.ZERO;
            BigDecimal productDiscount = BigDecimal.ZERO;
            BigDecimal otherDiscount = BigDecimal.ZERO;
            InvoiceEntity saved = null;

            if (req.getPromotionCodes() != null && !req.getPromotionCodes().isEmpty()) {
                // compute discounts per promotion and persist promotion_order rows after saving invoice
                for (String promoCode : req.getPromotionCodes()) {
                    if (promoCode == null || promoCode.isBlank()) continue;
                    var promo = promotionRepository.findByPromotionCode(promoCode).orElse(null);
                    if (promo == null) continue;

                    BigDecimal promoDiscount = BigDecimal.ZERO;
                    if (promo.getPromotionTypeEntity() != null && promo.getValue() != null) {
                        String pt = promo.getPromotionTypeEntity().getPromotionTypeCode();
                        if ("PT_PERCENT".equalsIgnoreCase(pt)) {
                            promoDiscount = total.multiply(promo.getValue());
                        } else if ("PT_FIXED".equalsIgnoreCase(pt)) {
                            promoDiscount = promo.getValue();
                        }
                    }
                    totalDiscount = totalDiscount.add(promoDiscount);

                    // classify promo into member/product/other
                    String customerPromoCode = null;
                    if (customer != null && customer.getCustomerTypeEntity() != null) {
                        customerPromoCode = customer.getCustomerTypeEntity().getPromotionCode();
                    }
                    if (customerPromoCode != null && customerPromoCode.equalsIgnoreCase(promo.getPromotionCode())) {
                        memberDiscount = memberDiscount.add(promoDiscount);
                    } else if (productRepository.countByPromotionCode(promo.getPromotionCode()) > 0) {
                        productDiscount = productDiscount.add(promoDiscount);
                    } else {
                        otherDiscount = otherDiscount.add(promoDiscount);
                    }
                }

                // ensure discount doesn't exceed total
                if (totalDiscount.compareTo(total) > 0) totalDiscount = total;

                inv.setDiscount(totalDiscount);
                inv.setFinalAmount(total.subtract(totalDiscount));
                inv.setOrderDetailList(details);

                saved = invoiceRepository.save(inv);

                // persist each promotion_order separately (save with computed promoDiscount)
                for (String promoCode : req.getPromotionCodes()) {
                    if (promoCode == null || promoCode.isBlank()) continue;
                    var promo = promotionRepository.findByPromotionCode(promoCode).orElse(null);
                    if (promo == null) continue;
                    BigDecimal promoDiscount = BigDecimal.ZERO;
                    if (promo.getPromotionTypeEntity() != null && promo.getValue() != null) {
                        String pt = promo.getPromotionTypeEntity().getPromotionTypeCode();
                        if ("PT_PERCENT".equalsIgnoreCase(pt)) {
                            promoDiscount = total.multiply(promo.getValue());
                        } else if ("PT_FIXED".equalsIgnoreCase(pt)) {
                            promoDiscount = promo.getValue();
                        }
                    }
                    // cap per-promotion discount so cumulative doesn't exceed total
                    if (promoDiscount.compareTo(BigDecimal.ZERO) > 0) {
                        PromotionOrderEntity po = new PromotionOrderEntity();
                        po.setPromotionOrderCode("PO_" + System.currentTimeMillis() + "_" + promo.getPromotionCode());
                        po.setOrderEntity(saved);
                        po.setPromotionEntity(promo);
                        po.setDiscountAmount(promoDiscount);
                        promotionOrderRepository.save(po);
                    }
                }

                // Note: promotions are now managed through promotion_order junction table
                // No need to set single promotion reference on invoice entity
                // attach discount breakdown to response via saved entity's final mapping below
            } else {
                // no promotionCodes provided, fallback to client discount field
                BigDecimal discount = req.getDiscount() == null ? BigDecimal.ZERO : req.getDiscount();
                if (discount.compareTo(total) > 0) discount = total;
                inv.setDiscount(discount);
                inv.setFinalAmount(total.subtract(discount));
                inv.setOrderDetailList(details);

                saved = invoiceRepository.save(inv);
            }

            // after invoice is saved, clear customer's cart items
            if (saved != null) {
                try {
                    java.util.List<com.devsoga.BookStore_V2.enties.CartEntity> carts = cartRepository.findByCustomerEntity_CustomerCode(customer.getCustomerCode());
                    if (carts != null && !carts.isEmpty()) {
                        cartRepository.deleteAll(carts);
                    }
                } catch (Exception ignore) {
                    // do not fail order creation if cart cleanup fails
                }
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
                out.setMemberDiscount(memberDiscount);
                out.setProductDiscount(productDiscount);
                out.setOtherDiscount(otherDiscount);
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

    private OrderRespone toResp(InvoiceEntity inv) {
        if (inv == null) return null;
        OrderRespone r = new OrderRespone();
        r.setOrderCode(inv.getOrderCode());
        r.setStatus(inv.getStatus());
        r.setDiscount(inv.getDiscount());
        r.setTotalAmount(inv.getTotalAmount());
        r.setFinalAmount(inv.getFinalAmount());
        if (inv.getCustomerEntity() != null) r.setCustomerCode(inv.getCustomerEntity().getCustomerCode());
        if (inv.getEmployeeEntity() != null) r.setEmployeeCode(inv.getEmployeeEntity().getEmployeeCode());
        // Get first promotion code from promotion_order relationships if available
        if (inv.getPromotionOrderList() != null && !inv.getPromotionOrderList().isEmpty()) {
            var firstPromoOrder = inv.getPromotionOrderList().get(0);
            if (firstPromoOrder.getPromotionEntity() != null) {
                r.setPromotionCode(firstPromoOrder.getPromotionEntity().getPromotionCode());
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
        r.setMemberDiscount(memberDisc);
        r.setProductDiscount(productDisc);
        r.setOtherDiscount(otherDisc);
    r.setNote(inv.getNote());
    r.setAddress(inv.getAddress());
    r.setPhoneNumber(inv.getPhoneNumber());
    r.setIsPaid(inv.getIsPaid());

            List<OrderDetailRespone> outDetails = new ArrayList<>();
        if (inv.getOrderDetailList() != null) {
            for (InvoiceDetailEntity d : inv.getOrderDetailList()) {
                OrderDetailRespone od = new OrderDetailRespone();
                od.setOrderDetailCode(d.getOrderDetailCode());
                if (d.getProductEntity() != null) od.setProductCode(d.getProductEntity().getProductCode());
                od.setQuantity(d.getQuantity());
                od.setUnitPrice(d.getUnitPrice());
                od.setTotalAmount(d.getTotalAmount());
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
