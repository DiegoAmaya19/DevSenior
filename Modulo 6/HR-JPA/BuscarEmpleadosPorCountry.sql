SELECT Em. * FROM 
    employees Em
    JOIN departments D USING (department_id)
    JOIN locations L USING (location_id)
    JOIN countries C USING (country_id)
    WHERE C.country_id = 'US'