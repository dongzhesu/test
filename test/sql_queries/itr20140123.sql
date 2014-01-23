ALTER TABLE tbooking ADD CONSTRAINT discount_lowest_check CHECK (discount >= 0);
ALTER TABLE tbooking ADD CONSTRAINT discount_highest_check CHECK (discount < 100);