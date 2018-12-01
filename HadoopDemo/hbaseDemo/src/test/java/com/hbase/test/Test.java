package com.hbase.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;

public class Test {
    @org.junit.Test
    public void put() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        byte[] rowid = Bytes.toBytes("row2");
        Put put = new Put(rowid);
        byte[] f1 = Bytes.toBytes("f1");
        byte[] id = Bytes.toBytes("id");
        byte[] value = Bytes.toBytes("102");
        put.addColumn(f1, id, value);
        table.put(put);
    }

    @org.junit.Test
    public void get() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        byte[] value = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("age"));
        System.out.println(Bytes.toString(value));

    }


    @org.junit.Test
    public void bigInsert() throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("0000");

        long start = System.currentTimeMillis();
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        HTable table = (HTable) connection.getTable(tableName);
        table.setAutoFlush(false);//不要自动清理缓冲区，默认写一次清理一次
        for (int i = 1; i < 10000; i++) {
            Put put = new Put(Bytes.toBytes("row" + decimalFormat.format(i)));
            put.setWriteToWAL(false);//关闭写前日志
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom" + i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes(i % 100));
            table.put(put);
            if (i % 2000 == 0) {
                table.flushCommits();
            }
            table.flushCommits();

        }
        System.out.println(System.currentTimeMillis() - start);

    }

    @org.junit.Test
    public void formatNum() {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("0000");

    }

    @org.junit.Test
    public void createNamespace() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        // NamespaceDescriptor namespaceDescriptor =NamespaceDescriptor.create("ns2").build();
        // admin.createNamespace(namespaceDescriptor);
        NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor n : namespaceDescriptors) {
            System.out.println(n.getName());
        }
    }

    @org.junit.Test
    public void createTable() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf("ns1:t2");
        HTableDescriptor hTableDescriptor1 = new HTableDescriptor(tableName);
        HColumnDescriptor columnDescriptor = new HColumnDescriptor("f1");
        hTableDescriptor1.addFamily(columnDescriptor);
        admin.createTable(hTableDescriptor1);
    }

    @org.junit.Test
    public void dropTable() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf("ns1:t2");
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    @org.junit.Test
    public void deleteDate() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        Delete delete = new Delete(Bytes.toBytes("row9999"));
        table.delete(delete);
    }

    @org.junit.Test
    public void scan() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);

        //  ResultScanner f1 = table.getScanner(Bytes.toBytes("f1"));
        Scan scan = new Scan(Bytes.toBytes("row5555"), Bytes.toBytes("row9999"));
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            System.out.println(Bytes.toString(next.getRow()) + ":" + Bytes.toString(next.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"))));
        }
    }

    @org.junit.Test
    public void scan2() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        Scan scan = new Scan(Bytes.toBytes("row5555"), Bytes.toBytes("row8888"));
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            NavigableMap<byte[], byte[]> f1 = next.getFamilyMap(Bytes.toBytes("f1"));
            for (NavigableMap.Entry<byte[], byte[]> entry : f1.entrySet()) {
                System.out.println(Bytes.toString(entry.getKey()) + ":" + Bytes.toString(entry.getValue()));
            }
        }
    }

    @org.junit.Test
    public void scan3() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t2");
        Table table = connection.getTable(tableName);
        Scan scan = new Scan(Bytes.toBytes("f1"));
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            List<Cell> cells = next.listCells();
            for (Cell cell : cells) {
                System.out.println(String.valueOf(cell.getTimestamp()));
            }
        }

    }

    @org.junit.Test
    public void getScanCache() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        //  ResultScanner f1 = table.getScanner(Bytes.toBytes("f1"));
        Scan scan = new Scan();
        scan.setCaching(1000);
        ResultScanner scanner = table.getScanner(scan);
        long start = System.currentTimeMillis();
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            System.out.println(next.getColumnLatestCell(Bytes.toBytes("f1"), Bytes.toBytes("name")));
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    @org.junit.Test
    public void filter() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:t1");
        Table table = connection.getTable(tableName);
        //  ResultScanner f1 = table.getScanner(Bytes.toBytes("f1"));
        Scan scan = new Scan();
        RowFilter filter = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("row1000")));
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            System.out.println(Bytes.toString(next.getRow()));
        }
    }

    @org.junit.Test
    public void putCalllog() throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        TableName tableName = TableName.valueOf("ns1:calllogs");
        Table table = connection.getTable(tableName);

        String callerid = "13845456767";
        String calleeid = "13989898987";
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        String callTime = sdf.format(new Date());
        int duration = 100;
        DecimalFormat dfd = new DecimalFormat();
        dfd.applyPattern("00000");
        String durStr = dfd.format(duration);
        int hash = (callerid + callTime.substring(0, 6)).hashCode() % 100;

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("00");
        String regNo = df.format(hash);
        //xx,callerid,time,direction,calleid,duration
        String rowkey = regNo + "," + callerid + "," + callTime + "," + "0" + "," + calleeid + "," + durStr;
        byte[] rowid = Bytes.toBytes(rowkey);
        Put put = new Put(rowid);
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("callerPos"), Bytes.toBytes("河北"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("calleePos"), Bytes.toBytes("河南"));

        table.put(put);


    }
}
