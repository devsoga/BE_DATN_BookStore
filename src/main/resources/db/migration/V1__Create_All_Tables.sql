-- =========================
-- ROLE & ACCOUNT TABLES
-- =========================
CREATE TABLE IF NOT EXISTS `role` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `role_code` VARCHAR(50) UNIQUE NOT NULL,
    `role_name` VARCHAR(255),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `account` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `account_code` VARCHAR(50) UNIQUE NOT NULL,
    `username` VARCHAR(100) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(20),
    `email` VARCHAR(255),
    `role_code` VARCHAR(50),
    `status` BOOLEAN DEFAULT TRUE,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`role_code`) REFERENCES `role`(`role_code`)
);

-- =========================
-- CUSTOMER TYPE & CUSTOMER
-- =========================
CREATE TABLE IF NOT EXISTS `customer_type` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_type_code` VARCHAR(50) UNIQUE NOT NULL,
    `customer_type_name` VARCHAR(255),
    `promotion_code` VARCHAR(50),
    `description` VARCHAR(255),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `customer` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `customer_code` VARCHAR(50) UNIQUE NOT NULL,
    `customer_name` VARCHAR(255),
    `points` DECIMAL(10,2) DEFAULT 0,
    `address` VARCHAR(255),
    `status` BOOLEAN DEFAULT TRUE,
    `customer_type_code` VARCHAR(50),
    `account_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_type_code`) REFERENCES `customer_type`(`customer_type_code`),
    FOREIGN KEY (`account_code`) REFERENCES `account`(`account_code`)
);

-- =========================
-- EMPLOYEE
-- =========================
CREATE TABLE IF NOT EXISTS `employee` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `employee_code` VARCHAR(50) UNIQUE NOT NULL,
    `employee_name` VARCHAR(255),
    `date_of_birth` DATE,
    `gender` BOOLEAN,
    `status` BOOLEAN DEFAULT TRUE,
    `account_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`account_code`) REFERENCES `account`(`account_code`)
);

-- =========================
-- SUPPLIER
-- =========================
CREATE TABLE IF NOT EXISTS `supplier` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `supplier_code` VARCHAR(50) UNIQUE NOT NULL,
    `supplier_name` VARCHAR(255),
    `address` VARCHAR(255),
    `description` TEXT,
    `phone_number` VARCHAR(20),
    `email` VARCHAR(255),
    `status` BOOLEAN DEFAULT TRUE,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- `supplier_product` table moved later so `product` exists before FK references.


-- =========================
-- PROMOTION TYPE & PROMOTION
-- =========================
CREATE TABLE IF NOT EXISTS `promotion_type` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `promotion_type_code` VARCHAR(50) UNIQUE NOT NULL,
    `promotion_type_name` VARCHAR(255),
    `description` VARCHAR(255),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `promotion` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `promotion_code` VARCHAR(50) UNIQUE NOT NULL,
    `promotion_name` VARCHAR(255),
    `description` VARCHAR(255),
    `value` DECIMAL(10,2),
    `promotion_type_code` VARCHAR(50),
    `start_date` DATETIME,
    `end_date` DATETIME,
    `status` BOOLEAN DEFAULT TRUE,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`promotion_type_code`) REFERENCES `promotion_type`(`promotion_type_code`)
);

CREATE TABLE IF NOT EXISTS `coupon` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `coupon_code` VARCHAR(50) UNIQUE NOT NULL,
    `coupon_name` VARCHAR(255),
    `description` VARCHAR(255),
    `promotion_type_code` VARCHAR(50),
    `value` DECIMAL(10,2) NOT NULL,
    `start_date` DATETIME NOT NULL,
    `end_date` DATETIME NOT NULL,
    `status` BOOLEAN DEFAULT TRUE,      -- có đang kích hoạt coupon không
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (`promotion_type_code`) REFERENCES `promotion_type`(`promotion_type_code`)
);




-- =========================
-- PRODUCT CATEGORY & PRODUCT
-- =========================
CREATE TABLE IF NOT EXISTS `product_category` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `category_code` VARCHAR(50) UNIQUE NOT NULL,
    `category_name` VARCHAR(255),
    `category_type` VARCHAR(255),
    `description` VARCHAR(255),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `product` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_code` VARCHAR(50) UNIQUE NOT NULL,
    `product_name` VARCHAR(255),
    `description` TEXT,
    `image` VARCHAR(255),
    `author` VARCHAR(255),
    `publisher` VARCHAR(255),
    `status` BOOLEAN DEFAULT TRUE,
    `category_code` VARCHAR(50),
    `promotion_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`category_code`) REFERENCES `product_category`(`category_code`),
    FOREIGN KEY (`promotion_code`) REFERENCES `promotion`(`promotion_code`)
);

-- =========================
-- SUPPLIER_PRODUCT (moved here to ensure `product` exists first)
-- =========================
CREATE TABLE IF NOT EXISTS `supplier_product` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `supplier_product_code` VARCHAR(50) UNIQUE NOT NULL,
    `supplier_code` VARCHAR(50),
    `product_code` VARCHAR(50),
    `import_price` DECIMAL(20,2),
    `status` BOOLEAN DEFAULT TRUE,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`supplier_code`) REFERENCES `supplier`(`supplier_code`),
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`)
);

-- =========================
-- ORDER & ORDER DETAIL
-- =========================
CREATE TABLE IF NOT EXISTS `order` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_code` VARCHAR(50) UNIQUE NOT NULL,
    `order_type` ENUM('Offline', 'Online') NOT NULL DEFAULT 'Offline',
    `payment_method` ENUM('Cash','QR') NOT NULL DEFAULT 'QR',
    `order_status` ENUM(
        'pending',
        'confirmed',
        'processing',
        'shipping',
        'delivered',
        'cancelled',
        'returned'
        ) NOT NULL DEFAULT 'pending',

    `is_paid` BOOLEAN DEFAULT FALSE, 
    `promotion_customer_code` VARCHAR(50),
    `promotion_customer_value` DECIMAL(20,2),
    `coupon_code` VARCHAR(50),
    `coupon_discount_value` DECIMAL(20,2),
    `discount` DECIMAL(20,2),
    `total_amount` DECIMAL(20,2),
    `final_amount` DECIMAL(20,2),
    `customer_code` VARCHAR(50),
    `employee_code` VARCHAR(50),
    `note` TEXT, -- Ghi chú đơn hàng
    `address` VARCHAR(255), -- Địa chỉ giao hàng
    `phone_number` VARCHAR(20), -- Số điện thoại người nhận
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_code`) REFERENCES `customer`(`customer_code`),
    FOREIGN KEY (`promotion_customer_code`) REFERENCES `promotion`(`promotion_code`),
    FOREIGN KEY (`coupon_code`) REFERENCES `coupon`(`coupon_code`),
    FOREIGN KEY (`employee_code`) REFERENCES `employee`(`employee_code`)
);


CREATE TABLE IF NOT EXISTS `order_detail` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `order_detail_code` VARCHAR(50) UNIQUE NOT NULL,
    `quantity` INT,
    `unit_price` DECIMAL(20,2),
    `final_price` DECIMAL(20,2),
    `promotion_code` VARCHAR(50),
    `discount_value` DECIMAL(10,2),
    `total_amount` DECIMAL(20,2),
    `product_code` VARCHAR(50),
    `order_code` VARCHAR(50),
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`),
    FOREIGN KEY (`order_code`) REFERENCES `order`(`order_code`) ON DELETE CASCADE,
    FOREIGN KEY (`promotion_code`) REFERENCES `promotion`(`promotion_code`)
);

-- =========================
-- ORDER PROMOTION (promotions applied to a specific order)
-- =========================
-- (Removed) `promotion_order` table: this project no longer stores per-order promotion rows here.

-- =========================
-- COMMENT
-- =========================
CREATE TABLE IF NOT EXISTS `comment` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `comment_code` VARCHAR(50) UNIQUE NOT NULL,
    `content` VARCHAR(255),
    `rating` INT,
    `status` BOOLEAN DEFAULT TRUE,
    `order_detail_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`order_detail_code`) REFERENCES `order_detail`(`order_detail_code`) ON DELETE CASCADE
);

-- =========================
-- CART & FAVORITE
-- =========================
CREATE TABLE IF NOT EXISTS `cart` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `quantity` INT,
    `total_amount` DECIMAL(20,2),
    `product_code` VARCHAR(50),
    `customer_code` VARCHAR(50),
    `promotion_code` VARCHAR(50),
    `discount_value` DECIMAL(10,2),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`) ON DELETE CASCADE,
    FOREIGN KEY (`customer_code`) REFERENCES `customer`(`customer_code`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `favorite` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `product_code` VARCHAR(50),
    `customer_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`) ON DELETE CASCADE,
    FOREIGN KEY (`customer_code`) REFERENCES `customer`(`customer_code`) ON DELETE CASCADE
);

-- =========================
-- IMPORT INVOICE & DETAILS
-- =========================
CREATE TABLE IF NOT EXISTS `import_invoice` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `import_invoice_code` VARCHAR(50) UNIQUE NOT NULL,
    `discount` DECIMAL(10,2),
    `status` VARCHAR(50) DEFAULT 'PENDING',
    `total_amount` DECIMAL(20,2),
    `description` VARCHAR(255),
    `reason` VARCHAR(255),
    `employee_code` VARCHAR(50),
    `supplier_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`employee_code`) REFERENCES `employee`(`employee_code`),
    FOREIGN KEY (`supplier_code`) REFERENCES `supplier`(`supplier_code`)
);


CREATE TABLE IF NOT EXISTS `import_invoice_detail` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `import_invoice_detail_code` VARCHAR(50) UNIQUE NOT NULL,
    `quantity_imported` INT NOT NULL,         -- Số lượng sản phẩm nhập
    `quantity_sold` INT DEFAULT 0,            -- Số lượng sản phẩm đã bán
    `cancelled_quantity` INT DEFAULT 0,       -- Số lượng sản phẩm đã bị hủy
    `import_price` DECIMAL(20,2),
    `total_amount` DECIMAL(20,2),
    `product_code` VARCHAR(50),
    `import_invoice_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`),
    FOREIGN KEY (`import_invoice_code`) REFERENCES `import_invoice`(`import_invoice_code`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `cancelled_product` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `cancelled_product_code` VARCHAR(50) UNIQUE NOT NULL,
    `quantity` INT NOT NULL,         -- Số lượng sản phẩm nhập
    `import_invoice_code` VARCHAR(50),
    `product_code` VARCHAR(50),
    `reason` TEXT,
    `import_invoice_detail_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`),
    FOREIGN KEY (`import_invoice_code`) REFERENCES `import_invoice`(`import_invoice_code`) ON DELETE CASCADE,
    FOREIGN KEY (`import_invoice_detail_code`) REFERENCES `import_invoice_detail`(`import_invoice_detail_code`) ON DELETE CASCADE
);




CREATE TABLE IF NOT EXISTS `inventory` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `inventory_code` VARCHAR(50) UNIQUE NOT NULL,
    `status` BOOLEAN DEFAULT TRUE,
    `product_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`)
);

CREATE TABLE IF NOT EXISTS `inventory_detail` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `inventory_detail_code` VARCHAR(50) UNIQUE NOT NULL,
    `inventory_code` VARCHAR(50),
    `import_invoice_code` VARCHAR(50),
    `import_invoice_detail_code` VARCHAR(50),
    `quantity` INT DEFAULT 0,

    FOREIGN KEY (`inventory_code`) REFERENCES `inventory`(`inventory_code`),
    FOREIGN KEY (`import_invoice_detail_code`) REFERENCES `import_invoice_detail`(`import_invoice_detail_code`),
    FOREIGN KEY (`import_invoice_code`) REFERENCES `import_invoice`(`import_invoice_code`)
);




CREATE TABLE IF NOT EXISTS `price_history` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `price_history_code` VARCHAR(50) UNIQUE NOT NULL,
    `unit_price` DECIMAL(10,2),
    `status` BOOLEAN DEFAULT FALSE,
    `product_code` VARCHAR(50),
    `import_invoice_code` VARCHAR(50),
    `import_invoice_detail_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`product_code`) REFERENCES `product`(`product_code`),
    FOREIGN KEY (`import_invoice_code`) REFERENCES `import_invoice`(`import_invoice_code`),
    FOREIGN KEY (`import_invoice_detail_code`) REFERENCES `import_invoice_detail`(`import_invoice_detail_code`)
);

-- =========================
-- RETURN / EXCHANGE
-- =========================
CREATE TABLE IF NOT EXISTS `return_exchange` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `return_exchange_code` VARCHAR(50) UNIQUE NOT NULL,
    `reason` VARCHAR(255),
    `status` BOOLEAN DEFAULT TRUE,
    `order_code` VARCHAR(50),
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`order_code`) REFERENCES `order`(`order_code`) ON DELETE CASCADE
);
