package com.chenway.ssm.service.impl;

import com.chenway.ssm.domain.CallLog;
import com.chenway.ssm.domain.CallLogRange;
import com.chenway.ssm.service.CallLogService;
import com.chenway.ssm.util.CallLogUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 呼叫日志
 */
@Service("callLogService")
public class CallLogServiceImpl implements CallLogService {

    private Table table;

    public CallLogServiceImpl() {
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection conn = ConnectionFactory.createConnection(conf);
            TableName name = TableName.valueOf("ns1:calllogs");
            table = conn.getTable(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有log
     */
    public List<CallLog> findAll() {
        List<CallLog> list = new ArrayList<CallLog>();
        try {
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            Iterator<Result> it = rs.iterator();
            byte[] f = Bytes.toBytes("f1");

            byte[] caller = Bytes.toBytes("caller");
            byte[] callee = Bytes.toBytes("callee");
            byte[] callTime = Bytes.toBytes("callTime");
            byte[] callDuration = Bytes.toBytes("callDuration");
            CallLog log = null;
            while (it.hasNext()) {
                log = new CallLog();
                Result r = it.next();
                log.setCaller(Bytes.toString(r.getValue(f, caller)));
                log.setCallee(Bytes.toString(r.getValue(f, callee)));
                log.setCallTime(Bytes.toString(r.getValue(f, callTime)));
                log.setCallDuration(Bytes.toString(r.getValue(f, callDuration)));
                list.add(log);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CallLog> findCallLogs(String call, List<CallLogRange> ranges) {
        List<CallLog> logs = new ArrayList<CallLog>();
        try {
            for (CallLogRange range : ranges) {
                Scan scan = new Scan();
                //设置扫描起始行和结束行
                scan.setStartRow(Bytes.toBytes(CallLogUtil.getStartRowkey(call, range.getStartPoint(), 100)));
                scan.setStopRow(Bytes.toBytes(CallLogUtil.getStopRowkey(call, range.getStartPoint(), range.getEndPoint(), 100)));

                ResultScanner rs = table.getScanner(scan);
                Iterator<Result> it = rs.iterator();
                byte[] f = Bytes.toBytes("f1");
                byte[] caller = Bytes.toBytes("caller");
                byte[] callee = Bytes.toBytes("callee");
                byte[] callTime = Bytes.toBytes("callTime");
                byte[] callDuration = Bytes.toBytes("callDuration");
                CallLog log = null;
                while (it.hasNext()) {
                    log = new CallLog();
                    Result r = it.next();
                    String rowkey = Bytes.toString(r.getRow());
                    String flag = rowkey.split(",")[3];
                    log.setFlag(flag.equals("0") ? true : false);
                    log.setCaller(Bytes.toString(r.getValue(f, caller)));
                    log.setCallee(Bytes.toString(r.getValue(f, callee)));
                    log.setCallTime(Bytes.toString(r.getValue(f, callTime)));
                    log.setCallDuration(Bytes.toString(r.getValue(f, callDuration)));
                    logs.add(log);
                }
            }
            return logs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
