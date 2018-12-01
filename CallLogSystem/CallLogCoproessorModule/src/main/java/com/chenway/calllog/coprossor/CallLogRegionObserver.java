package com.chenway.calllog.coprossor;

import com.chenway.calllog.utils.CallLogUtil;
import javafx.scene.control.Tab;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
创建协处理器
 */
public class CallLogRegionObserver extends BaseRegionObserver {
    private static final String REF_ROW_ID = "refrowid";
    private static final String CALL_LOG_TABLE_NAME = "ns1:calllogs";

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);
        String tableName0 = TableName.valueOf(CALL_LOG_TABLE_NAME).getNameAsString();
        String tableName1 = e.getEnvironment().getRegion().getRegionInfo().getTable().getNameAsString();
        if (!tableName0.equals(tableName1)) {
            return;
        }

        String rowKey = Bytes.toString(put.getRow());
        String arr[] = rowKey.split(",");
        if (arr[3].equals("1")) {
            return;
        }
        //hashcode,caller,time,flag,callee,duration
        String caller = arr[1];
        String callTime = arr[2];
        String callee = arr[4];
        String callDuration = arr[5];
        String hashcode = CallLogUtil.getHashcode(callee, callTime, 100);
        String calleeRowKey = hashcode + "," + callee + "," + callTime + ",1," + caller + "," + callDuration;

        Put newPut = new Put(Bytes.toBytes(calleeRowKey));
        newPut.addColumn(Bytes.toBytes("f2"), Bytes.toBytes(REF_ROW_ID), Bytes.toBytes(rowKey));
        TableName tableName = TableName.valueOf(CALL_LOG_TABLE_NAME);
        Table table = e.getEnvironment().getTable(tableName);
        table.put(newPut);

    }

    @Override
    public void postGetOp(ObserverContext<RegionCoprocessorEnvironment> e, Get get, List<Cell> results) throws IOException {
        String tableName = e.getEnvironment().getRegion().getRegionInfo().getTable().getNameAsString();
        if (!tableName.equals(CALL_LOG_TABLE_NAME)) {
            return;
        } else {
            String rowKey = Bytes.toString(get.getRow());
            String[] arr = rowKey.split(",");
            if (arr[3].equals("0")) {
                return;
            } else {
                String refrowid = Bytes.toString(CellUtil.cloneRow(results.get(0)));
                Table table = e.getEnvironment().getTable(TableName.valueOf(CALL_LOG_TABLE_NAME));
                Get get1 = new Get(Bytes.toBytes(refrowid));
                Result result = table.get(get1);
                List<Cell> cells = result.listCells();
                results.clear();
                results.addAll(cells);
            }
        }
    }

    /**
     *
     */
    public boolean postScannerNext(ObserverContext<RegionCoprocessorEnvironment> e, InternalScanner s, List<Result> results, int limit, boolean hasMore) throws IOException {
        boolean b = super.postScannerNext(e, s, results, limit, hasMore);

        //新集合
        List<Result> newList = new ArrayList<Result>();

        //获得表名
        String tableName = e.getEnvironment().getRegion().getRegionInfo().getTable().getNameAsString();

        //判断表名是否是ns1:calllogs
        if (tableName.equals(CALL_LOG_TABLE_NAME)) {
            Table tt = e.getEnvironment().getTable(TableName.valueOf(CALL_LOG_TABLE_NAME));
            for (Result r : results) {
                //rowkey
                String rowkey = Bytes.toString(r.getRow());
                String flag = rowkey.split(",")[3];
                //主叫
                if (flag.equals("0")) {
                    newList.add(r);
                }
                //被叫
                else {
                    //取出主叫号码
                    byte[] refrowkey = r.getValue(Bytes.toBytes("f2"), Bytes.toBytes(REF_ROW_ID));
                    Get newGet = new Get(refrowkey);
                    newList.add(tt.get(newGet));
                }
            }
            results.clear();
            results.addAll(newList);
        }
        return b;
    }
}
