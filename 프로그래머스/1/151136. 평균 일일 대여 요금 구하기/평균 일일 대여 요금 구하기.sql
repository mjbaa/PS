SELECT round(avg(daily_fee)) as average_fee
FROM car_rental_company_car
WHERE car_type = 'SUV'