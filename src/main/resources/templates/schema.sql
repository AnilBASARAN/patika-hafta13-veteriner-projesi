CREATE DATABASE veterinaria
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C.UTF-8'
    LC_CTYPE = 'C.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE IF NOT EXISTS public.animal
(
    id bigint NOT NULL DEFAULT nextval('animal_id_seq'::regclass),
    breed character varying(255) COLLATE pg_catalog."default",
    colour character varying(255) COLLATE pg_catalog."default",
    date_of_birth date,
    gender character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    species character varying(255) COLLATE pg_catalog."default",
    customer_id bigint,
    CONSTRAINT animal_pkey PRIMARY KEY (id),
    CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.appointment
(
    id bigint NOT NULL DEFAULT nextval('appointment_id_seq'::regclass),
    date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint,
    CONSTRAINT appointment_pkey PRIMARY KEY (id),
    CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id)
        REFERENCES public.animal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.available_date
(
    id bigint NOT NULL DEFAULT nextval('available_date_id_seq'::regclass),
    available_date date,
    doctor_id bigint,
    CONSTRAINT available_date_pkey PRIMARY KEY (id),
    CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.customer
(
    id bigint NOT NULL DEFAULT nextval('customer_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.doctor
(
    id bigint NOT NULL DEFAULT nextval('doctor_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT doctor_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.vaccine
(
    id bigint NOT NULL DEFAULT nextval('vaccine_id_seq'::regclass),
    code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    protection_finish_date date,
    protection_start_date date,
    animal_id bigint,
    CONSTRAINT vaccine_pkey PRIMARY KEY (id),
    CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id)
        REFERENCES public.animal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


-- 3 Customer
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (1, 'Kızılay', 'Ankara', 'ahmet@gmail.com', 'Ahmet', '05555555555');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (2, 'Kızılay', 'Ankara', 'mehmet@gmail.com', 'Mehmet', '05555555554');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (3, 'Kızılay', 'Ankara', 'ali@gmail.com', 'Ali', '05555555553');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (4, 'Kızılay', 'Ankara', 'veli@gmail.com', 'Veli', '05555555552');
INSERT INTO public.customer (id, address, city, email, name, phone) VALUES (5, 'Kızılay', 'Ankara', 'ayşe@gmail.com', 'Ayşe', '05555555551');

-- 3 Doctor 
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (1, 'Kızılay', 'Ankara', 'sinan@gmail.com', 'Sinan', '05555555550');
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (2, 'Kızılay', 'Ankara', 'ahmet@gmail.com', 'Ahmet', '05555555549');
INSERT INTO public.doctor (id, address, city, email, name, phone) VALUES (3, 'Kızılay', 'Ankara', 'mehmet@gmail.com', 'Mehmet', '05555555548');


-- 5 Animal
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (1, 'Köpek', 'Siyah', '2019-01-01', 'Erkek', 'Karabaş', 'Kangal', 1);
INSERT INTO public.animal (id, breed, colour, date_of_birth , gender, name, species, customer_id) VALUES (2, 'Köpek', 'Beyaz', '2019-01-01', 'Dişi', 'Boncuk', 'Kangal', 2);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (3, 'Köpek', 'Beyaz', '2019-01-01', 'Erkek', 'Pamuk', 'Cocker', 3);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (4, 'Kedi', 'Siyah', '2019-01-01', 'Dişi', 'Tekir', 'Tekir', 4);
INSERT INTO public.animal (id, breed, colour, date_of_birth, gender, name, species, customer_id) VALUES (5, 'Kedi', 'Beyaz', '2019-01-01', 'Erkek', 'Pamuk', 'Van', 5);

-- 5 Vaccine 
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (1, 'K001', 'Kuduz', '2021-01-01', '2024-01-01', 1);
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (2, 'K002', 'Kuduz', '2021-01-01', '2024-04-01', 2);
INSERT INTO public.vaccine (id, code, name, protection_finish_date, protection_start_date, animal_id) VALUES (3, 'K003', 'Kuduz', '2021-01-01', '2024-07-01', 3);

-- 5 Appointment 
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (1, '2021-01-01 10:00:00', 1, 1);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (2, '2021-01-01 11:00:00', 2, 2);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (3, '2021-01-01 12:00:00', 3, 3);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (4, '2021-01-01 13:00:00', 4, 1);
INSERT INTO public.appointment (id, date, animal_id, doctor_id) VALUES (5, '2021-01-01 14:00:00', 5, 2);

