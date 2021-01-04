package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	private static BookDAO instance = new BookDAO();
	private BookDAO() {}
	public static BookDAO getInstance() {
		return instance;
	}
	
	public ArrayList<BookVO> list = new ArrayList<>(); //회원 정보 리스트
	public boolean first = true;
	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 접속 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getMysqlConnection() {
		String url = "jdbc:mysql://localhost:3306/mysqldb";
		String user = "root";
		String pw = "root";
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//mysql 드라이버 로딩
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("mysql 접속성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close();} catch (Exception e) {e.printStackTrace();}
		if(pstmt != null) try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
		if(conn != null) try {conn.close();} catch (Exception e) {e.printStackTrace();}
	}
	
	public int getBookCode() { //도서 번호를 증가시키는 함수
		int bocde = 0;
		try {
			
			Connection conn = getConnection();
			String getNoSql = "SELECT MAX(bcode) FROM book_TBL";
			PreparedStatement pstmt = conn.prepareStatement(getNoSql);
			ResultSet rs = pstmt.executeQuery();
				
			if(rs.next()){
				bocde = rs.getInt(1) + 1;
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bocde;
	}
	
	public int insertBook(BookVO vo) throws SQLException { //도서를 추가시키는 함수
			int cnt = 0;
		try {
			String insertSql = "INSERT INTO book_TBL(bcode, btitle, bwriter, bpub, bprice, bdate)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			
			pstmt.setInt(1, vo.bcode);
			pstmt.setString(2, vo.btitle);
			pstmt.setString(3, vo.bwriter);
			pstmt.setInt(4, vo.bpub);
			pstmt.setInt(5, vo.bprice);
			pstmt.setDate(6, vo.bdate);
			cnt = pstmt.executeUpdate();
			
			list.add(vo);
			close(null, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return cnt;
	}
	
	public int updateBook(BookVO vo) { //도서 정보를 수정하는 함수
		list.set(checkBook(vo.bcode), vo);
		
		
		int cnt = 0;
		try {
			Connection conn = getConnection();
			
			String updateSql = "UPDATE book_TBL SET btitle=?, bwriter=?, bpub=?, bprice=?, bdate=? WHERE bcode = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setString(1, vo.btitle);
			pstmt.setString(2, vo.bwriter);
			pstmt.setInt(3, vo.bpub);
			pstmt.setInt(4, vo.bprice);
			pstmt.setDate(5, vo.bdate);
			pstmt.setInt(6, vo.bcode);
			cnt = pstmt.executeUpdate();
			close(null, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int deleteBook(int bcode) { //도서 정보를 삭제하는 함수(도서 삭제
		list.remove(checkBook(bcode));
		int cnt = 0;
		
		try {
			Connection conn = getConnection();
			String deleteSql = "DELETE FROM book_TBL WHERE bcode = ?";
			PreparedStatement pstmt = conn.prepareStatement(deleteSql);
			pstmt.setInt(1, bcode);
			cnt = pstmt.executeUpdate();
			close(null, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return cnt;
	}
	
	public int checkBook(int bcode) { //도서 번호를 검사하여 도서 정보를 보내는 함수
		for(int i = 0; i <= list.size(); i++) {
			if(list.get(i).bcode == bcode) {
				return i;
			}
		}
		return 0;
	}
	
	public BookVO getBook(int bcode) { //도서 정보를 select문으로 가져오기
		BookVO vo = null;
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM book_TBL WHERE bcode = ?";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, bcode);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BookVO(rs.getInt("bcode"), rs.getString("btitle"), rs.getString("bwriter"),
						rs.getInt("bprice"), rs.getInt("bpub"), rs.getDate("bdate"));
				close(rs, pstmt, conn);
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void firstBook() { //테이블의 남은 데이터를 리스트에 추가해줌
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM book_TBL ORDER BY bcode";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			
			BookVO vo = null;
			while(rs.next()){
				vo = new BookVO(rs.getInt("bcode"), rs.getString("btitle"), rs.getString("bwriter"),
					rs.getInt("bprice"), rs.getInt("bpub"), rs.getDate("bdate"));
				list.add(vo);
			}
			instance.close(rs, pstmt, conn);
			instance.first = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}