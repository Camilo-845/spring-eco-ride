CREATE TABLE IF NOT EXISTS channel(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

create table if not exists notification_events(
  id SERIAL PRIMARY KEY,
  event_type VARCHAR(50) NOT NULL UNIQUE,
  description TEXT
);

CREATE TABLE IF NOT EXISTS template(
  id SERIAL PRIMARY KEY,
  event_type INTEGER NOT NULL REFERENCES notification_events(id) ON DELETE SET NULL,
  channel INTEGER NOT NULL REFERENCES channel(id) ON DELETE CASCADE,
  subject VARCHAR(255) NOT NULL,
  body TEXT NOT NULL,
  CONSTRAINT uq_template_event_channel UNIQUE (event_type, channel)
);

CREATE TABLE IF NOT EXISTS outbox_status(
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS outbox(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  event_type INTEGER NOT NULL REFERENCES notification_events(id) ON DELETE SET NULL,
  payload JSONB NOT NULL,
  status INTEGER NOT NULL REFERENCES outbox_status(id) ON DELETE SET NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  scheduled_at TIMESTAMP,
  sent_at TIMESTAMP,
  template_id INTEGER NOT NULL REFERENCES template(id) ON DELETE RESTRICT,
  retries INTEGER NOT NULL DEFAULT 0
);
