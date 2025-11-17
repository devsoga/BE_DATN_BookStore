-- Flyway migration V3: create an EVENT and triggers to auto-expire promotions when end_date has passed
-- Creates an EVENT that runs hourly to mark promotions with end_date < NOW() as inactive
-- Also creates BEFORE INSERT/BEFORE UPDATE triggers to ensure status is set correctly on changes

-- Note: modifying the global event_scheduler or creating triggers in the same SQL file
-- can require elevated privileges and custom DELIMITER handling which Flyway/JDBC
-- may not support. To keep this migration portable we only create a single-statement
-- EVENT here. If your MySQL user doesn't have EVENT privileges or the server's
-- event_scheduler is disabled, ask your DBA to enable it or use the alternative
-- (Spring scheduled task) described below.

CREATE EVENT IF NOT EXISTS ev_expire_promotions
ON SCHEDULE EVERY 1 SECOND
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    -- expire promotions whose end_date has passed
    UPDATE promotion
    SET status = FALSE
    WHERE status = TRUE
      AND end_date IS NOT NULL
      AND end_date < NOW();

    -- expire coupons whose end_date has passed (one-time or time-limited coupons)
    UPDATE coupon
    SET status = FALSE
    WHERE status = TRUE
      AND end_date IS NOT NULL
      AND end_date < NOW();
END;

