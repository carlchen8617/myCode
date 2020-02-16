#!/usr/bin/env python3

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


d=[{'name':'Puffball','owner':'Diane','species':'hamster','sex':'f','birth':'1999-03-30','death':'0001-01-01'}]
ki=spark.createDataFrame(d)       
col("death").cast(TimestampType())

jj=ki.write.jdbc("jdbc:mysql://localhost/test",\
                 "test.pet",\
                 "append",\
                 {"driver":"com.mysql.jdbc.Driver",\
                  "user":"carl",\
                  "password":"myPassword",\
                  "serverTimezone":"UTC"}\
                 )
print(jj)


