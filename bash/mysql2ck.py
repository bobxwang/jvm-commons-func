# coding: utf-8
#!/usr/bin/python

import logging
from logging import handlers
from datetime import datetime
from clickhouse_driver import Client
from functools import reduce

FILENAME='mysql2ck.log'
LOG_FORMAT="%(asctime)s - %(filename)s[line:%(lineno)d] - %(levelname)s:  %(message)s"
DATE_FORMAT="%Y-%m-%d %H:%M:%S %p"
CKHOST='localhost'
DATABASE='tutorial'
USER='default'
PASSWORD=''
SETTINGS={'max_threads': 2, 'priority': 10}
MYSQLHOST='10.190.37.154:3306'

logger=logging.getLogger('mysql2ck')
logger.setLevel(level=logging.INFO)
formatter = logging.Formatter(LOG_FORMAT)
time_rotating_file_handler = handlers.TimedRotatingFileHandler(filename=FILENAME, when='D')
time_rotating_file_handler.setLevel(logging.INFO)
time_rotating_file_handler.setFormatter(formatter)
logger.addHandler(time_rotating_file_handler)
logging.basicConfig(filename=FILENAME, level=logging.INFO, format=LOG_FORMAT, datefmt=DATE_FORMAT)

start=datetime.now()
logging.info("begin to sync on {}".format(start))
client=Client(host=CKHOST, database=DATABASE, user=USER, password=PASSWORD, settings=SETTINGS)
tables=[]
tables_list=client.execute('show tables')
if tables_list:
  tables=reduce(lambda x,y:x+y,tables_list)
kv_tables={'hrmdepartment': 'id',
  'hrmpubholiday': 'id',
  'hrmresource': 'ID',
  'hrmsubcompany': 'id',
  'workflow_base': 'id',
  'workflow_currentoperator': 'id',
  'workflow_nodebase': 'id',
  'workflow_nodeovertime': 'ID',
  'workflow_requestbase': 'REQUESTID',
  'workflow_requestlog': 'LOGID',
  'geely_user_report_list': 'user_id'}
for key,value in kv_tables.items():
  logging.info("begin to sync {}".format(key))
  try:
    if key in tables:
      rowCount=client.execute('select count(1) from {}'.format(key))
      logging.info("before sync table, it has [{}] rows".format(rowCount[0][0]))
      client.execute('drop table {}'.format(key))
    else:
      logging.info("it did not exist the table {}".format(key))
    client.execute("CREATE TABLE {} ENGINE = MergeTree() ORDER BY ({}) AS SELECT * FROM mysql('{}', 'ecology', '{}', 'ecology', 'ecology');".format(key,value,MYSQLHOST,key))
    rowCount=client.execute('select count(1) from {}'.format(key))
    logging.info("after sync table, it has [{}] rows".format(rowCount[0][0]))
  except Exception,err:
    logging.error(err)
  finally:
    logging.info("end to sync {}".format(key))

end=datetime.now()
durn=(end - start).seconds
logging.info("current invoke sync used {} seconds, end on {}\r\n".format(durn, end))
