package com.chenway.ssm.web.controller;

import com.chenway.ssm.domain.CallLog;
import com.chenway.ssm.domain.CallLogRange;
import com.chenway.ssm.service.CallLogService;
import com.chenway.ssm.service.hive.HiveCallLogService;
import com.chenway.ssm.util.CallLogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
@Controller
public class CallLogController {
    @Resource(name = "hiveCallLogService")
    private HiveCallLogService hcs;

    @Resource(name = "callLogService")
    private CallLogService cs;

    @RequestMapping("/callLog/findAll")
    public String findAll(Model m) {
        List<CallLog> list = cs.findAll();
        m.addAttribute("callLogs", list);
        return "callLog/callLogList";
    }

    /*
     进入查询通话记录的页面，form
     */
    @RequestMapping("/callLog/toFindCallLogPage")
    public String toFindCallLogPage() {
        return "callLog/findCallLog";
    }

    @RequestMapping(value = "/callLog/findCallLog", method = RequestMethod.POST)
    public String findCallLog(Model m, @RequestParam("caller") String caller, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        List<CallLogRange> list = CallLogUtil.getCallLogRanges(startTime, endTime);
        List<CallLog> logs = cs.findCallLogs(caller, list);
        m.addAttribute("callLogs", logs);
        return "callLog/callLogList";
    }

    @RequestMapping(value = "/callLog/findLatestCallLog", method = RequestMethod.POST)
    public String findLatestCallLog(Model model, @RequestParam("caller") String caller) {
        CallLog log = hcs.findLastestCallLog(caller);
        if (log != null) {
            model.addAttribute("log", log);
        }

        return "callLog/latestCallLog";
    }

    @RequestMapping(value = "/callLog/toFindLatestCallLog")
    public String toFindLatestCallLog() {

        return "callLog/findLatestCallLog";
    }
}
