package com.lj.gps.frame.utils;

import com.lj.gps.biz.entity.User;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.exception.GlobalExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;


@Component("vehicleAuthorityUtil")
public class VehicleAuthorityUtil {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static String isOpen = "on";

    /**
     * 映射用户对应车辆关系sql
     * 用户-->角色-->权限-->车辆(分组,分类,车辆)关系
     *
     * @param columnName
     * @return
     */
    public String appendSqlWithInTable(String columnName, Map<Integer, String> rvMap) {
        String groupSql = "";
        String classSql = "";
        String vehicleSql = "";
        String startParam = " and " + columnName + " in (";
        String endParam = " ) ";
        String symbol = " UNION ";
        String lastSql = "";
        StringBuilder sb = new StringBuilder();
        if (rvMap != null) {
            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP).split(",");
                groupSql = " SELECT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid LEFT JOIN d_group dgroup ON dclass.vehicleGroupId = dgroup.vehicleGroupId WHERE dgroup.vehicleGroupId IN ( " + StringUtils.join(array, ",") + " )";
                sb.append(groupSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS).split(",");
                classSql = " SELECT  DISTINCT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid WHERE dclass.classid IN (" + StringUtils.join(array, ",") + " )  ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(classSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE).split(",");
                vehicleSql = " SELECT DISTINCT dvl.vehicleId FROM d_vehicle dvl WHERE dvl.vehicleId in (" + StringUtils.join(array, ",") + " ) ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(vehicleSql);
            }
        }
        //Object array1[] = authorityDao.getVehicleIds(sb.toString());
        if (sb.length() > 0) {
            //lastSql = startParam + StringUtils.join(array1, ",") + endParam;
            lastSql = startParam + sb.toString() + endParam;
        }

        return lastSql;
    }

    /**
     * 映射用户对应车辆关系sql
     * 用户-->角色-->权限-->车辆(分组,分类,车辆)关系
     *
     * @return
     */
    public String appendSqlWithTable(Map<Integer, String> rvMap) {
        String groupSql = "";
        String classSql = "";
        String vehicleSql = "";
        String startParam = " ,(";
        String endParam = " ) temp  ";
        String symbol = " UNION ";
        String lastSql = "";
        StringBuilder sb = new StringBuilder();
        if (rvMap != null) {
            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP).split(",");
                groupSql = " SELECT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid LEFT JOIN d_group dgroup ON dclass.vehicleGroupId = dgroup.vehicleGroupId WHERE dgroup.vehicleGroupId IN ( " + StringUtils.join(array, ",") + " ) ";
                sb.append(groupSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS).split(",");
                classSql = " SELECT DISTINCT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid WHERE dclass.classid IN (" + StringUtils.join(array, ",") + " )  ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(classSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE).split(",");
                vehicleSql = " SELECT DISTINCT  dvl.vehicleId FROM d_vehicle dvl WHERE dvl.vehicleId in (" + StringUtils.join(array, ",") + " ) ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(vehicleSql);
            }
        }
        if (sb.length() > 0) {
            lastSql = startParam + sb.toString() + endParam;
        }

        return lastSql;
    }

    /**
     * 权限控制的关联条件（sql语句）
     *
     * @param columnName 关联字段名称
     * @return
     */
    public String appendSqlWithCondition(String columnName, Map<Integer, String> rvMap) {
        if (rvMap != null) {
            String sql = " and temp.vehicleId = " + columnName + " ";
            return sql;
        }
        return "";
    }

    /**
     * 权限控制的关联条件（sql语句）
     *
     * @param sort
     * @return
     */
    public String appendSqlWithOrderByTemp(String sort, Map<Integer, String> rvMap) {
        if (rvMap != null) {
            String sql = " ORDER BY temp.vehicleId  " + sort + " ";
            return sql;
        }
        return "";
    }

    /**
     * 分组权限控制的表关联（sql语句）
     *
     * @return
     */
    public String appendSqlWithGroupTable(Map<Integer, String> rvMap) {
        String groupSql = "";
        String classSql = "";
        String vehicleSql = "";
        String startParam = " ,(";
        String endParam = " ) temp ";
        String symbol = " UNION ";
        String lastSql = "";
        StringBuilder sb = new StringBuilder();
        if (rvMap != null) {
            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP).split(",");
                groupSql = " SELECT dgroup.vehicleGroupId  FROM d_group dgroup  WHERE dgroup.vehicleGroupId IN ( " + StringUtils.join(array, ",") + " )";
                sb.append(groupSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS).split(",");
                classSql = " SELECT DISTINCT dgroup.vehicleGroupId FROM d_group dgroup LEFT JOIN d_class dclass ON dgroup.vehicleGroupId = dclass.vehicleGroupId WHERE dclass.classid IN (" + StringUtils.join(array, ",") + " )  ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(classSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE).split(",");
                vehicleSql = " SELECT DISTINCT dgroup.vehicleGroupId FROM d_group dgroup LEFT  JOIN d_class dclass ON dgroup.vehicleGroupId = dclass.vehicleGroupId LEFT JOIN d_vehicle dvehicle ON dclass.classid = dvehicle.classId WHERE dvehicle.vehicleId IN (" + StringUtils.join(array, ",") + " ) ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(vehicleSql);
            }
        }
        if (sb.length() > 0) {
            lastSql = startParam + sb.toString() + endParam;
        }

        return lastSql;
    }

    /**
     * 分组权限控制的关联条件（sql语句）
     *
     * @param columnName 关联字段名称
     * @return
     */
    public String appendSqlWithGroupCondition(String columnName, Map<Integer, String> rvMap) {
        if (rvMap != null) {
            String sql = "  and temp.vehicleGroupId = " + columnName + " ";
            return sql;
        }
        return "";
    }


    /**
     * 分类权限控制的表关联（sql语句）
     *
     * @return
     */
    public String appendSqlWithClassTable(Map<Integer, String> rvMap) {
        String groupSql = "";
        String classSql = "";
        String vehicleSql = "";
        String startParam = " ,(";
        String endParam = " ) temp ";
        String symbol = " UNION ";
        String lastSql = "";
        StringBuilder sb = new StringBuilder();
        if (rvMap != null) {
            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP).split(",");
                groupSql = " SELECT dclass.classid FROM d_class dclass LEFT JOIN d_group dgroup ON dclass.vehicleGroupId = dgroup.vehicleGroupId  WHERE dgroup.vehicleGroupId IN  ( " + StringUtils.join(array, ",") + " )  ";
                sb.append(groupSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS).split(",");
                classSql = " SELECT DISTINCT dclass.classid FROM d_class dclass WHERE dclass.classid IN  (" + StringUtils.join(array, ",") + " ) ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(classSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE).split(",");
                vehicleSql = " SELECT DISTINCT dclass.classid FROM d_class dclass LEFT JOIN d_group dgroup ON dclass.vehicleGroupId = dgroup.vehicleGroupId LEFT JOIN d_vehicle dvehicle  ON dclass.classid = dvehicle.classId WHERE dvehicle.vehicleId IN (" + StringUtils.join(array, ",") + " )  ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(vehicleSql);
            }
        }
        if (sb.length() > 0) {
            lastSql = startParam + sb.toString() + endParam;
        }

        return lastSql;
    }

    /**
     * 分类权限控制的关联条件（sql语句）
     *
     * @param columnName 关联字段名称
     * @return
     */
    public String appendSqlWithClassCondition(String columnName, Map<Integer, String> rvMap) {
        if (rvMap != null) {
            String sql = "  and temp.classid = " + columnName + " ";
            return sql;
        }
        return "";
    }

    /**
     * 映射用户对应车辆关系sql
     * 用户-->角色-->权限-->车辆(分组,分类,车辆)关系
     *
     * @param columnName
     * @return
     */
    public String appendSqlWithOrTable(String columnName, Map<Integer, String> rvMap) {
        String groupSql = "";
        String classSql = "";
        String vehicleSql = "";
        String startParam = " and ( ";
        String endParam = " ) ";
        String symbol = " or ";
        String lastSql = "";
        StringBuilder sb = new StringBuilder();
        if (rvMap != null) {
            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_GROUP).split(",");
                groupSql = columnName + " in ( SELECT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid LEFT JOIN d_group dgroup ON dclass.vehicleGroupId = dgroup.vehicleGroupId WHERE dgroup.vehicleGroupId IN ( " + StringUtils.join(array, ",") + " )) ";
                sb.append(groupSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_CLASS).split(",");
                classSql = columnName + " in ( SELECT  DISTINCT dvl.vehicleId FROM d_vehicle dvl LEFT JOIN d_class dclass ON  dvl.classId = dclass.classid WHERE dclass.classid IN (" + StringUtils.join(array, ",") + " ))  ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(classSql);
            }

            if (StringUtils.isNotEmpty(rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE))) {
                String array[] = rvMap.get(Constants.ROLE_VEHICLE_TYPE_VEHICLE).split(",");
                vehicleSql = columnName + " in (" + StringUtils.join(array, ",") + " ) ";
                if (sb.length() > 0) {
                    sb.append(symbol);
                }
                sb.append(vehicleSql);
            }
        }
        if (sb.length() > 0) {
            lastSql = startParam + sb.toString() + endParam;
        }

        return lastSql;
    }

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public static Integer getLoginUserId() {
        Object obj = RequestContextHolder.currentRequestAttributes().getAttribute(Constants.SESSION_LOGIN_USER, RequestAttributes.SCOPE_SESSION);
        if (null != obj) {
            User user = (User) obj;
            return user.getUserid();
        }
        return null;
    }

}
