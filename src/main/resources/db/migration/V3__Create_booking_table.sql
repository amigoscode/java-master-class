CREATE TABLE booking (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    car_id UUID NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (car_id) REFERENCES car(id)
);

CREATE INDEX idx_booking_user_id ON booking(user_id);
CREATE INDEX idx_booking_car_id ON booking(car_id);