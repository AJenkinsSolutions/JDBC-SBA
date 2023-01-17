package jpa.entitymodels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "Course")
public class Course implements Serializable{

	

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int cid;
	
	@Column(name = "name")
	private String cName;
	
	@Column(name = "Instructor")
	private String cInstructorName;
	
	public Course() {
		this.cName = null;
		this.cInstructorName = null; 
	}
	
	public Course(String cName, String cInstructorName) {
		super();
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}
	
	


	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public String toString() {
		return "Course [cid=" + cid + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}
	
	

}
