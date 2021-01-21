package com.soolfam.yearEndParty.repository;

import com.soolfam.yearEndParty.domain.Guestbook;
import com.soolfam.yearEndParty.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestbookRepository {

    private DataSource dataSource;

    public Guestbook save(Guestbook guestbook) {
        String sql = "insert into guestbook(nickname,comment) values(?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, guestbook.getNickname());
            pstmt.setString(2, guestbook.getComment());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                guestbook.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return guestbook;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Autowired
    public GuestbookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Guestbook> findAll() {
        String sql = "select * from guestbook";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Guestbook> guestbooks = new ArrayList<>();
            while (rs.next()) {
                Guestbook guestbook = new Guestbook();
                guestbook.setId(rs.getLong("id"));
                guestbook.setNickname(rs.getString("nickname"));
                guestbook.setComment(rs.getString("comment"));

                guestbooks.add(guestbook);
            }
            return guestbooks;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
