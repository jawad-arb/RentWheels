SELECT * from Reservations;
insert into Reservations(car_id,customer_id,reservation_date,start_date,end_date,total_cost,status)VALUES (?,?,?,?,?,?,?);
DELETE FROM Reservations WHERE reservation_id=?;
UPDATE Reservations SET car_id=?,customer_id=?,reservation_date=?,start_date=?,end_date=?,total_cost=?,status=? WHERE reservation_id=?;