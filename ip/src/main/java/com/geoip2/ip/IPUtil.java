package com.geoip2.ip;

/**
 * Created by 79782 on 2018/6/23.
 */
public class IPUtil {
    public static String getNetMask(String mask) {
        int inetMask =Integer.parseInt(mask);
        if(inetMask > 32){
            return null;
        }
        //子网掩码为1占了几个字节
        int num1 = inetMask/8;
        //子网掩码的补位位数
        int num2 = inetMask%8;
        int array[] = new int[4];
        for (int i = 0; i < num1; i++) {
            array[i] = 255;
        }
        for (int i = num1; i < 4; i++) {
            array[i] = 0;
        }
        for (int i = 0; i < num2; num2--) {
            array[num1] += Math.pow(2, 8-num2);
        }
        String netMask =  array[0] + "." + array[1] + "." + array[2] + "." + array[3];
        return netMask;
    }

    public static String getLowAddr(String ipinfo, String netMask) {
        String lowAddr = "";
        int ipArray[] = new int[4];
        int netMaskArray[] = new int[4];
        if(4 != ipinfo.split("\\.").length || "" == netMask){
            return null;
        }
        for (int i = 0; i < 4; i++) {
            try{
                ipArray[i] = Integer.parseInt(ipinfo.split("\\.")[i]);
            }catch(NumberFormatException e){
                String ip = ipinfo.replaceAll("\n", "");
                ipArray[i] = Integer.parseInt(ip.split("\\.")[i]);
            }
            netMaskArray[i] = Integer.parseInt(netMask.split("\\.")[i]);
            if(ipArray[i] > 255 || ipArray[i] < 0 || netMaskArray[i] > 255 || netMaskArray[i] < 0){
                return null;
            }
            ipArray[i] = ipArray[i]&netMaskArray[i];
        }
        //构造最小地址
        for (int i = 0; i < 4; i++){
            if(i == 3){
                ipArray[i] = ipArray[i] + 1;
            }
            if ("" == lowAddr){
                lowAddr +=ipArray[i];
            } else{
                lowAddr += "." + ipArray[i];
            }
        }
        return lowAddr;
    }

    public static String getHighAddr(String ipinfo, String netMask) {
        String lowAddr = getLowAddr(ipinfo, netMask);
        int hostNumber = getHostNumber(netMask);
        if("" == lowAddr || hostNumber == 0){
            return null;
        }
        int lowAddrArray[] = new int[4];
        for (int i = 0; i < 4; i++) {
            lowAddrArray[i] = Integer.parseInt(lowAddr.split("\\.")[i]);
            if(i == 3){
                lowAddrArray[i] = lowAddrArray[i] - 1;
            }
        }
        lowAddrArray[3] = lowAddrArray[3] + (hostNumber - 1);
        if(lowAddrArray[3] >255){
            int k = lowAddrArray[3] / 256;
            lowAddrArray[3] = lowAddrArray[3] % 256;
            lowAddrArray[2] = lowAddrArray[2] + k;
        }
        if(lowAddrArray[2] > 255){
            int  j = lowAddrArray[2] / 256;
            lowAddrArray[2] = lowAddrArray[2] % 256;
            lowAddrArray[1] = lowAddrArray[1] + j;
            if(lowAddrArray[1] > 255){
                int  k = lowAddrArray[1] / 256;
                lowAddrArray[1] = lowAddrArray[1] % 256;
                lowAddrArray[0] = lowAddrArray[0] + k;
            }
        }
        String highAddr = "";
        for(int i = 0; i < 4; i++){
            if(i == 3){
                lowAddrArray[i] = lowAddrArray[i] - 1;
            }
            if("" == highAddr){
                highAddr = lowAddrArray[i]+"";
            }else{
                highAddr += "." + lowAddrArray[i];
            }
        }
        return highAddr;
    }

    public static int getHostNumber(String netMask) {
        int hostNumber = 0;
        int netMaskArray[] = new int[4];
        for (int i = 0; i < 4 ; i++) {
            netMaskArray[i] = Integer.parseInt(netMask.split("\\.")[i]);
            if(netMaskArray[i] < 255){
                hostNumber =  (int) (Math.pow(256,3-i) * (256 - netMaskArray[i]));
                break;
            }
        }
        return hostNumber;
    }

    public static long ip2long(String ip) {
        String[] ips = ip.split("[.]");
        long num = 16777216L * Long.parseLong(ips[0]) + 65536L
                * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2])
                + Long.parseLong(ips[3]);
        return num;
    }
}