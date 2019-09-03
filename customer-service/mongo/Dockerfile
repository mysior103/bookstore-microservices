FROM mongo:4.0.5

# admin db user set up
ENV MONGO_INITDB_ROOT_USERNAME=mongo
ENV MONGO_INITDB_ROOT_PASSWORD=password

COPY ./init.sh /docker-entrypoint-initdb.d/

EXPOSE 27017