ALTER TABLE tproduct ALTER COLUMN product_price SET NOT NULL;
ALTER TABLE tproduct ALTER COLUMN product_price_2 SET NOT NULL;
ALTER TABLE tproduct ALTER COLUMN product_qty SET NOT NULL;
ALTER TABLE tproduct ALTER COLUMN product_qty SET DEFAULT 0;

ALTER TABLE tbooking ALTER COLUMN booking_qty SET NOT NULL;
ALTER TABLE tbooking ALTER COLUMN booking_qty SET DEFAULT 0;
ALTER TABLE tbooking ALTER COLUMN unit_price SET NOT NULL;
ALTER TABLE tbooking ALTER COLUMN unit_price SET DEFAULT 0;
ALTER TABLE tbooking ALTER COLUMN discount SET NOT NULL;
ALTER TABLE tbooking ALTER COLUMN discount SET DEFAULT 0;
ALTER TABLE tbooking ALTER COLUMN booking_price SET NOT NULL;
ALTER TABLE tbooking ALTER COLUMN booking_price SET DEFAULT 0;