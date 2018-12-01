package leftjoin.reducejoid;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComboKey implements WritableComparable<ComboKey> {
    //0-customer  1-order
    private int type;
    private int cid;
    private int oid;
    private String cunstomerInfo = "";
    private String orderInfo = "";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getCunstomerInfo() {
        return cunstomerInfo;
    }

    public void setCunstomerInfo(String cunstomerInfo) {
        this.cunstomerInfo = cunstomerInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(type);
        dataOutput.writeInt(cid);
        dataOutput.writeInt(oid);
        dataOutput.writeUTF(cunstomerInfo);
        dataOutput.writeUTF(orderInfo);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        type = dataInput.readInt();
        cid = dataInput.readInt();
        oid = dataInput.readInt();
        cunstomerInfo = dataInput.readUTF();
        orderInfo = dataInput.readUTF();
    }

    @Override
    public int compareTo(ComboKey o) {
        int type0 = o.getType();
        int cid0 = o.getCid();
        int oid0 = o.getOid();
        String customerInfo0 = o.getCunstomerInfo();
        String orderInfo0 = o.getOrderInfo();
        //是否同一个customer的数据
        if (cid == cid0) {
            //同一个客户的两个订单
            if (type0 == type) {
                return oid - oid0;
            } else {//一个cunstomer+他的order
                if (type == 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }//cid不一样
        else {
            return cid - cid0;
        }

    }
}
