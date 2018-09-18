package com.hhjt.study.retrofit;

import java.util.List;

public class PersonData {

    private String code;
    private String msg;
    private int count;
    private List<DataBean> data;

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

        private String createTimeStr;
        private String updateTimeStr;
        private String deptName;
        private String orgName;
        private String banName;
        private String floorName;
        private String dormName;
        private int count;
        private String id;
        private String banId;
        private String personType;
        private String gender;
        private String headPicture;
        private String orgId;
        private String dormId;
        private String floorId;
        private String deptId;
        private String personNumber;
        private String teacherDuty;
        private String studentType;
        private String teacherDutyName;
        private String studentTypeName;
        private String name;
        private String createrId;
        private String updateId;
        private String remark;
        private List<FaceListBean> faceList;
        private String alarmUrl;
        private boolean isStranger;
        private String welcomeWords;

        public String getTeacherDutyName() {
            return teacherDutyName;
        }

        public void setTeacherDutyName(String teacherDutyName) {
            this.teacherDutyName = teacherDutyName;
        }

        public String getStudentTypeName() {
            return studentTypeName;
        }

        public void setStudentTypeName(String studentTypeName) {
            this.studentTypeName = studentTypeName;
        }

        public String getAlarmUrl() {
            return alarmUrl;
        }

        public void setAlarmUrl(String alarmUrl) {
            this.alarmUrl = alarmUrl;
        }

        public boolean isStranger() {
            return isStranger;
        }

        public void setStranger(boolean stranger) {
            isStranger = stranger;
        }

        public String getWelcomeWords() {
            return welcomeWords;
        }

        public void setWelcomeWords(String welcomeWords) {
            this.welcomeWords = welcomeWords;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public String getUpdateTimeStr() {
            return updateTimeStr;
        }

        public void setUpdateTimeStr(String updateTimeStr) {
            this.updateTimeStr = updateTimeStr;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getBanName() {
            return banName;
        }

        public void setBanName(String banName) {
            this.banName = banName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getDormName() {
            return dormName;
        }

        public void setDormName(String dormName) {
            this.dormName = dormName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBanId() {
            return banId;
        }

        public void setBanId(String banId) {
            this.banId = banId;
        }

        public String getPersonType() {
            return personType;
        }

        public void setPersonType(String personType) {
            this.personType = personType;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getDormId() {
            return dormId;
        }

        public void setDormId(String dormId) {
            this.dormId = dormId;
        }

        public String getFloorId() {
            return floorId;
        }

        public void setFloorId(String floorId) {
            this.floorId = floorId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getPersonNumber() {
            return personNumber;
        }

        public void setPersonNumber(String personNumber) {
            this.personNumber = personNumber;
        }

        public String getTeacherDuty() {
            return teacherDuty;
        }

        public void setTeacherDuty(String teacherDuty) {
            this.teacherDuty = teacherDuty;
        }

        public String getStudentType() {
            return studentType;
        }

        public void setStudentType(String studentType) {
            this.studentType = studentType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreaterId() {
            return createrId;
        }

        public void setCreaterId(String createrId) {
            this.createrId = createrId;
        }

        public String getUpdateId() {
            return updateId;
        }

        public void setUpdateId(String updateId) {
            this.updateId = updateId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<FaceListBean> getFaceList() {
            return faceList;
        }

        public void setFaceList(List<FaceListBean> faceList) {
            this.faceList = faceList;
        }

        public static class FaceListBean {
            /**
             * createTime : 1533892409000
             * createrId : null
             * updateTime : null
             * updateId : null
             * remark : null
             * fileId : 2DF8F8CC0FEE470BB74FA58ED9EE5C8A
             * faceId : 6223FA1347BA478AA3A53F6AB8E99577
             * fileName : 6223FA1347BA478AA3A53F6AB8E99577_face_o7c.jpg
             * suffix : .jpg
             * url : /static/face/6223FA1347BA478AA3A53F6AB8E99577_face_o7c.jpg
             * primevalName : \mahuateng.jpg
             * fileSize : 55527
             * faceUrl : http://182.151.214.104:4000/static/face/6223FA1347BA478AA3A53F6AB8E99577_face_o7c.jpg
             */

            private long createTime;
            private String createrId;
            private String updateTime;
            private String updateId;
            private String remark;
            private String fileId;
            private String faceId;
            private String fileName;
            private String suffix;
            private String url;
            private String primevalName;
            private String fileSize;
            private String faceUrl;

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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateId() {
                return updateId;
            }

            public void setUpdateId(String updateId) {
                this.updateId = updateId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }

            public String getFaceId() {
                return faceId;
            }

            public void setFaceId(String faceId) {
                this.faceId = faceId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getSuffix() {
                return suffix;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPrimevalName() {
                return primevalName;
            }

            public void setPrimevalName(String primevalName) {
                this.primevalName = primevalName;
            }

            public String getFileSize() {
                return fileSize;
            }

            public void setFileSize(String fileSize) {
                this.fileSize = fileSize;
            }

            public String getFaceUrl() {
                return faceUrl;
            }

            public void setFaceUrl(String faceUrl) {
                this.faceUrl = faceUrl;
            }
        }
    }
}