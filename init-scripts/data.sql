-- Criação da tabela de elegibilidade
CREATE TABLE IF NOT EXISTS eligibility (
    cnpj VARCHAR(14) PRIMARY KEY,
    is_eligible BOOLEAN NOT NULL
);

-- Inserção de CNPJs de exemplo
INSERT INTO eligibility (cnpj, is_eligible) VALUES
    ('64706560000149', true),
    ('78129519000161', false),
    ('88121070000149', true),
    ('68576467000118', false),
    ('00627547000111', true),
    ('91270622000195', false);
