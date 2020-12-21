package junit5_rest_assured.task;

import io.restassured.response.Response;
import junit5_rest_assured.apiobject.DepartmentObject;

import java.util.ArrayList;

/**
 * @Description:
 * 优化思路:对清理数据进行task层封装
 * @Author: chuchen
 * @CreateDate: 2020/12/19$ 17:23$
 */
public class EvnClearHeleperTask {
    public static void evnClearHeleperTask (String access_token){
        //先调用查询方法,查询公司中有多少部门
        Response listDepartmentResponse = DepartmentObject.listDepartmentObject(access_token);
//        assertEquals("0", listDepartmentResponse.path("errcode").toString());//断言不卸载封装层
        //获取部门列表,返回的id是一个数组,利用foreach循环,定义一个int类型变量遍历返回的id数组
        ArrayList<Integer> listDepartmentResponseId = listDepartmentResponse.path("department.id");
        for (int evnClearDepartmentId : listDepartmentResponseId) {
            if (1 == evnClearDepartmentId || 5 == evnClearDepartmentId || 6 == evnClearDepartmentId) {
                //1为跟部门,无法删除,5与6部门比较喜欢,不想删除
                continue;
            }
            System.out.print(evnClearDepartmentId + ",");//查看有多少部门
            //将获取的evnClearDepartmentId作为删除部门方法入参的departmentId,达到清楚数据的效果
            Response deleteDepartmentResponse = DepartmentObject.deleteDepartmentObject(access_token, String.valueOf(evnClearDepartmentId));
        }

    }
}
