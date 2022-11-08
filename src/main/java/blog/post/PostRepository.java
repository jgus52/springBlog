package blog.post;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final DataSource dataSource;

    public PostRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{ if(conn!= null) conn.close();} catch(SQLException e){}
        try{ if(pstmt!= null) pstmt.close();} catch(SQLException e){}
        try{ if(rs!= null) rs.close();} catch(SQLException e){}
    }

    public Post createPost(Post post){
        String sql = "insert into post(content) values(?) RETURNING * ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            Array contentArray = conn.createArrayOf("TEXT", post.getContent());

            pstmt.setArray(1, contentArray);
            pstmt.execute();

            rs = pstmt.getResultSet();
            rs.next();

            post.setId(rs.getInt("id"));
            post.setCreatedAt(rs.getTimestamp("createdAt"));

            return post;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }

    public List<Post> getPosts() {
        String sql = "select * from post order by createdAt desc";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()){
//                Post post = new Post(rs.getInt("id"), (String[])rs.getArray("content").getArray(), rs.getTimestamp("createdAt"));
                Post post= new Post();
                post.setId(rs.getInt("id"));
                if(rs.getArray("content") == null){
                    String[] arr={};
                    post.setContent(arr);
                }
                else
                    post.setContent((String[])rs.getArray("content").getArray());
                post.setCreatedAt(rs.getTimestamp("createdAt"));

                posts.add(post);
            }

            return posts;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }

    public Post getPost() {
        String sql = "select * from post order by createdAt desc LIMIT 1";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if(rs.next() == false) return null;

//            Post post = new Post(rs.getInt("id"), (String[])rs.getArray("content").getArray(), rs.getTimestamp("createdAt"));
            Post post= new Post();
            post.setId(rs.getInt("id"));
            if(rs.getArray("content") == null){
                String[] arr={};
                post.setContent(arr);
            }
            else
                post.setContent((String[])rs.getArray("content").getArray());
            post.setCreatedAt(rs.getTimestamp("createdAt"));

            return post;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }

    public Post getPost(int id) {
        String sql = "select * from post WHERE id=(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if(rs.next() == false) return null;

//            rs.next();

//            Post post = new Post(rs.getInt("id"), (String[])rs.getArray("content").getArray(), rs.getTimestamp("createdAt"));
            Post post= new Post();
            post.setId(rs.getInt("id"));
            if(rs.getArray("content") == null){
                String[] arr={};
                post.setContent(arr);
            }
            else
                post.setContent((String[])rs.getArray("content").getArray());
            post.setCreatedAt(rs.getTimestamp("createdAt"));

            return post;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }

    public void editPost(Post post){
        String sql = "update post set content=(?) where id=(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            Array contentArray = conn.createArrayOf("TEXT", post.getContent());

            pstmt.setArray(1, contentArray);
            pstmt.setInt(2, post.getId());

            pstmt.executeUpdate();
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }
}
