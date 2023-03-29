FROM postgres
ENV POSTGRES_DB="pop_sfi"
COPY data.sql /docker-entrypoint-initdb.d/