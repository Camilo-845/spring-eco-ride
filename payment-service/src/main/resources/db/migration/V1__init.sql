CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TYPE payment_intent_status AS ENUM (
    'REQUIRES_ACTION', 
    'AUTHORIZED', 
    'CAPTURED', 
    'FAILED', 
    'PENDING'
);

CREATE TYPE charge_status AS ENUM (
    'SUCCEEDED', 
    'FAILED'
);

CREATE TABLE IF NOT EXISTS payments_intents (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  reservation_id UUID NOT NULL, -- Recomendación: usar snake_case para columnas
  amount DECIMAL(10, 2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  status payment_intent_status NOT NULL DEFAULT 'PENDING',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS charges(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  payment_intent_id UUID NOT NULL REFERENCES payments_intents(id) ON DELETE CASCADE,
  provider VARCHAR(20) NOT NULL,
  provider_ref VARCHAR(50) NOT NULL,
  charge_status charge_status NOT NULL,
  captured_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS refunds(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  charge_id UUID NOT NULL REFERENCES Charges(id) ON DELETE CASCADE,
  amount DECIMAL(10, 2) NOT NULL,
  reason VARCHAR(255),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
