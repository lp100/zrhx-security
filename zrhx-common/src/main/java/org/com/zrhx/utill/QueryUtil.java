package org.com.zrhx.utill;

import org.com.zrhx.enity.BaseEntity;
import org.com.zrhx.xss.SQLFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: QueryUtil
 * @Description: 查询参数
 * @author: gs
 * @date: 2017/6/9 8:56
 */
public class QueryUtil<T extends BaseEntity>  {


    public QueryUtil(T t, HttpServletRequest request){

        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        int[] pageNumber = pageNumber(page, rows);
        t.setLimit(pageNumber[1]);
        t.setStart(pageNumber[0]);
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx =   SQLFilter.sqlInject(request.getParameter("sidx"));
        String order =  SQLFilter.sqlInject(request.getParameter("order"));
        t.setSidx(sidx);
        t.setOrder(order);
    }

    /**
     *
     * @Title: pageNumber
     *
     * @Description: TODO(分页参数)
     *
     * @param @param page 第几页
     * @param @param rows 每页多条记录
     * @param @return
     *
     * @return int[] 返回类型 每页的开始记录 第一页为1 第二页为number +1 ,每页显示条数
     *
     * @throws
     *
     */
    protected int[] pageNumber(String page, String rows) {

        // 当前页,page由分页工具负责传过来
        int intPage = Integer.parseInt((page == null || page == "0" || ""
                .equals(page)) ? "1" : page);
        // 每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0" || ""
                .equals(rows)) ? "10" : rows);
        // 每页的开始记录 第一页为1 第二页为number +1
        int startNumber = (intPage - 1) * number;
        int[] num = { startNumber, number };
        return num;
    }

}
