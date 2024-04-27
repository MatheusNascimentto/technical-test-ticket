CREATE TABLE boleto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_de_barras VARCHAR(100),
    valor DECIMAL(10, 2),
    data_vencimento DATE,
    status VARCHAR(20),
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);