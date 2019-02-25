export MYSQL_DATABASE=newsDb
#For Local Mysql
export MYSQL_USER=root
export MYSQL_PASSWORD=root
export MYSQL_ROOT_PASSWORD=root123
#For Docker Mysql
#export MYSQL_USER=newsroot
#export MYSQL_PASSWORD=newsroot
#export MYSQL_ROOT_PASSWORD=newsroot123
export MYSQL_CI_URL=jdbc:mysql://localhost:3306/newsDb
echo $MYSQL_USER
