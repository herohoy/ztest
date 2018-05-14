package com.herohoy.tools.treeloop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author lihui  2018/5/6 下午5:45
 **/
public class TreeLoopJava {
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/tree?useUnicode=true&characterEncoding=utf8",
                    "root","today-36524");
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * 查询所有子节点的java递归代码写法（可行，尾递归暂不适用，需要以此代码为出发点思考优化）
     * @param id
     * @return
     */
    public ArrayList<TreeNodeJava> getChildNodesRec(Long id) {
        ArrayList<TreeNodeJava> result = new ArrayList<TreeNodeJava>();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from tree_node where parent_id=?");
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                result.add(new TreeNodeJava(rs.getLong("id"),Optional.of(rs.getString("name")),
                        Optional.of(new TreeNodeJava(id)),getChildNodesRec(rs.getLong("id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
