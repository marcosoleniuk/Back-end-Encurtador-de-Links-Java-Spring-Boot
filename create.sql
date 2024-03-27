
    create table table_url (
        id bigserial not null,
        created_at varchar(255),
        hits bigint,
        long_url varchar(255),
        short_url varchar(255),
        primary key (id)
    );
