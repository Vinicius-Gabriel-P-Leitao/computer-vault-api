-- Cria a tabela se ela não existir
-- Essa tabela é criada pel o hibernate
CREATE TABLE IF NOT EXISTS public.tb_computer
(
    col_amount_of_ram_installed integer,
    col_frequency_ram integer,
    col_hd integer,
    col_memory_ram integer,
    col_ssd integer,
    col_model_ram VARCHAR(6) COLLATE pg_catalog."default" NOT NULL,
    col_number_patrimony VARCHAR(6) COLLATE pg_catalog."default",
    col_location_computer VARCHAR(7) COLLATE pg_catalog."default" NOT NULL,
    col_business_unit VARCHAR(10) COLLATE pg_catalog."default" NOT NULL,
    col_so VARCHAR(10) COLLATE pg_catalog."default" NOT NULL,
    col_type_ram VARCHAR(10) COLLATE pg_catalog."default" NOT NULL,
    col_computer_brand VARCHAR(15) COLLATE pg_catalog."default",
    col_cpu VARCHAR(15) COLLATE pg_catalog."default",
    col_ip VARCHAR(15) COLLATE pg_catalog."default" NOT NULL,
    col_name_computer VARCHAR(15) COLLATE pg_catalog."default" NOT NULL,
    col_conditions VARCHAR(16) COLLATE pg_catalog."default" NOT NULL,
    col_type_computer VARCHAR(16) COLLATE pg_catalog."default" NOT NULL,
    computer_id uuid NOT NULL,
    col_user VARCHAR(30) COLLATE pg_catalog."default" NOT NULL,
    col_department VARCHAR(100) COLLATE pg_catalog."default" NOT NULL,

    -- Restrições
    CONSTRAINT tb_computer_pkey PRIMARY KEY (computer_id),
    CONSTRAINT tb_computer_col_ip_key UNIQUE (col_ip),
    CONSTRAINT tb_computer_col_name_computer_key UNIQUE (col_name_computer),
    CONSTRAINT tb_computer_col_model_ram_check CHECK (col_model_ram::text = ANY (ARRAY['DIMM'::character varying, 'SODIMM'::character varying, 'SOLDADA'::character varying]::text[])),
    CONSTRAINT tb_computer_col_location_computer_check CHECK (col_location_computer::text = ANY (ARRAY['MATRIZ'::character varying, 'POSTO'::character varying, 'ESTOQUE'::character varying]::text[])),
    CONSTRAINT tb_computer_col_so_check CHECK (col_so::text = ANY (ARRAY['W11'::character varying, 'W10'::character varying, 'W7'::character varying]::text[])),
    CONSTRAINT tb_computer_col_type_ram_check CHECK (col_type_ram::text = ANY (ARRAY['DDR3'::character varying, 'DDR4'::character varying]::text[])),
    CONSTRAINT tb_computer_col_type_computer_check CHECK (col_type_computer::text = ANY (ARRAY['DESKTOP'::character varying, 'ALL_IN_ONE'::character varying, 'MICRO'::character varying, 'MONTADo'::character varying]::text[]))
)

-- Infere que a tipagem vai ser do postgresql
TABLESPACE pg_default;

-- Assina a tabela
ALTER TABLE IF EXISTS public.tb_computer
    OWNER to "Vinicius";