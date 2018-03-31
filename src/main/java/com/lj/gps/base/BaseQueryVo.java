package com.lj.gps.base;
import java.io.Serializable;
import java.util.List;

/**
 * 查询条件基类,所有的QueryVo都要继承这个类
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/11<br/>
 * Time: 9:24<br/>
 */
public class BaseQueryVo implements Serializable {

    /**
     * 查询值
     */
    private Integer id;
    private Integer int1;
    private Integer int2;
    private Integer int3;

    private String string1;
    private String string2;
    private String string3;

    /**
     * 对应Mapper.xml foreach collection的list/array
     */
//    private List<Integer> list;
//    private Integer[] array;

    /**
     * 设备号list
     */
    private List<String> snList;

    /**
     * 用户id,分组id,分类id,车辆id,工单id,设备id
     * List对应Mapper.xml foreach collection的list
     */
    private Integer userId;
    private Integer groupId;
    private Integer classId;
    private Integer vehicleId;
    private Integer wordOrderId;
    private Integer trackInfoId;
    private List<Integer> idList;
    private List<Integer> groupIdList;
    private List<Integer> classIdList;
    private List<Integer> vehicleIdList;
    private List<Integer> wordOrderIdList;
    private List<Integer> trackInfoIdList;

    /**
     * 姓名/名称
     */
    private String name;

    /**
     * 开始时间
     */
    private String sTime;

    /**
     * 截止时间
     */
    private String eTime;

    /**
     * 当前时间
     */
    private String cTime;

    /**
     * 追加table关联
     */
    private String appendSqlWithTable;

    /**
     * 追加table关联，带列参数
     */
    private String appendSqlWithInTable;

    /**
     * 权限控制的关联条件（sql语句），追加Where条件
     */
    private String appendSqlWithCondition;

    /**
     * 权限控制的关联条件（sql语句），追加Order排序
     */
    private String appendSqlWithOrderByTemp;

    /**
     * 分组权限控制的表关联
     */
    private String appendSqlWithGroupTable;

    /**
     * 分组权限控制的关联条件（sql语句）
     */
    private String appendSqlWithGroupCondition;

    /**
     * 分类权限控制的表关联
     */
    private String appendSqlWithClassTable;

    /**
     * 分类权限控制的关联条件（sql语句）
     */
    private String appendSqlWithClassCondition;

    /**
     * 映射用户对应车辆关系sql
     */
    private String appendSqlWithOrTable;

    /**
     * 根据时间段拼装sql语句
     */
    private String appendSqlByTimeRange;

    /**
     * 清除所有sql
     */
    public void emptyAllSql() {
        setAppendSqlWithTable("");
        setAppendSqlWithInTable("");
        setAppendSqlWithCondition("");
        setAppendSqlWithOrderByTemp("");
        setAppendSqlWithGroupTable("");
        setAppendSqlWithGroupCondition("");
        setAppendSqlWithClassTable("");
        setAppendSqlWithClassCondition("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    public Integer getInt3() {
        return int3;
    }

    public void setInt3(Integer int3) {
        this.int3 = int3;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getWordOrderId() {
        return wordOrderId;
    }

    public void setWordOrderId(Integer wordOrderId) {
        this.wordOrderId = wordOrderId;
    }

    public Integer getTrackInfoId() {
        return trackInfoId;
    }

    public void setTrackInfoId(Integer trackInfoId) {
        this.trackInfoId = trackInfoId;
    }

    public List<String> getSnList() {
        return snList;
    }

    public void setSnList(List<String> snList) {
        this.snList = snList;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public List<Integer> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Integer> groupIdList) {
        this.groupIdList = groupIdList;
    }

    public List<Integer> getClassIdList() {
        return classIdList;
    }

    public void setClassIdList(List<Integer> classIdList) {
        this.classIdList = classIdList;
    }

    public List<Integer> getVehicleIdList() {
        return vehicleIdList;
    }

    public void setVehicleIdList(List<Integer> vehicleIdList) {
        this.vehicleIdList = vehicleIdList;
    }

    public List<Integer> getWordOrderIdList() {
        return wordOrderIdList;
    }

    public void setWordOrderIdList(List<Integer> wordOrderIdList) {
        this.wordOrderIdList = wordOrderIdList;
    }

    public List<Integer> getTrackInfoIdList() {
        return trackInfoIdList;
    }

    public void setTrackInfoIdList(List<Integer> trackInfoIdList) {
        this.trackInfoIdList = trackInfoIdList;
    }

//    public List<Integer> getList() {
//        return list;
//    }
//
//    public void setList(List<Integer> list) {
//        this.list = list;
//    }
//
//    public Integer[] getArray() {
//        return array;
//    }
//
//    public void setArray(Integer[] array) {
//        this.array = array;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getAppendSqlWithTable() {
        return appendSqlWithTable;
    }

    public void setAppendSqlWithTable(String appendSqlWithTable) {
        this.appendSqlWithTable = appendSqlWithTable;
    }

    public String getAppendSqlWithInTable() {
        return appendSqlWithInTable;
    }

    public void setAppendSqlWithInTable(String appendSqlWithInTable) {
        this.appendSqlWithInTable = appendSqlWithInTable;
    }

    public String getAppendSqlWithCondition() {
        return appendSqlWithCondition;
    }

    public void setAppendSqlWithCondition(String appendSqlWithCondition) {
        this.appendSqlWithCondition = appendSqlWithCondition;
    }

    public String getAppendSqlWithOrderByTemp() {
        return appendSqlWithOrderByTemp;
    }

    public void setAppendSqlWithOrderByTemp(String appendSqlWithOrderByTemp) {
        this.appendSqlWithOrderByTemp = appendSqlWithOrderByTemp;
    }

    public String getAppendSqlWithGroupTable() {
        return appendSqlWithGroupTable;
    }

    public void setAppendSqlWithGroupTable(String appendSqlWithGroupTable) {
        this.appendSqlWithGroupTable = appendSqlWithGroupTable;
    }

    public String getAppendSqlWithGroupCondition() {
        return appendSqlWithGroupCondition;
    }

    public void setAppendSqlWithGroupCondition(String appendSqlWithGroupCondition) {
        this.appendSqlWithGroupCondition = appendSqlWithGroupCondition;
    }

    public String getAppendSqlWithClassTable() {
        return appendSqlWithClassTable;
    }

    public void setAppendSqlWithClassTable(String appendSqlWithClassTable) {
        this.appendSqlWithClassTable = appendSqlWithClassTable;
    }

    public String getAppendSqlWithClassCondition() {
        return appendSqlWithClassCondition;
    }

    public void setAppendSqlWithClassCondition(String appendSqlWithClassCondition) {
        this.appendSqlWithClassCondition = appendSqlWithClassCondition;
    }

    public String getAppendSqlWithOrTable() {
        return appendSqlWithOrTable;
    }

    public void setAppendSqlWithOrTable(String appendSqlWithOrTable) {
        this.appendSqlWithOrTable = appendSqlWithOrTable;
    }

    public String getAppendSqlByTimeRange() {
        return appendSqlByTimeRange;
    }

    public void setAppendSqlByTimeRange(String appendSqlByTimeRange) {
        this.appendSqlByTimeRange = appendSqlByTimeRange;
    }
}
