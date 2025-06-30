-- changeset alex2808:add_column_create_at_to_cart
ALTER TABLE cart ADD COLUMN IF NOT EXISTS CreatedAt datetime NULL;