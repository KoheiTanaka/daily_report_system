package models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "bookmark")
@NamedQueries({
    @NamedQuery(
            name = "getMyAllBookmark",
            query = "SELECT b FROM Bookmark AS b WHERE b.employee =:employee ORDER BY b.id DESC "
            ),
    @NamedQuery(
            name = "getMyBookmarkCount",
            query = "SELECT COUNT(b) FROM Bookmark AS b WHERE b.employee = :employee"
            ),

    @NamedQuery(
            name = "getBookMarkByEmpAndRep",
            query = "SELECT b FROM Bookmark AS b WHERE b.employee = :employee AND b.report = :report"
            ),



})

@Entity
public class Bookmark {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@ManyToOne
@JoinColumn(name = "employee_id",nullable=false)
private Employee employee;

@ManyToOne
@JoinColumn(name = "report_id",nullable=false)
private Report report;


public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public Employee getEmployee() {
    return employee;
}

public void setEmployee(Employee employee) {
    this.employee = employee;
}

public Report getReport() {
    return report;
}

public void setReport(Report report) {
    this.report = report;
}
}