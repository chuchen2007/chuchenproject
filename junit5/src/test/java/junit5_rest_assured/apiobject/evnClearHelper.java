package junit5_rest_assured.apiobject;

import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description:
 * 优化思路:对公共调用的清理数据方法进行封装.
 * 这么封装有缺点,没有用到 DepartmentObject封装的类,导致后续维护增加成本.比如创建部门
 * 接口进行的更改,需要修改DepartmentObject类和此类两处创建部门接口.
 * 所以进行task层封装,规避这个缺点
 * @Author: chuchen
 * @CreateDate: 2020/12/21$ 0:11$
 */
public class evnClearHelper {
    public static void getEvnClear(String access_token){
        //先调用查询方法,查询公司中有多少部门
        Response listDepartmentResponse = given().log().all()
                .when()
                .contentType("application/json;charset=UTF-8")
                .queryParams("access_token", access_token)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .extract().response();
//        assertEquals("0", listDepartmentResponse.path("errcode").toString());//断言不放在封装层
        //获取部门列表,返回的id是一个数组,利用foreach循环,定义一个int类型变量遍历返回的id数组
        ArrayList<Integer> listDepartmentResponseId = listDepartmentResponse.path("department.id");
        for (int evnClearDepartmentId : listDepartmentResponseId) {
            if (1 == evnClearDepartmentId || 5 == evnClearDepartmentId || 6 == evnClearDepartmentId) {
                //1为跟部门,无法删除,5与6部门比较喜欢,不想删除
                continue;
            }
            System.out.print(evnClearDepartmentId + ",");//查看有多少部门
            //将获取的evnClearDepartmentId作为删除部门方法入参的departmentId,达到清楚数据的效果
            Response deleteDepartmentResponse = given().log().all()//查看evnClearDepartmentId入参信息对不对
                    .when()
                    .contentType("application/json")
                    .queryParams("access_token", access_token)
                    .param("id", evnClearDepartmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then().log().all()
                    .extract().response();
        }
    }
}
