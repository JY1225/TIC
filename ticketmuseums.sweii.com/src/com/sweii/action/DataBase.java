package com.sweii.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.erican.auth.vo.Admin;
import com.sweii.dao.BaseServiceImpl;
/**
 * 
 * @author mr_li
 * �����������֮����໥ͬ��
 *
 */
public class DataBase  extends BaseServiceImpl{
	
	/**
	 * ����������ȡ���ݿ�����
	 * @return Connection
	 */
	public static final Connection getSQL2000() {
		Connection con =  null; 
		String url = "jdbc:sqlserver://win-gms2k-HKWG01.xincache.cn:1433;databaseName=net3106025";
		String uid = "net3106025";
		String pwd = "C4M6T2p4";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	     try{   
	    	 Class.forName(driver);   
	         con = DriverManager.getConnection(url , uid , pwd);   
	     }catch(SQLException se){   
		    System.out.println("���ݿ�����ʧ�ܣ�");   
		    se.printStackTrace() ;   
	     } catch (ClassNotFoundException e) {
	    	 System.out.println("��������ʧ�ܣ�");   
			e.printStackTrace();
		}
		return con;   
     }
	/**
	 * Ԥ��ͬ��������ݹ���
	 * @param synId ͬ����
	 * @param roodId ����id
	 * @param size Ʊ��
	 * @param price Ʊ��
	 * @param total �ܶ�
	 * @param preTiem Ԥ��ʱ��
	 * @param linkMan ��ϵ��
	 * @param phone �ֻ�
	 * @param mobile �绰
	 * @param address ��ַ
	 * @return int  1 Ϊ�ɹ���0Ϊʧ��
	 */
	public int addPreOrder(Integer synId,Integer roodId,Integer 
			size,Integer price,Integer total,Date preTiem,
			String linkMan,String phone,String mobile,String address){
		Connection con=getSQL2000();
		int i=0;
		PreparedStatement pstmt = null;
		String sql="insert into Pre_order(syn_id,room_id,size,price,total,pre_time,link_man,phone,mobile,address)  values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql) ;   
			pstmt.setInt(1, synId);
			pstmt.setInt(2, roodId);
			pstmt.setInt(3, size);
			pstmt.setInt(4, price);
			pstmt.setInt(5, total);
			Timestamp time=new Timestamp(preTiem.getTime());
			pstmt.setTimestamp(6,time);
			pstmt.setString(7, linkMan);
			pstmt.setString(8, phone);
			pstmt.setString(9, mobile);
			pstmt.setString(10, address);
			i=pstmt.executeUpdate();
			close(null,pstmt,con);
		} catch (SQLException e) {
			System.out.println("û����Ҫ���µ����ݣ�");
		}finally{
			close(null,pstmt,con);
		}
		return i;
	}
	/**
	 * �������id,֪�����������ж�������
	 * @return int  ���ݱ��ж���������
	 */
	public int getmaxPreId(){
		int max=0;
		Connection con=getSQL2000();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = "select max(id) as id  from Pre_order";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				max = rs.getInt("id");
			}
			close(rs,pstmt,con);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݱ�Ϊ�գ���������ݣ�");
		}finally{
			close(rs,pstmt,con);
		}
		return max;
	}
	/**
	 * ���·������ϵ�ͬ���У���ʾ������ͬ������
	 * @param synId ͬ����
	 * @param id  ͬ��������id
	 * @return int  
	 */
	public int updatePreOrder(Integer synId,Integer id){
			Connection con=getSQL2000();
			PreparedStatement pstmt=null;
			int i=0;
			try {
				String sql="update Pre_order set syn_id = ? where id = ?";
				pstmt = con.prepareStatement(sql) ;   
				pstmt.setInt(1, synId);
				pstmt.setInt(2, id);
				i=pstmt.executeUpdate();
				close(null,pstmt,con);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("����ʧ�ܣ�û����Ҫ���µ����ݣ�");
			}finally{
				close(null,pstmt,con);
			}
			return i;
	}
	/**
	 * ��ӷ���
	 * @return
	 * @throws SQLException
	 */
	public int addRoom(){
		PreparedStatement pstmt=null;
		Connection con=getSQL2000();
		int i=0;
		try {
			String sql="insert into Room(name,Short_name)  values('����','͵�컻��')";
			pstmt= con.prepareStatement(sql) ;   
			i=pstmt.executeUpdate();
			close(null,pstmt,con);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,pstmt,con);
		}
		return i;
	}
	/**
	 * ���ʱ��η���
	 * @return int
	 */
	public int addTicketPrice(){
		int i=0;
		Connection con=getSQL2000();
		PreparedStatement pstmt=null;
		try {
			String sql="insert into ticket_price(week,start_time,end_time,price)  values('������','18:00','23:59','120')";
			pstmt= con.prepareStatement(sql) ;   
			i=pstmt.executeUpdate();
			close(null,pstmt,con);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,pstmt,con);
		}
			return i;
	}
	/**
	 * ��ʾ����
	 * @return int
	 */
	public int selectRoom(){
		Connection con=getSQL2000();
		String sql="select * from Room ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				  String name = rs.getString("name") ;   
				  System.out.println("name="+name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs,pstmt,con);
		}
		return 0;   
	}
	/**
	 * �ͷ���Դ
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public void close(ResultSet rs,PreparedStatement pstmt,Connection conn){
		try {
			 if(rs != null){   // �ر����ݼ�   
			 	  rs.close() ;   
			 }if(pstmt != null){   // �ر�����   
			      pstmt.close() ;   
			 }if(conn != null){  // �ر����Ӷ���   
			      conn.close() ;   
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) throws SQLException {
		DataBase  db=new DataBase();
		System.out.println(db.getmaxPreId());
	}
	/**
	 * �Զ�ͬ������ ����spring����
	 */
	public  boolean flag=true;
	public void check() throws Exception{
		/*
			if(flag){
				flag=false;
				try {
					System.out.println("��ʱ��������");
					int maxid=0;
					DataBase  db=new DataBase();
					maxid=db.getmaxPreId();  // ��������������id��
					List<PreOrder> list=db.selectPreOrder(maxid); //���������������������û������������ ����ͬ����Ϊnull�� ����ʾҪͬ����
					for(PreOrder pre : list){
						if(pre.getPreTime()!=null){
							int temid=pre.getId();
							pre.setCreateTime(new Date());
							pre.setType(2);
							pre.setStatus(1);
							Admin admin=new Admin();
							admin.setId(1);
							pre.setAdmin(admin);
							Room room=new Room();
							room.setId(pre.getRoomId());
							pre.setRoom(room);
							pre.setSynId(1);
							this.preOrderDao.save(pre); //��������أ� 
							db.updatePreOrder( pre.getId(), temid);//��ȡ�ոձ����һ�����ݣ�Ȼ���ٸ��·������ϵ�����,��һ������Ϊupdate��ֵ���ڶ���Ϊ����ֵ
						}else {
							System.out.println(pre.getId()+"\t"+pre.getLinkMan()+":�ͻ���Ʊʱ���Ѿ�Ϊ�գ�����ϵ����Ա��");
						}
						
					}
					//�����ϴ�������
					List<PreOrder> listpre=selectPreOrder();
					for(PreOrder preor : listpre){
						db.addPreOrder(preor.getId(), preor.getRoom().getId(), preor.getSize(), 
								preor.getPrice(), preor.getTotal(), preor.getPreTime(), 
								preor.getLinkMan(), preor.getPhone(), preor.getMobile(), preor.getAddress());
						updatePreOrder(preor.getId()); //���±���һ���ֶΣ���ʾ��ͬ�����������ϴ���������
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					flag=true;
				}
			}	*/
		}	
}
