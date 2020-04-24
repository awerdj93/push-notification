CREATE TABLE sellers
   (
      id   BIGSERIAL   NOT NULL PRIMARY KEY,

      seller_id    BIGSERIAL   NOT NULL,

      product_id    BIGSERIAL NOT NULL,
      seller_addr  VARCHAR(255) NOT NULL

  );