kubectl exec -it mysql-sfs-0 -n spring-cloud -- mysql -uroot -ppassword -e "
DROP DATABASE IF EXISTS order_db;
DROP DATABASE IF EXISTS account_db;
DROP DATABASE IF EXISTS storage_db;

CREATE DATABASE IF NOT EXISTS order_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS account_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS storage_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SHOW DATABASES;
"