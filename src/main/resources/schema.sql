-- public.account_types definition

-- Drop table

DROP TABLE public.account_types;

CREATE TABLE public.account_types (
	id bigserial NOT NULL,
	type varchar(255) NULL,
	CONSTRAINT account_types_pkey PRIMARY KEY (id),
	CONSTRAINT constraint_acc_type UNIQUE (type)
);

-- public.account_holder_info definition

-- Drop table

DROP TABLE public.account_holder_info;

CREATE TABLE public.account_holder_info (
	account_id bigserial NOT NULL,
	address varchar(255) NULL,
	age int4 NULL,
	contact_no varchar(255) NULL,
	created_on timestamp NULL,
	date_of_birth timestamp NULL,
	gender varchar(255) NULL,
	hobby varchar(255) NULL,
	"name" varchar(255) NULL,
	id int8 NULL,
	CONSTRAINT account_holder_info_pkey PRIMARY KEY (account_id),
	 CONSTRAINT fk_id
          FOREIGN KEY(id)
    	   REFERENCES public.account_types(id)
);


-- public.account_holder_info foreign keys

--ALTER TABLE public.account_holder_info ADD CONSTRAINT fkahjhywvj4wmssex3kmmesw1tb FOREIGN KEY (id) REFERENCES public.account_types(id);


