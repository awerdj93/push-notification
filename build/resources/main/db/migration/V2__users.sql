 CREATE TABLE users
   (
      id    BIGSERIAL NOT NULL,
      user_id   BIGSERIAL   NOT NULL,
      user_name VARCHAR(255) NOT NULL,

      user_addr VARCHAR(255) NOT NULL,
      user_email VARCHAR(255) NOT NULL
  );
