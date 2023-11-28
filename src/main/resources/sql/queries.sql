-- insert into Reservations(car_id,customer_id,reservation_date,start_date,end_date,total_cost,status)VALUES (?,?,?,?,?,?,?);
-- DELETE FROM Reservations WHERE reservation_id=?;
-- UPDATE Reservations SET car_id=?,customer_id=?,reservation_date=?,start_date=?,end_date=?,total_cost=?,status=? WHERE reservation_id=?;
--
-- SELECT * from Users;
-- SELECT * from Reservations;
-- select * from Cars;
-- select * from Customers;
-- select * from Blacklist;
--
-- insert into Blacklist(customer_id, car_id, start_date, end_date, reason) VALUES (?,?,?,?,?);
-- DELETE FROM Blacklist where entry_id=?;
-- UPDATE Blacklist set customer_id=?,car_id=?,start_date=?,end_date=?,reason=? WHERE entry_id=?;
-- SELECT COUNT(*) FROM Blacklist ;
-- SELECT (customer_id, car_id, start_date, end_date, reason) FROM Blacklist WHERE entry_id=?;
-- SELECT count(*) from Blacklist where customer_id=?;

# create Table CarInsurance(
#     insuranceId int primary key auto_increment,
#     carId int not null,
#     startDate DATE ,
#     endDate DATE,
#     FOREIGN KEY(carId) REFERENCES Cars(car_id)
# );
#
# alter Table Cars
# add Column insuranceId int not null,
# ADD FOREIGN KEY (insuranceId) REFERENCES CarInsurance(insuranceId);
#

select * from Cars;