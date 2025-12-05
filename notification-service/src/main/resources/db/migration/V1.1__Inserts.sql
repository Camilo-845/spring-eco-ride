INSERT INTO channel (name) VALUES 
('email'),
('sms'),
('push');

INSERT INTO outbox_status (name) VALUES 
('pending'),   -- Esperando a ser procesada por el worker
('processing'), -- Si usas bloqueo, indica que un worker la está manejando
('sent'),      -- Enviada con éxito
('failed'),    -- Falló el envío, puede reintentarse
('dead_letter'); -- Falló permanentemente después de N reintentos

INSERT INTO notification_events (event_type, description) VALUES 
-- Eventos Disparados por TripService
('RESERVATION_CONFIRMED', 'La reserva del asiento ha sido confirmada con éxito.'),
('RESERVATION_CANCELLED', 'La reserva fue cancelada (por el pasajero o por el fallo del pago).'),
('TRIP_SCHEDULED', 'El conductor ha publicado un nuevo viaje recurrente o único.'),
('TRIP_COMPLETED', 'El viaje ha finalizado, habilita rating.'),

-- Eventos Disparados por PaymentService (como parte de la Saga)
('PAYMENT_AUTHORIZED', 'Pago autorizado, la reserva está pendiente de confirmación.'),
('PAYMENT_FAILED', 'La autorización del pago falló, requiere acción o compensación.'),

-- Eventos de Perfil/Recordatorio
('DRIVER_RATED', 'El conductor ha recibido una nueva calificación.'),
('REMINDER_TRIP_SOON', 'Recordatorio de viaje programado que comienza pronto.');
