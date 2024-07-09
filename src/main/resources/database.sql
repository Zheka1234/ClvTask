CREATE TABLE public.product (
                                id BIGSERIAL PRIMARY KEY,
                                description VARCHAR(50) NOT NULL,
                                price DECIMAL(10, 2) NOT NULL,
                                quantity_in_stock INTEGER NOT NULL,
                                wholesale_product BOOLEAN NOT NULL
);

CREATE TABLE public.discount_card (
                                      id BIGSERIAL PRIMARY KEY,
                                      number INTEGER NOT NULL,
                                      amount SMALLINT NOT NULL CHECK (amount >= 0 AND amount <= 100)
);