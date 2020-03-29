#!/usr/bin/env python3

# run like spark-submit --driver-class-path  /usr/share/java/mysql-connector-java.jar readMysqlSpark.py
import pyspark 
import os
from pyspark.sql.types import TimestampType
from pyspark.sql.functions import col
 

os.environ['PYSPARK_PYTHON'] = '/usr/bin/python3'


spark = pyspark.sql.SparkSession.builder \
       .master("local") \
       .appName("Word Count") \
       .config("spark.some.config.option", "some-value") \
       .getOrCreate()

query="(select * from test.pet) t1_alias"

jj=spark.read.format('jdbc')\
            .option("url", "jdbc:mysql://localhost/test")\
            .option("driver", "com.mysql.jdbc.Driver")\
            .option("dbtable",  query)\
            .option("user", "carl")\
            .option("password", "Fangpi!234")\
            .option("serverTimezone","UTC")\
            .load()

jj.show()
print(jj)


