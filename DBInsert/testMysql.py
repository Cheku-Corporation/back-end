import sys
import mysql.connector


# Usage: python testMysql.py <user> <password>


def main():
    #validate arguments
    if len(sys.argv) != 3:
        print("Usage: python testMysql.py <user> <password>")
        sys.exit(1)


    #connect to database
    print("Connecting to database...")
    try:
        cnx = mysql.connector.connect(
            port = 33050, 
            user=sys.argv[1], 
            password=sys.argv[2])

    except mysql.connector.Error as err:
        print("Error connecting to database: {}".format(err))
        sys.exit(1)

    print(cnx)
    print("Connected to database")

    #Create cursor
    cursor = cnx.cursor()

    #Execute query
    cursor.execute('SHOW DATABASES')
    db_list = []
    for db in cursor.fetchall():
        db_list.append(db[0])

    #Use database
    if 'demo' in db_list:
        cursor.execute('USE demo')
        print("Using database demo")
    
    else:
        print("Database demo not found")
        sys.exit(1)

    #Table list
    cursor.execute('SHOW TABLES')
    table_list = []
    for table in cursor.fetchall():
        table_list.append(table[0])
        print("\t> " +table[0])

    #Close cursor
    cursor.close()


if __name__ == '__main__':
    main()
