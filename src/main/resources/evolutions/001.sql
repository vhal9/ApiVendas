CREATE TABLE CLIENTE (
                         CLI_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         CLI_NOME VARCHAR(100),
                         CLI_CPF VARCHAR(11)
);

CREATE TABLE PRODUTO (
                         PROD_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         PROD_DESCRICAO VARCHAR(100),
                         PROD_PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE PEDIDO (
                        PED_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                        CLIENTE_ID INTEGER REFERENCES CLIENTE (CLI_ID),
                        PED_DATA_PEDIDO TIMESTAMP,
                        PED_TOTAL NUMERIC(20,2),
                        PED_STATUS VARCHAR(20)
);

CREATE TABLE ITEM_PEDIDO (
                             ITEM_PED_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                             PEDIDO_ID INTEGER REFERENCES PEDIDO (PED_ID),
                             PRODUTO_ID INTEGER REFERENCES PRODUTO (PROD_ID),
                             ITEM_PED_QUANTIDADE INTEGER
);
