-- Problem 2
-- 2.a 3
/* 2.b 
ap: general_ledger_accounts, invoice_archive, invoice_line_items, invoces, terms, vendor_contacts, vendors 
ex: active_invoices, color_sample, customers, date_sample, departments, emplyees, float_sample, null_sample, paid_invoices, projects, string_sample
om: customers, items, order_details, orders*/
-- 2.c 68
-- 2.d 114
-- 2.e 122
-- 2.f yes
-- 2.g 2
-- 2.h customer_id

-- 2.i 
SELECT * FROM om.orders;

-- 2.j 
SELECT title, artist FROM om.items;


-- Problem 5
-- 5.a 11
-- 5.b album, artist, customer, employee, genre, invoice, invoiceline, mediatype, playlist, playlisttrack, track
-- 5.c 347
-- 5.d AlbumID

-- 5.e 
SELECT * FROM album;

-- 5.f 
SELECT FirstName, LastName, Title FROM Employee;
