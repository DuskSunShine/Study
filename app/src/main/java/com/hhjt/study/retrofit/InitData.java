package com.hhjt.study.retrofit;



import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class InitData {


    /**
     * code : 10200
     * msg : 成功
     * count : 0
     * data : [{"createTimeStr":"2018-04-20 15:43:10","updateTimeStr":null,"analysis":{"createTime":1524210102000,"createrId":"4da698c87bf94a2c8ede341285cfc4","updateTime":null,"updateId":null,"remark":"","serverId":"ED4DB5D0929A430FA3A97BC6E1843C0A","serverName":"123算法服务器","serverType":"analysis_server","serverCode":null,"serverIp":"192.168.3.123","serverPort":"6666","userName":"","password":"","status":"1"},"accessName":"一号门","accessAddress":"","setUpTimeStr":null,"manufacturerName":"海康","serverName":null,"port":"554","password":"hk123456","status":"1","cameraId":"D651A23F097A4C9990DDAD1652FEE706","analysisServer":"ED4DB5D0929A430FA3A97BC6E1843C0A","userName":"admin","cameraType":"distinguish_camera","ip":"192.168.3.64","rtspAddress":"rtsp://admin:hk123456@192.168.3.64:554/Streaming/Channels/101?transportmode=unicast&amp;profile=Profile_1","accessId":"10001","manufacturer":"hIkvision","cameraCode":"fff","cameraName":"算法办公室","createrId":"4da698c87bf94a2c8ede341285cfc4","updateId":null,"remark":""}]
     */


    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private List<DataBean> data;

    public static InitData objectFromData(String str) {

        return new Gson().fromJson(str, InitData.class);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return accessName+","+manufacturerName+","+ip+","+userName+","+cameraName;
        }

        /**
         * createTimeStr : 2018-04-20 15:43:10
         * updateTimeStr : null
         * analysis : {"createTime":1524210102000,"createrId":"4da698c87bf94a2c8ede341285cfc4","updateTime":null,"updateId":null,"remark":"","serverId":"ED4DB5D0929A430FA3A97BC6E1843C0A","serverName":"123算法服务器","serverType":"analysis_server","serverCode":null,"serverIp":"192.168.3.123","serverPort":"6666","userName":"","password":"","status":"1"}
         * accessName : 一号门
         * accessAddress :
         * setUpTimeStr : null
         * manufacturerName : 海康
         * serverName : null
         * port : 554
         * password : hk123456
         * status : 1
         * cameraId : D651A23F097A4C9990DDAD1652FEE706
         * analysisServer : ED4DB5D0929A430FA3A97BC6E1843C0A
         * userName : admin
         * cameraType : distinguish_camera
         * ip : 192.168.3.64
         * rtspAddress : rtsp://admin:hk123456@192.168.3.64:554/Streaming/Channels/101?transportmode=unicast&amp;profile=Profile_1
         * accessId : 10001
         * manufacturer : hIkvision
         * cameraCode : fff
         * cameraName : 算法办公室
         * createrId : 4da698c87bf94a2c8ede341285cfc4
         * updateId : null
         * remark :
         */

        @SerializedName("createTimeStr")
        private String createTimeStr;
        @SerializedName("updateTimeStr")
        private Object updateTimeStr;
        @SerializedName("analysis")
        private AnalysisBean analysis;
        @SerializedName("accessName")
        private String accessName;
        @SerializedName("accessAddress")
        private String accessAddress;
        @SerializedName("setUpTimeStr")
        private Object setUpTimeStr;
        @SerializedName("manufacturerName")
        private String manufacturerName;
        @SerializedName("serverName")
        private Object serverName;
        @SerializedName("port")
        private String port;
        @SerializedName("password")
        private String password;
        @SerializedName("status")
        private String status;
        @SerializedName("cameraId")
        private String cameraId;
        @SerializedName("analysisServer")
        private String analysisServer;
        @SerializedName("userName")
        private String userName;
        @SerializedName("cameraType")
        private String cameraType;
        @SerializedName("ip")
        private String ip;
        @SerializedName("rtspAddress")
        private String rtspAddress;
        @SerializedName("accessId")
        private String accessId;
        @SerializedName("manufacturer")
        private String manufacturer;
        @SerializedName("cameraCode")
        private String cameraCode;
        @SerializedName("cameraName")
        private String cameraName;
        @SerializedName("createrId")
        private String createrId;
        @SerializedName("updateId")
        private Object updateId;
        @SerializedName("remark")
        private String remark;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public Object getUpdateTimeStr() {
            return updateTimeStr;
        }

        public void setUpdateTimeStr(Object updateTimeStr) {
            this.updateTimeStr = updateTimeStr;
        }

        public AnalysisBean getAnalysis() {
            return analysis;
        }

        public void setAnalysis(AnalysisBean analysis) {
            this.analysis = analysis;
        }

        public String getAccessName() {
            return accessName;
        }

        public void setAccessName(String accessName) {
            this.accessName = accessName;
        }

        public String getAccessAddress() {
            return accessAddress;
        }

        public void setAccessAddress(String accessAddress) {
            this.accessAddress = accessAddress;
        }

        public Object getSetUpTimeStr() {
            return setUpTimeStr;
        }

        public void setSetUpTimeStr(Object setUpTimeStr) {
            this.setUpTimeStr = setUpTimeStr;
        }

        public String getManufacturerName() {
            return manufacturerName;
        }

        public void setManufacturerName(String manufacturerName) {
            this.manufacturerName = manufacturerName;
        }

        public Object getServerName() {
            return serverName;
        }

        public void setServerName(Object serverName) {
            this.serverName = serverName;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCameraId() {
            return cameraId;
        }

        public void setCameraId(String cameraId) {
            this.cameraId = cameraId;
        }

        public String getAnalysisServer() {
            return analysisServer;
        }

        public void setAnalysisServer(String analysisServer) {
            this.analysisServer = analysisServer;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCameraType() {
            return cameraType;
        }

        public void setCameraType(String cameraType) {
            this.cameraType = cameraType;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getRtspAddress() {
            return rtspAddress;
        }

        public void setRtspAddress(String rtspAddress) {
            this.rtspAddress = rtspAddress;
        }

        public String getAccessId() {
            return accessId;
        }

        public void setAccessId(String accessId) {
            this.accessId = accessId;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getCameraCode() {
            return cameraCode;
        }

        public void setCameraCode(String cameraCode) {
            this.cameraCode = cameraCode;
        }

        public String getCameraName() {
            return cameraName;
        }

        public void setCameraName(String cameraName) {
            this.cameraName = cameraName;
        }

        public String getCreaterId() {
            return createrId;
        }

        public void setCreaterId(String createrId) {
            this.createrId = createrId;
        }

        public Object getUpdateId() {
            return updateId;
        }

        public void setUpdateId(Object updateId) {
            this.updateId = updateId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public static class AnalysisBean {
            /**
             * createTime : 1524210102000
             * createrId : 4da698c87bf94a2c8ede341285cfc4
             * updateTime : null
             * updateId : null
             * remark :
             * serverId : ED4DB5D0929A430FA3A97BC6E1843C0A
             * serverName : 123算法服务器
             * serverType : analysis_server
             * serverCode : null
             * serverIp : 192.168.3.123
             * serverPort : 6666
             * userName :
             * password :
             * status : 1
             */

            @SerializedName("createTime")
            private long createTime;
            @SerializedName("createrId")
            private String createrId;
            @SerializedName("updateTime")
            private Object updateTime;
            @SerializedName("updateId")
            private Object updateId;
            @SerializedName("remark")
            private String remark;
            @SerializedName("serverId")
            private String serverId;
            @SerializedName("serverName")
            private String serverName;
            @SerializedName("serverType")
            private String serverType;
            @SerializedName("serverCode")
            private Object serverCode;
            @SerializedName("serverIp")
            private String serverIp;
            @SerializedName("serverPort")
            private String serverPort;
            @SerializedName("userName")
            private String userName;
            @SerializedName("password")
            private String password;
            @SerializedName("status")
            private String status;

            public static AnalysisBean objectFromData(String str) {

                return new Gson().fromJson(str, AnalysisBean.class);
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreaterId() {
                return createrId;
            }

            public void setCreaterId(String createrId) {
                this.createrId = createrId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateId() {
                return updateId;
            }

            public void setUpdateId(Object updateId) {
                this.updateId = updateId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getServerId() {
                return serverId;
            }

            public void setServerId(String serverId) {
                this.serverId = serverId;
            }

            public String getServerName() {
                return serverName;
            }

            public void setServerName(String serverName) {
                this.serverName = serverName;
            }

            public String getServerType() {
                return serverType;
            }

            public void setServerType(String serverType) {
                this.serverType = serverType;
            }

            public Object getServerCode() {
                return serverCode;
            }

            public void setServerCode(Object serverCode) {
                this.serverCode = serverCode;
            }

            public String getServerIp() {
                return serverIp;
            }

            public void setServerIp(String serverIp) {
                this.serverIp = serverIp;
            }

            public String getServerPort() {
                return serverPort;
            }

            public void setServerPort(String serverPort) {
                this.serverPort = serverPort;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
