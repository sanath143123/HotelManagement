CREATE DATABASE hotelmanagement
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    
CREATE SCHEMA hotelschma
    AUTHORIZATION postgres;
    
CREATE SEQUENCE hotelschma."booking-id-seq"
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE hotelschma."hotel-id-seq"
    INCREMENT 1
    START 100
    MINVALUE 0
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE hotelschma."room-id-seq"
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE hotelschma."user-id-seq"
    INCREMENT 1
    START 100
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
CREATE TABLE hotelschma.users
(
    id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    bonus integer,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

CREATE TABLE hotelschma.hotels
(
    id bigint NOT NULL,
    hotelname text COLLATE pg_catalog."default",
    place text COLLATE pg_catalog."default",
    CONSTRAINT hotels_pkey PRIMARY KEY (id)
)

CREATE TABLE hotelschma.rooms
(
    id bigint NOT NULL,
    hotelid bigint,
    roomcount integer,
    availablerooms integer,
    price double precision,
    roomtype character(50) COLLATE pg_catalog."default",
    CONSTRAINT rooms_pkey PRIMARY KEY (id),
    CONSTRAINT "hotelid-id" FOREIGN KEY (hotelid)
        REFERENCES hotelschma.hotels (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE hotelschma.roombooking
(
    id bigint NOT NULL,
    userid bigint,
    hotelid bigint,
    roomid bigint,
    bookingdate date,
    no_of_rooms integer,
    totalamount double precision,
    status character(20) COLLATE pg_catalog."default",
    CONSTRAINT "hotel-id" FOREIGN KEY (hotelid)
        REFERENCES hotelschma.hotels (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "roomid-id" FOREIGN KEY (roomid)
        REFERENCES hotelschma.rooms (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "userid-id" FOREIGN KEY (userid)
        REFERENCES hotelschma.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)