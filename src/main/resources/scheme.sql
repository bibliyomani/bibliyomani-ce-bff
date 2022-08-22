CREATE TABLE book
(
    book_id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    book_name           TEXT,
    book_md5_name       VARCHAR(32), -- max of md5,
    book_path           VARCHAR(50),
    book_last_read_page UNSIGNED INT
);