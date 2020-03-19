# Order of operations
These commands are for linux/Mac, changes will need to made if you are running this in Microsoft Windows.

## Prerequisites
Docker is installed
psql client is installed

## Actions

### Running PostgreSQL
1. Pull Docker Image
`docker pull postgres`

2. Build data directory
`mkdir -p ~/srv/onke`

3. Run docker image
`docker run --rm --name umuzi -e POSTGRES_PASSWORD=dbuser123 -d -v $HOME/srv/onke:/var/lib/postgresql/data -p 5432:5432 onke`

### Stopping PostgreSQL
`docker stop `

### Logging into Database
* `psql -h localhost -U postgres -d umuzi`

### Creating starter data
1. `psql -h localhost -U onke -f database.sql`
2. `psql -h localhost -U onke -d umuzi -f customer.sql`
3. `psql -h localhost -U onke -d umuzi -f employees.sql`
4. `psql -h localhost -U onke -d umuzi -f orders.sql`
5. `psql -h localhost -U onke -d umuzi -f payments.sql`
6. `psql -h localhost -U onke -d umuzi -f products`
