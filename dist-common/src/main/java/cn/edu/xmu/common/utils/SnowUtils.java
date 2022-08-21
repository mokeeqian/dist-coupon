package cn.edu.xmu.common.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 雪花算法生成ID
 *
 * @Author: xchen.manbu
 * @Date: 2020/2/20 13:42
 */
public class SnowUtils {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数：41bit时间戳+5bit数据中心+5bit机器标识+12bit序列号
     */
    private final static long DATA_CENTER_BIT = 5;
    private final static long MACHINE_BIT = 5;
    private final static long SEQUENCE_BIT = 12;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 步长512
     */
    private static final long STEP_SIZE = 2 << 8;

    /**
     * 基础序列号
     * 每次发生一次时钟回拨，basicSequence += STEP_SIZE
     */
    private long basicSequence = 0L;

    /**
     * 数据中心
     */
    private long dataCenterId;

    /**
     * 机器标识
     */
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    private static SnowUtils idWorker;

    static {
        // 数据中心编码
        long dataCenterId = getDataCenterId(MAX_DATA_CENTER_NUM);
        // 机器编码
        long machineId = getMachineId(dataCenterId, MAX_MACHINE_NUM);
        idWorker = new SnowUtils(dataCenterId, machineId);
    }


    public SnowUtils(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 静态工具类
     *
     * @return
     */
    public static String generateId() {
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewStamp();
        if (currStmp < lastStmp) {
            return handleMovedBackwards(currStmp);
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为basicSequence
            sequence = basicSequence;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        // 循环等待，直到当前时间大于上次生成ID的时间
        while (mill <= lastStmp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }


    /**
     * 时钟回拨处理
     * <p>
     * 假设我们的业务系统在单机上的QPS为3w/s
     * 那么其实平均每毫秒只需要产生30个id即可, 远没有达到设计的4096
     * 也就是说通常情况下序列号的使用都是处在一个低水位, 当发生时钟回拨的时候, 这些尚未被使用的序号就可以派上用场了
     * <p>
     * 对给定的基础序列号稍加修改, 后面每发生一次时钟回拨就将基础序列号加上指定的步长,
     * 例如开始时是从0递增, 发生一次时钟回拨后从512开始递增, 再发生一次时钟回拨则从1024递增
     * 这样能够满足6次的时钟回拨到同一时间点(发生这种操作就有点扯了)
     *
     * @param currStmp
     * @return
     */
    private long handleMovedBackwards(long currStmp) {
        basicSequence += STEP_SIZE;
        if (basicSequence == MAX_SEQUENCE + 1) {
            basicSequence = 0;
            currStmp = getNextMill();
        }
        sequence = basicSequence;

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTAMP_LEFT
                | dataCenterId << DATA_CENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    /**
     * 获取机器编码
     *
     * @param dataCenterId
     * @param maxMachineId
     * @return
     */
    protected static long getMachineId(long dataCenterId, long maxMachineId) {
        StringBuffer sb = new StringBuffer();
        sb.append(dataCenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            // GET jvmPid
            sb.append(name.split("@")[0]);
        }
        // MAC + PID 的 hashcode 获取16个低位
        return (sb.toString().hashCode() & 0xffff) % (maxMachineId + 1);
    }

    /**
     * 获取数据中心编码
     */
    protected static long getDataCenterId(long maxDataCenterId) {
        if (maxDataCenterId < 0L || maxDataCenterId > MAX_DATA_CENTER_NUM) {
            maxDataCenterId = MAX_DATA_CENTER_NUM;
        }
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDataCenterId + 1);
            }
        } catch (Exception e) {
        }
        return id;
    }

}