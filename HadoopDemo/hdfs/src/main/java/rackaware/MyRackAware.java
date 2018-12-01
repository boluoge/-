package rackaware;

import org.apache.hadoop.net.DNSToSwitchMapping;

import java.util.ArrayList;
import java.util.List;

public class MyRackAware implements DNSToSwitchMapping {
    @Override
    public List<String> resolve(List<String> names) {
        List<String> list = new ArrayList<String>();
        for (String str : names) {
            System.out.println(str);
            if (str.startsWith("192")) {
                String ip = str.substring(str.lastIndexOf(".") + 1);
                if (Integer.parseInt(ip) <= 130) {
                    list.add("/rack1/" + ip);
                } else {
                    list.add("/rack2/" + ip);
                }
            } else if (str.startsWith("s")) {
                String ip = str.substring(1);
                if (Integer.parseInt(ip) <= 130) {
                    list.add("/rack1/" + ip);
                } else {
                    list.add("/rack2/" + ip);
                }
            }
        }
        return list;
    }

    @Override
    public void reloadCachedMappings() {

    }

    @Override
    public void reloadCachedMappings(List<String> list) {

    }
}
