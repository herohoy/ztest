package com.herohoy.tools.treeloop;

import com.herohoy.utils.tailrec.TailInvoke;
import com.herohoy.utils.tailrec.TailRecursion;

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

    private ResultSet getNodeRs(Long id){
        ResultSet result = null;
        try {
            PreparedStatement pst = conn.prepareStatement("select * from tree_node where id=?");
            pst.setLong(1,id);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    private ResultSet getNodeRsChilds(Long id){
        ResultSet result = null;
        try {
            PreparedStatement pst = conn.prepareStatement("select * from tree_node where parent_id=?");
            pst.setLong(1,id);
            result = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    /**
     * 当前节点查询 (假设父节点默认查出)
     * @return
     * @author lihui
     */
    public Optional<TreeNodeJava> getNode(Long id){
        Optional<TreeNodeJava> result = Optional.empty();
        try {
            ResultSet rs = getNodeRs(id);
            while (rs.next()){
                result = Optional.of(new TreeNodeJava(rs.getLong("id"),
                        Optional.of(rs.getString("name"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    /**
     * 单层父节点查询
     * @param id unique
     * @return
     */
    public Optional<TreeNodeJava> getNodeWithSingleParent(Long id){
        Optional<TreeNodeJava> result = Optional.empty();
        try {
            ResultSet rs = getNodeRs(id);
            while (rs.next()){
                result = Optional.of(new TreeNodeJava(rs.getLong("id"),
                        Optional.of(rs.getString("name")),
                        getNode(rs.getLong("parent_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    private Optional<TreeNodeJava> currentNode = Optional.empty();

    private TailRecursion<Optional<TreeNodeJava>> getParentsTailrec(final Optional<TreeNodeJava> node){
        if(!node.isPresent()){
            return TailInvoke.done(currentNode);
        }else{
            node.get().setParent(
                node.get().getParent().isPresent() ?
                    getNodeWithSingleParent(node.get().getParent().get().getId()) :
                        Optional.empty()
            );
            return TailInvoke.call(() -> getParentsTailrec(node.get().getParent()));
        }
    }

    public Optional<TreeNodeJava> getNodeJavaWithParents(Long id){
        currentNode = getNodeWithSingleParent(id);
        return getParentsTailrec(currentNode).invoke();
    }

    /**
     * 查询所有子节点的java递归代码写法（可行，尾递归暂不适用，需要以此代码为出发点思考优化）
     * @param id
     * @return
     */
    public ArrayList<TreeNodeJava> getChildNodesRec(Long id) {
        ArrayList<TreeNodeJava> result = new ArrayList<TreeNodeJava>();
        try {
            ResultSet rs = getNodeRsChilds(id);
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
