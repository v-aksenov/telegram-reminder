clean:
	./gradlew clean

build: clean
	./gradlew bootJar

up: build
	docker-compose up