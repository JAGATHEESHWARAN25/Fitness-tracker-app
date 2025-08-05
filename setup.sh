
#!/bin/bash

# Set variables
PROJECT_FOLDER="/home/coder/project/workspace/question_generation_service/solutions/0c06a440-b4a5-436c-becd-b34f60bfc48c/springapp"
DATABASE_NAME="0c06a440_b4a5_436c_becd_b34f60bfc48c"

# Create database
mysql -u root -pexamly -e "CREATE DATABASE IF NOT EXISTS ${DATABASE_NAME};" 2>/dev/null || echo "Database creation failed, will use default"

# Generate Spring Boot project using Spring CLI
spring init \
  --type=maven-project \
  --language=java \
  --boot-version=3.4.0 \
  --packaging=jar \
  --java-version=17 \
  --groupId=com.examly \
  --artifactId=springapp \
  --name="Fitness Tracker" \
  --description="Fitness Tracker Application with workout management features" \
  --package-name=com.examly.springapp \
  --dependencies=web,data-jpa,validation,mysql \
  --build=maven \
  ${PROJECT_FOLDER}

# Wait for project generation to complete
sleep 2

# Create application.properties with database configuration
cat > "${PROJECT_FOLDER}/src/main/resources/application.properties" << EOL
spring.datasource.url=jdbc:mysql://localhost:3306/${DATABASE_NAME}?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=examly
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
EOL
