-- Creates the 3 per-service databases on the shared MySQL container's first boot.
-- (Runs automatically because it's mounted into /docker-entrypoint-initdb.d)

CREATE DATABASE IF NOT EXISTS authservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS userservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS expenseservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

GRANT ALL PRIVILEGES ON authservice.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON userservice.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON expenseservice.* TO 'root'@'%';
FLUSH PRIVILEGES;